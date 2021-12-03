package org.epam.final_project.other;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender {
    private final static Logger logger=Logger.getLogger(Sender.class.getName());
    private static final String PATH= "send.properties";
    private  String username;
    private  String password;
    private final Properties props;

    public Sender() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Properties properties=new Properties();
        try {
            properties.load(DBManager.class.getClassLoader().getResourceAsStream(PATH));
            this.password=properties.getProperty("password");
            this.username=properties.getProperty("email");
        } catch (IOException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }
    }

    public void send(String text,String title,String email){
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            //Заголовок письма
            message.setSubject(title);
            //Содержимое
            message.setText(text);

            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }
    }
}