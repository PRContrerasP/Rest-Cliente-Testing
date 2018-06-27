package com.pablocontreras.resttesting.model;

import com.pablocontreras.resttesting.entity.Cliente;

public class mCliente {

    public mCliente() {
    }

    public mCliente(Cliente cliente) {
        this.id = cliente.getId();
        this.rut = cliente.getRut();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
    }

    private long id;
    private String rut;
    private String nombre;
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

    public mCliente(long id, String rut, String nombre, String apellido) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;


    }
}
