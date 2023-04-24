package org.example.DAO;
import org.example.Entidades.Partido;
import org.example.Entidades.Pronostico;
import org.example.Entidades.Ronda;
import org.example.Services.PronosticosService;

import java.util.HashSet;
import java.util.List;

public class PruebaDAO_Entrega3 {
    public static void main(String[] args) {

        PronosticosService pronosService = new PronosticosService();
        PartidosDAO partidosDAO = new PartidosDAO();
        List<Ronda> rondas = partidosDAO.obtenerRondas();
        HashSet<Integer> hasSet = new HashSet<>();

        System.out.println("\n Pronósticos Deportivos \n");

        // Rondas
        int i = 0;
        for (Ronda ron : rondas) {
            if (!hasSet.contains(ron.getId())) {
                hasSet.add(ron.getId());
                System.out.println("Ronda: " + ron.getId() + " " + ron.getNombre() + "\n" +
                        partidosDAO.filtrarPartidosPorClaveForanea(ron.getPartidos(), hasSet.size()) + "\n");
            }
            i++;
        }

        // Pronósticos
        pronosService.generarPronosticos();
    }
}
