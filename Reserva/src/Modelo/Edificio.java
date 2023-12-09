/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Edificio {
    int numeroEdificio;
    int numeroAula;
    String nombreEdificio;
   public int id;

   
    public Edificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Edificio() {
    }

    public Edificio(int numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }

    public Edificio(int numeroEdificio, int numeroAula) {
        this.numeroEdificio = numeroEdificio;
        this.numeroAula = numeroAula;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.numeroEdificio;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edificio other = (Edificio) obj;
        if (this.numeroEdificio != other.numeroEdificio) {
            return false;
        }
        if (this.numeroAula != other.numeroAula) {
            return false;
        }
        return Objects.equals(this.nombreEdificio, other.nombreEdificio);
    }



    @Override
    public String toString() {
        return nombreEdificio ;
    }

    
    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

  

   

   

    
    
}
