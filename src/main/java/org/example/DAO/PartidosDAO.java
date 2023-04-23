package org.example.DAO;

import org.example.Entidades.Partido;
import org.example.Factory.ConexionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidosDAO {

    public List<Partido> obtenerPartidos() {

        List<Partido> partidos = new ArrayList<>();
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
                        int golesEquipo2 = Integer.parseInt(rs.getString("golesEquipo1"));
                        String equipo2 = rs.getString("equipo2");

                        Partido newPartido = new Partido(id, equipo1, golesEquipo1, golesEquipo2, equipo2);
                        partidos.add(newPartido);
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


}
