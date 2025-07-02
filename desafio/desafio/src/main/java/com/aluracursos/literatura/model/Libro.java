package com.aluracursos.literatura.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "autores", joinColumns = @JoinColumn(name = "libro_id"))
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private Double numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idiomas = datosLibro.idiomas();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();

        // Convertir DatosAutor a String (nombre del autor)
        this.autores = datosLibro.autor()
                .stream()
                .map(a -> new Autor(
                        a.nombre(),
                        a.fechaDeNacimiento() != null ? a.fechaDeNacimiento().toString() : "",
                        a.fechaDeFallecimiento() != null ? a.fechaDeFallecimiento().toString() : ""
                ))
                .toList();
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "\n-----LIBRO-------\n" +
                "Titulo: " + titulo + '\n' +
                "Autores: " + autores + '\n' +
                "Idiomas: " + idiomas + '\n' +
                "NumeroDeDescargas: " + numeroDeDescargas +'\n';
    }
}
