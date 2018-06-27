package com.pablocontreras.resttesting.entity;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cliente")
@Entity
public class Cliente implements Serializable{

    public Cliente(){

    }

    public Cliente(long id,String rut,String nombre,String apellido){
        this.id = id;
        this.rut=rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @GeneratedValue
    @Id
    @Column(name = "id_cliente")
    private long id;
    @Column(name = "rut_cliente")
    private String rut;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
