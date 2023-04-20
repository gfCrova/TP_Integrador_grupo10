package org.example.DAO;

import org.example.Entidades.Equipo;
import org.example.Entidades.Persona;
import org.example.Entidades.Pronostico;
import org.example.Factory.ConexionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.Enums.ResultadoEnum.EMPATE;
import static org.example.Enums.ResultadoEnum.GANADOR;

public class PronosticosDAO {

    public void listarEmpleado() {

        Connection con = ConexionFactory.getConnection();

        try (con) {
            System.out.println("Lista de Pronósticos: ");

            final PreparedStatement statement = con.prepareStatement("SELECT pron.id_pronosticos, pron.persona, pron.equipo1, pron.gana1, pron.empate, pron.gana2, pron.equipo2, pron.personas_pron_id, per.id_personas, per.nombre FROM pronosticos pron INNER JOIN personas per ON pron.personas_pron_id = per.id_personas");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {
                    int count = 0;

                    while (rs.next()) {

                        int id = rs.getInt("pron.id_pronosticos");
                        String persona = rs.getString("pron.persona");
                        String equipo1 = rs.getString("pron.equipo1");
                        String gana1 = rs.getString("pron.gana1");
                        String empata = rs.getString("pron.empate");
                        String gana2 = rs.getString("pron.gana2");
                        String equipo2 = rs.getString("pron.equipo2");
                        int personaPronosticoId = rs.getInt("pron.personas_pron_id");

                        int id_personas = rs.getInt("per.id_personas");
                        String nombre = rs.getString("per.nombre");

                        System.out.println("ID: " + id + ", Nombre: " + persona + ", Equipo1: " + equipo1 + ", Gana1: " + gana1 + ", Gana2: " + gana2  + ", Equipo2: " + equipo2);
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
