package edu.liceolapaz.mgr.jugadoresbasket.model;

import java.time.LocalDate;
import java.time.Period;

public class Jugador {
    private int id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private int alturaCm;
    private double pesoKg;
    private String posicion;
    private boolean lesionado;
    private double salarioBruto;
    private int equipoId;
    private String nombreEquipo;
    private String conferencia;

    public Jugador() {
    }

    public Jugador(int id, String nombre, String apellidos, LocalDate fechaNacimiento,
                   int alturaCm, double pesoKg, String posicion, boolean lesionado,
                   double salarioBruto, int equipoId, String nombreEquipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.alturaCm = alturaCm;
        this.pesoKg = pesoKg;
        this.posicion = posicion;
        this.lesionado = lesionado;
        this.salarioBruto = salarioBruto;
        this.equipoId = equipoId;
        this.nombreEquipo = nombreEquipo;
    }


    public double getSalarioNeto() {
        return this.salarioBruto * 0.60;
    }

    public int getEdad() {
        if (this.fechaNacimiento == null) return 0;
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getAlturaCm() { return alturaCm; }
    public void setAlturaCm(int alturaCm) { this.alturaCm = alturaCm; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public boolean isLesionado() { return lesionado; }
    public void setLesionado(boolean lesionado) { this.lesionado = lesionado; }

    public double getSalarioBruto() { return salarioBruto; }
    public void setSalarioBruto(double salarioBruto) { this.salarioBruto = salarioBruto; }

    public int getEquipoId() { return equipoId; }
    public void setEquipoId(int equipoId) { this.equipoId = equipoId; }

    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }

    public String getConferencia() { return conferencia; }
    public void setConferencia(String conferencia) { this.conferencia = conferencia; }
}