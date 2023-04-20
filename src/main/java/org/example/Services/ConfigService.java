package org.example.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    private String driver;
    private String url;
    private String username;
    private String password;

    public ConfigService() {
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void obtenerCofiguracion() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\config.ini");
            properties.load(fileInputStream);

            this.driver = properties.getProperty("driverName");
            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
