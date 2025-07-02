package com.aluracursos.literatura.repositorio;

import com.aluracursos.literatura.model.Autor;
import com.aluracursos.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ILibroRepositorio extends JpaRepository<Libro,Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a")
    List<String> listaAutores();

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a WHERE a.fechaNacimiento LIKE CONCAT('%', :anio, '%') OR a.fechaFallecimiento LIKE CONCAT('%', :anio, '%')")
    List<Autor> listarAutoresPorAnho(String anio);


    List<Libro> findByIdiomasContainingIgnoreCase(String idioma);
}
