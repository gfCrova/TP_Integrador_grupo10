package org.example.DAO;
import org.example.Services.PronosticosService;

public class PruebaDAO {
    public static void main(String[] args) {

        PronosticosService pronosService = new PronosticosService();

        pronosService.obtenerResultados();
        pronosService.generarPronosticos();
    }
}
