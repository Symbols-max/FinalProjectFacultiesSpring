package org.epam.final_project.other;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private final static Logger logger=Logger.getLogger(DBManager.class.getName());
    private static final String PATH= "application.properties";

    public static Connection connectToDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }
        Properties properties=new Properties();
        try {
            properties.load(DBManager.class.getClassLoader().getResourceAsStream(PATH));
        } catch (IOException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }

        return DriverManager.getConnection(properties.getProperty("spring.datasource.url"),
                properties.getProperty("spring.datasource.username"),properties.getProperty("spring.datasource.password"));

    }


}
