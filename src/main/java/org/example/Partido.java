package org.example;

public class Partido {

    private Equipo equipoLocal;
    private Equipo equipoVisitante;

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo local) {
        this.equipoLocal = local;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo visitante) {
        this.equipoVisitante = visitante;
    }

    public ResultadoEnum resultadoPartido(Equipo equipo) {
        if (equipo == equipoLocal) {
            if (equipoLocal.getCantidadGoles() > equipoVisitante.getCantidadGoles()) {
                System.out.println("Resultado del partido: \n" +
                        ResultadoEnum.GANADOR + " " + equipoLocal.getNombre() + " " + equipoLocal.getCantidadGoles() + " - " +
                        equipoVisitante.getCantidadGoles() + " " + equipoVisitante.getNombre() + " " + ResultadoEnum.PERDEDOR);
                return ResultadoEnum.GANADOR;
            } else if  (equipoLocal.getCantidadGoles() == equipoVisitante.getCantidadGoles()) {
                System.out.println("Resultado del partido: \n" +
                        ResultadoEnum.EMPATE + ": " + equipoLocal.getNombre() + " " + equipoLocal.getCantidadGoles() + " - " + equipoVisitante.getCantidadGoles() + " " + equipoVisitante.getNombre());
                return ResultadoEnum.EMPATE;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        } else if (equipo == equipoVisitante) {
            if (equipoVisitante.getCantidadGoles() > equipoLocal.getCantidadGoles()) {
                System.out.println("Resultado del partido: \n" +
                        ResultadoEnum.PERDEDOR + " " + equipoLocal.getNombre() + " " + equipoLocal.getCantidadGoles() + " - " +
                        equipoVisitante.getCantidadGoles() + " " + equipoVisitante.getNombre() + " " + ResultadoEnum.GANADOR);
                return ResultadoEnum.GANADOR;
            } else if  (equipoLocal.getCantidadGoles() == equipoVisitante.getCantidadGoles()) {
                System.out.println("Resultado del partido: \n" +
                        ResultadoEnum.EMPATE + ": " + equipoLocal.getNombre() + " " + equipoLocal.getCantidadGoles() + " - " + equipoVisitante.getCantidadGoles() + " " + equipoVisitante.getNombre());
                return ResultadoEnum.EMPATE;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        } else {
            if (equipoLocal.getCantidadGoles() == equipoVisitante.getCantidadGoles()) {
                System.out.println("Resultado del partido: \n" +
                        ResultadoEnum.EMPATE + ": " + equipoLocal.getNombre() + " " + equipoLocal.getCantidadGoles() + " - " + equipoVisitante.getCantidadGoles() + " " + equipoVisitante.getNombre());
                return ResultadoEnum.EMPATE;
            }
            return ResultadoEnum.EMPATE;
        }
    }
}
