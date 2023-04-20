package org.example.Factory;

import org.example.Services.ConfigService;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionFactory {

    public static Connection getConnection() {

        ConfigService load = new ConfigService();
        load.obtenerCofiguracion();

        String url = load.getUrl();
        String username = load.getUsername();
        String password = load.getPassword();

        String driverName = load.getDriver();

        Connection con = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url,username,password);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}
