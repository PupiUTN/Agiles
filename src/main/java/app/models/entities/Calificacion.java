/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Calificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int puntaje;
    private String comentario;
    @ManyToOne
    private Reserva reserva;

    private boolean from_owner;

    //para jpa o jackson, necesito constructor vacio y todos los setters y getters de cada atributo
    public Calificacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public boolean isFrom_owner() {
        return from_owner;
    }

    public void setFrom_owner(boolean from_owner) {
        this.from_owner = from_owner;
    }


}
