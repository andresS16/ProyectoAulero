
package Modelo;

import java.util.Objects;


public class Aula {
    private int id;
    private int numAula;
    private int capacidad;
    private String edificio;

    public Aula(int id, int numAula, int capacidad) {
        this.id = id;
        this.numAula = numAula;
        this.capacidad = capacidad;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public Aula() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumAula() {
        return numAula;
    }

    public void setNumAula(int numAula) {
        this.numAula = numAula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Aula other = (Aula) obj;
    return this.numAula == other.numAula && Objects.equals(this.edificio, other.edificio);
}

        

    @Override
    public String toString() {
        return capacidad+"";
    }

    public String toStringNumeroAula(){
        return ""+numAula+"";
    }
    public String toStringEdificio() {
        return  edificio+"";
    }
   

   
    
    
    
}
