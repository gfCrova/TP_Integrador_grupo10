package org.example.Services;

import org.example.DAO.PronosticosDAO;
import org.example.Entidades.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.Enums.ResultadoEnum.EMPATE;
import static org.example.Enums.ResultadoEnum.GANADOR;

public class PronosticosService {

    ArrayList<Partido> partidosRonda1 = new ArrayList<>();
    Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);

    // Tomar lista de resultados de un archivo
    public void obtenerResultados() {
        FilesService files = new FilesService();
        files.leerResultados(files.getFileResultados(), partidosRonda1);

        for (int i = 0; i < ronda1.getPartidos().size(); i++) {
            System.out.print("Partido " + (i + 1) + ": " + ronda1.getPartidos().get(i));
        }
        System.out.println("\n");
    }

    public void generarPronosticos() {

        PronosticosDAO dao = new PronosticosDAO();
        List<Pronostico> listaPron = dao.listarPronosticos();
        List<Persona> listaPersonas = dao.listarPersonas();

        for (Persona persona : listaPersonas) {
            persona.setRonda(ronda1);
            persona.setPronostico(new ArrayList<Pronostico>());
            int count = 0;
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

                if(pr.puntos(persona) == 1) {
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
        if (total == 5) {
            total += configService.getPuntosExtraRonda();
            System.out.println(persona.getNombre() + " ACERTASTE TODOS LOS PRONÓSTICOS DE LA RONDA!");
            System.out.println("Bofificación de Puntos: +" + configService.getPuntosExtraRonda());
            System.out.println("Puntaje Total de " + persona.getNombre() + " es = " + total + "\n");
        }
    }
}
