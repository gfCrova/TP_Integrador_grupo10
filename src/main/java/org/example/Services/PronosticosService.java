package org.example.Services;

import org.example.DAO.PartidosDAO;
import org.example.DAO.PronosticosDAO;
import org.example.Entidades.*;

import java.util.*;

import static org.example.Enums.ResultadoEnum.*;

public class PronosticosService {

    PronosticosDAO pronDAO = new PronosticosDAO();
    PartidosDAO partidosDAO = new PartidosDAO();
    List<Pronostico> listaPron = pronDAO.listarPronosticos();
    List<Persona> listaPersonas = pronDAO.listarPersonas();
    List<Ronda> listaRondas =  partidosDAO.obtenerRondas();
    ConfigService configService = new ConfigService();

    public void generarPronosticos() {
        Pronostico pr = new Pronostico();
        configService.obtenerCofiguracion();

        for (Persona persona : listaPersonas) {
            System.out.println("Pronósticos de " + persona.getNombre() + "\n");
            int count = 0;
            persona.setRonda(listaRondas.get(count));
            persona.setPronostico(new ArrayList<>());
            while (count < persona.getRonda().getPartidos().size()) {

                Equipo equipoLocal = persona.getRonda().getPartidos().get(count).getEquipoLocal();
                Equipo equipoVisitante = persona.getRonda().getPartidos().get(count).getEquipoVisitante();
                Partido partido = persona.getRonda().getPartidos().get(count);

                pr = new Pronostico(partido, equipoLocal);

                int aciertosEnRondaActual = 0;
                for (Pronostico lpr : listaPron) {
                    pr.setIdRonda(listaRondas.get(count).getId());
                    pr.setIdPersona(lpr.getIdPersona());
                    if (lpr.getIdRonda() == listaRondas.get(count).getId()) {
                        if (pr.puntos(persona) == configService.getPuntosPronAcertado()) {
                            aciertosEnRondaActual++;
                        }
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
                }
                persona.getPronostico().add(pr);
                salidaPronosticos(persona, count);
                if (aciertosEnRondaActual == listaRondas.get(count).getPartidos().size()) {
                    int bonificacion = configService.getPuntosExtraRonda();
                    System.out.println(persona.getNombre() + " - (bonificación de " + bonificacion + " puntos por acertar todos los pronósticos en la ronda)");
                }
                count++;
            }
            System.out.println("\n");
        }
        System.out.println("\nPuntaje Total: ");
        for (Persona per : listaPersonas) {
            total(per);
        }
    }

    public void total(Persona persona) {
        int total = persona.getRonda().totalPuntos(persona.getPronostico(), persona);
        System.out.println(persona.getNombre() + " -> \t " + total + " Pts");
    }

    public void salidaPronosticos(Persona persona, int count) {
        configService.obtenerCofiguracion();
        Pronostico pron = persona.getPronostico().get(count);

        if (pron.puntos(persona) == configService.getPuntosPronAcertado()) {
            pron.setComprobar(ACERTADO);
            System.out.println(persona.getNombre() + " - Pronóstico Ronda: " + pron.getPartido().getRondaNumero() + " - " + listaRondas.get(count).getNombre() + " Partido: " + pron.getPartido().getId() + " " + pron.getComprobar() + "!. Puntos: +" + pron.puntos(persona));
        } else {
            pron.setComprobar(ERRADO);
            System.out.println(persona.getNombre() + " - Pronóstico Ronda: " + pron.getPartido().getRondaNumero() + " - " + listaRondas.get(count).getNombre() + " Partido: " + pron.getPartido().getId() + " " + pron.getComprobar() + "!. Puntos: " + pron.puntos(persona));
        }

        /*int aciertosEnRondaActual = 0;
        for (Pronostico pronostico : listaPron) {
            if (pron.getIdRonda() == listaRondas.get(count).getId()) {
                if (pron.puntos(persona) == configService.getPuntosPronAcertado()) {
                    aciertosEnRondaActual++;
                }
            }
        }
        if (aciertosEnRondaActual == listaRondas.get(count).getPartidos().size()) { // si la persona acertó todos los pronósticos en la ronda actual
            int bonificacion = configService.getPuntosExtraRonda(); // número de puntos de bonificación
            //persona.sumarPuntos(bonificacion); // sumar los puntos de bonificación a la puntuación total de la persona
            System.out.println(persona.getNombre() + " - (bonificación de " + bonificacion + " puntos por acertar todos los pronósticos en la ronda)");
        }
        if (pr.getIdRonda() == listaRondas.get(count).getId() && pr.getComprobar() == ACERTADO) {
                System.out.println(persona.getNombre() + " ACERTASTE TODOS LOS PRONÓSTICOS DE LA RONDA: " + pr.getPartido().getRondaNumero() + " Bonificación de puntos: " + configService.getPuntosExtraRonda());
        }*/
    }
}

