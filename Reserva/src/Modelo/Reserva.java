
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Reserva {
    int numeroEdificio;
    int numeroAula;
    String nombreEdificio;
    int hora;
    String dia;
    LocalDate fecha;
    String materia;
    String carrera;
    int capacidadAula;
    int id_fecha;

    public Reserva(int numeroEdificio, int numeroAula, int hora, String dia, LocalDate fecha) {
        this.numeroEdificio = numeroEdificio;
        this.numeroAula = numeroAula;
        this.hora = hora;
        this.dia = dia;
        this.fecha = fecha;
    }
    

    public Reserva() {
    }

    public Reserva(String nombreEdificio, int hora, String dia, int id_fecha) {
        this.nombreEdificio = nombreEdificio;
        this.hora = hora;
        this.dia = dia;
       
        this.id_fecha = id_fecha;
    }

    public int getId_fecha() {
        return id_fecha;
    }

    public void setId_fecha(int id_fecha) {
        this.id_fecha = id_fecha;
    }

    

    public int getCapacidadAula() {
        return capacidadAula;
    }

    public void setCapacidadAula(int capacidadAula) {
        this.capacidadAula = capacidadAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }
    

    public int getNumeroEdificio() {
        return numeroEdificio;
    }

    public void setNumeroEdificio(int numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }

    public int getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(int numeroAula) {
        this.numeroAula = numeroAula;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
}
