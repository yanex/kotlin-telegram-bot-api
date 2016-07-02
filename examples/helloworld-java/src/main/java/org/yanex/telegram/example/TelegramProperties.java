package org.yanex.telegram.example;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TelegramProperties {
    private static final String FILE_NAME = "telegram.properties";
    
    private static final String PROP_TOKEN = "token";
    private static final String PROP_LAST_ID = "lastId";
    
    @NotNull
    public String getToken() {
        String token = readProperties().getProperty(PROP_TOKEN);
        if (token == null) {
            throw new RuntimeException("Token is not set in " + FILE_NAME);
        }
        return token;
    }
    
    public void setLastId(long lastId) {
        Properties props = readProperties();
        props.setProperty(PROP_LAST_ID, Long.toString(lastId));
        writeProperties(props);
    }
    
    public long getLastId() {
        String lastId = readProperties().getProperty(PROP_LAST_ID);
        return lastId == null ? 0 : Long.parseLong(lastId);
    }
    
    private static void writeProperties(Properties props) {
        try (FileOutputStream output = new FileOutputStream(FILE_NAME)) {
            props.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Properties readProperties() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(FILE_NAME)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}
