package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDataProvider {

    Properties properties = new Properties();

    public ConfigDataProvider() {
        try (InputStream input = getClass().getResourceAsStream("/config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getStagingURL() {
        return properties.getProperty("qaUrl");

    }


}
