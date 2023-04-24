package org.example.Services;

import org.example.DAO.PartidosDAO;
import org.example.DAO.PronosticosDAO;
import org.example.Entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.Enums.ResultadoEnum.*;

public class PronosticosService {

    PronosticosDAO dao = new PronosticosDAO();
    List<Pronostico> listaPron = dao.listarPronosticos();
    List<Persona> listaPersonas = dao.listarPersonas();

    PartidosDAO partidosDAO = new PartidosDAO();
    List<Partido> listaPartidos = partidosDAO.obtenerPartidos();
    List<Ronda> listaRondas =  partidosDAO.obtenerRondas();

    ConfigService configService = new ConfigService();

    Pronostico pr;

    public void generarPronosticos() {

        for (Persona persona : listaPersonas) {
            int count = 0;
            persona.setRonda(listaRondas.get(count));
            persona.setPronostico(new ArrayList<>());
            while (count < persona.getRonda().getPartidos().size()) {

                Equipo equipoLocal = persona.getRonda().getPartidos().get(count).getEquipoLocal();
                Equipo equipoVisitante = persona.getRonda().getPartidos().get(count).getEquipoVisitante();

                pr = new Pronostico(persona.getRonda().getPartidos().get(count), equipoLocal);

                for (Pronostico lpr : listaPron) {
                    pr.setIdPersona(lpr.getIdPersona());
                    if (lpr.getPersona().equals(persona.getNombre())) {
                        if (lpr.getEquipo1().equals(equipoLocal.getNombre()) || lpr.getEquipo2().equals(equipoVisitante.getNombre())) {
                            if (lpr.getGana1().contains("x")) {
                                pr.setResultado(GANADOR);
                            } else if (lpr.getGana2().contains("x")) {
                                pr.setEquipo(equipoVisitante);
                                pr.setResultado(GANADOR);
                            } else if (lpr.getEmpate().contains("x")) {
                                pr.setResultado(EMPATE);
                            }
                        }
                    }
                }
                salidaPronosticos(persona, count);
                configService.obtenerCofiguracion();
                persona.getPronostico().add(pr);
                count++;
            }
            System.out.println("\n");
        }
        for (Persona per : listaPersonas) {
            total(per);
        }
    }

    public void total(Persona persona) {
        int total = persona.getRonda().totalPuntos(persona.getPronostico(), persona);
        System.out.println(persona.getNombre() + " - Puntaje Total: " + total);
    }

    public void salidaPronosticos(Persona persona, int count) {

        if (listaRondas.get(count).getId() <= 7) {
            if (pr.puntos(persona) == configService.getPuntosPronAcertado()) {
                pr.setComprobar(ACERTADO);
                System.out.println(persona.getNombre() + " - Pronóstico Ronda: " + pr.getPartido().getRondaNumero() + " - " + listaRondas.get(count).getNombre() + " Partido: " + pr.getPartido().getId() + " " + pr.getComprobar() + "!. Puntos: +" + pr.puntos(persona));
                if (Objects.equals(listaRondas.get(count).getId(), pr.getPartido().getRondaNumero()) && Objects.equals(pr.getComprobar(), ACERTADO)) {
                    System.out.println(persona.getNombre() + " ACERTASTE TODOS LOS PRONÓSTICOS DE LA RONDA " + pr.getPartido().getRondaNumero());
                    System.out.println("Bofificación de Puntos: +" + configService.getPuntosExtraRonda());
                }
            } else {
                pr.setComprobar(ERRADO);
                System.out.println(persona.getNombre() + " - Pronóstico Ronda: " + pr.getPartido().getRondaNumero() + " - " + listaRondas.get(count).getNombre() + " Partido: " + pr.getPartido().getId() + " " + pr.getComprobar() + "!. Puntos: " + pr.puntos(persona));
            }
        }
    }
}
