package edu.liceolapaz.mgr.jugadoresbasket.model;

public class Equipo {
    private int id;
    private String nombre;
    private String ciudad;
    private String conferencia; // "ESTE" o "OESTE"

    public Equipo() {
    }

    public Equipo(int id, String nombre, String ciudad, String conferencia) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.conferencia = conferencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getConferencia() { return conferencia; }
    public void setConferencia(String conferencia) { this.conferencia = conferencia; }

    @Override
    public String toString() {
        return nombre;
    }
}