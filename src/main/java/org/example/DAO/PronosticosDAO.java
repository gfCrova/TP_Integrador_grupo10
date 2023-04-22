package org.example.DAO;

import org.example.Entidades.Persona;
import org.example.Entidades.Pronostico;
import org.example.Factory.ConexionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PronosticosDAO {

    public List<Pronostico> listarPronosticos() {

        Pronostico nuevoPronostico = new Pronostico();
        List<Pronostico> prDao = new ArrayList<>();
        Connection con = ConexionFactory.getConnection();

        try (con) {

            final PreparedStatement statement = con.prepareStatement("SELECT pron.id_pronosticos, pron.persona, pron.equipo1, pron.gana1, pron.empate, pron.gana2, pron.equipo2, pron.personas_pron_id, per.id_personas, per.nombre " +
                    "FROM pronosticos pron INNER JOIN personas per ON pron.personas_pron_id = per.id_personas");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {
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

                        Pronostico pr = new Pronostico(id, persona, equipo1, gana1, empata, gana2, equipo2);
                        prDao.add(pr);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prDao;
    }

    public List<Persona> listarPersonas() {

        Connection con = ConexionFactory.getConnection();
        Persona nuevaPersona = new Persona();
        List<Persona> listaPersonas = new ArrayList<Persona>();

        try (con) {

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM personas");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {

                    while (rs.next()) {
                        int id = rs.getInt("id_personas");
                        String persona = rs.getString("nombre");

                        nuevaPersona = new Persona(id, persona);
                        listaPersonas.add(nuevaPersona);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPersonas;
    }
}
