package org.example.Services;

import org.example.DAO.PartidosDAO;
import org.example.DAO.PronosticosDAO;
import org.example.Entidades.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.Enums.ResultadoEnum.EMPATE;
import static org.example.Enums.ResultadoEnum.GANADOR;

public class PronosticosService {

    public void generarPronosticos() {

        PronosticosDAO dao = new PronosticosDAO();
        List<Pronostico> listaPron = dao.listarPronosticos();
        List<Persona> listaPersonas = dao.listarPersonas();

        PartidosDAO partidosDAO = new PartidosDAO();
        List<Partido> listaPartidos = partidosDAO.obtenerPartidos();
        List<Ronda> listaRondas =  partidosDAO.obtenerRondas();

        for (Ronda ronda : listaRondas) {
            for (Partido partido : listaPartidos) {
                if (partido.getRondaNumero() == ronda.getId()) {
                    ronda.setPartidos(listaPartidos);
                }
            }
        }

        for (Persona persona : listaPersonas) {
            int count = 0;
            persona.setRonda(listaRondas.get(count));
            persona.setPronostico(new ArrayList<Pronostico>());
            while (count < persona.getRonda().getPartidos().size()) {

                Equipo equipoLocal = persona.getRonda().getPartidos().get(count).getEquipoLocal();
                Equipo equipoVisitante = persona.getRonda().getPartidos().get(count).getEquipoVisitante();

                Pronostico pr = new Pronostico(persona.getRonda().getPartidos().get(count), equipoLocal);

                for (Pronostico lpr : listaPron) {
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
                persona.getPronostico().add(pr);

                if (pr.puntos(persona) == 1) {
                    System.out.println(persona.getNombre() + " - Pronóstico Partido " + (count + 1) + ": ACERTADO!. Puntos: +" + pr.puntos(persona));
                } else {
                    System.out.println(persona.getNombre() + " - Pronóstico Partido " + (count + 1) + ": NO acertado. Puntos: " + pr.puntos(persona));
                }
                count++;
            }

            generarPuntaje(persona);
        }
    }

    public void generarPuntaje(Persona persona) {
        // Total de Puntos de partidos acertados
        int total = persona.getRonda().totalPuntos(persona.getPronostico(), persona);
        System.out.println("Puntaje Total de " + persona.getNombre() +  " es = " + total + "\n");

        // Puntos Extra por 'Acertar' todos los pronósticos de la Ronda
        ConfigService configService = new ConfigService();
        configService.obtenerCofiguracion();
        // total == 5
        if (total == persona.getRonda().getPartidos().size()) {
            total += configService.getPuntosExtraRonda();
            System.out.println(persona.getNombre() + " ACERTASTE TODOS LOS PRONÓSTICOS DE LA RONDA!");
            System.out.println("Bofificación de Puntos: +" + configService.getPuntosExtraRonda());
            System.out.println("Puntaje Total de " + persona.getNombre() + " es = " + total + "\n");
        }
    }
}
