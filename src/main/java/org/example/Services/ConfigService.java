package org.example.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    private String driver, url, username, password;
    private int puntosPronAcertado, puntosExtraRonda, puntosExtraFase;

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

    public int getPuntosPronAcertado() { return puntosPronAcertado; }

    public int getPuntosExtraRonda() { return puntosExtraRonda; }

    public int getPuntosExtraFase() { return puntosExtraFase; }

    public void obtenerCofiguracion() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\config.ini");
            properties.load(fileInputStream);

            this.driver = properties.getProperty("driverName");
            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
            this.puntosPronAcertado = Integer.parseInt(properties.getProperty("puntos_pronostico_acertado"));
            this.puntosExtraRonda = Integer.parseInt(properties.getProperty("puntos_extra_por_ronda_acertada"));
            this.puntosExtraFase = Integer.parseInt(properties.getProperty("puntos_extra_por_fase_acertada"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
