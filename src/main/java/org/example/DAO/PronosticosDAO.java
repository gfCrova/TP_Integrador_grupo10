package org.example.DAO;

import org.example.Entidades.Pronostico;
import org.example.Factory.ConexionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PronosticosDAO {

    public void listarEmpleado() {

        List<Pronostico> lista = new ArrayList<>();
        Connection con = ConexionFactory.getConnection();

        try (con) {
            System.out.println("Lista de Pronósticos:");

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM pronosticos");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id_pronosticos") +
                                ", Persona: " + rs.getString("persona") +
                                ", Equipo1: " + rs.getString("equipo1") +
                                ", Gana1: " + rs.getString("gana1") +
                                ", Empate: " + rs.getString("empate") +
                                ", Gana2: " + rs.getString("gana2") +
                                ", Equipo2: " + rs.getString("equipo2"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
