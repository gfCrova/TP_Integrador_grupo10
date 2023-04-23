package org.example.DAO;

import org.example.Entidades.Equipo;
import org.example.Entidades.Partido;
import org.example.Entidades.Ronda;
import org.example.Factory.ConexionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidosDAO {

    List<Ronda> listaRonda = new ArrayList<>();

    public List<Partido> obtenerPartidos() {

        List<Partido> partidos = new ArrayList<>();
        Partido partido;
        Connection con = ConexionFactory.getConnection();

        try (con) {

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM partidos");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {
                    while (rs.next()) {

                        int id = rs.getInt("id_partidos");
                        String equipo1 = rs.getString("equipo1");
                        int golesEquipo1 = Integer.parseInt(rs.getString("golesEquipo1"));
                        int golesEquipo2 = Integer.parseInt(rs.getString("golesEquipo2"));
                        String equipo2 = rs.getString("equipo2");
                        int rondas_id = Integer.parseInt(rs.getString("rondas_id"));

                        partido = new Partido(id, new Equipo(equipo1), golesEquipo1, golesEquipo2, new Equipo(equipo2), rondas_id);
                        partidos.add(partido);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

    List<Partido> list = obtenerPartidos();

    public List<Ronda> obtenerRondas() {

        Ronda ronda;
        Connection con = ConexionFactory.getConnection();

        try (con) {

            final PreparedStatement statement = con.prepareStatement("SELECT r.id_rondas, r.nombre, r.fases_id, p.rondas_id FROM rondas r INNER JOIN partidos p ON r.id_rondas = p.rondas_id");

            try (statement) {
                statement.execute();
                final ResultSet rs = statement.getResultSet();

                try (rs) {
                    while (rs.next()) {
                        int id = rs.getInt("r.id_rondas");
                        String nombreDeRonda = rs.getString("r.nombre");
                        int fk_fases = Integer.parseInt(rs.getString("r.fases_id"));
                        int rondas_id = Integer.parseInt(rs.getString("p.rondas_id"));

                        ronda = new Ronda();
                        ronda.setId(id);
                        ronda.setNombre(nombreDeRonda);
                        ronda.setNumero(fk_fases);
                        if (ronda.getId() == rondas_id) {
                            ronda.setPartidos(list);
                        }
                        listaRonda.add(ronda);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaRonda;
    }
}
