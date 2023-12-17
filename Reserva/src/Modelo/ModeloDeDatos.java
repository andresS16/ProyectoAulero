/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class ModeloDeDatos {
    
    String edificio;
    int numeroAula;
     int capacidad;
    int hora;
    String dia;
   private LocalDate fecha;

    public ModeloDeDatos(String edificio, int numeroAula, int capacidad, int hora, String dia, LocalDate fecha) {
        this.edificio = edificio;
        this.numeroAula = numeroAula;
        this.capacidad = capacidad;
        this.hora = hora;
        this.dia = dia;
        this.fecha = fecha;
    }

    public ModeloDeDatos() {
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(int numeroAula) {
        this.numeroAula = numeroAula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
