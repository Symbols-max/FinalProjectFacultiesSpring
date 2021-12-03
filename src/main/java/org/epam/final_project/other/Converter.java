package org.epam.final_project.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Converter {
    private final static Logger logger=Logger.getLogger(Converter.class.getName());

    public static byte[] streamToByteArray(InputStream inputStream1) {
        byte[] bytes;
        try (InputStream inputStream = inputStream1) {
            bytes = new byte[inputStream.available()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) inputStream.read();
            }
        }catch (IOException e){
            logger.log(Level.WARNING,e.getMessage(),e);
            bytes=null;
        }
        return bytes;
    }
}
