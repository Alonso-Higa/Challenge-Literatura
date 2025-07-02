package com.aluracursos.literatura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Autor {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "fecha_fallecimiento")
    private String fechaFallecimiento;

    public Autor(){}
    public Autor(String nombre, String fechaNacimiento, String fechaFallecimiento){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public String getFechaFallecimiento() {
        return fechaFallecimiento;
    }
    @Override
    public String toString(){
        return "Autor: " + nombre + '\n' +
                "Fecha nacimiento: " + fechaNacimiento + '\n' +
                "Fecha de fallecimiento: " + fechaFallecimiento;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) &&
                Objects.equals(fechaNacimiento, autor.fechaNacimiento) &&
                Objects.equals(fechaFallecimiento, autor.fechaFallecimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaNacimiento, fechaFallecimiento);
    }
}
