package com.aluracursos.literatura.principal;

import com.aluracursos.literatura.model.Autor;
import com.aluracursos.literatura.model.Datos;
import com.aluracursos.literatura.model.DatosLibro;
import com.aluracursos.literatura.model.Libro;
import com.aluracursos.literatura.repositorio.ILibroRepositorio;
import com.aluracursos.literatura.service.ConsumoAPI;
import com.aluracursos.literatura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private final ILibroRepositorio libroRepository;
    Optional<Libro> libroBuscad;
    public Principal(ILibroRepositorio libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void mostrarElMenu(){
        int opcion = -1;
        while(opcion != 0){
            var menu = """
                    Opciones
                    1 - buscar libro por titulo
                    2 - listar libros registrados 
                    3 - listar autores registrados
                    4 - listar autores vistos en un determinado anio
                    5 - listar libros por idioma
                    0 - salir
                    """;
            System.out.println(menu);
            System.out.println("Ingrese la opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:{
                    buscarLibroPorTitulo();
                    break;
                }
                case 2:{
                    listarLibros();
                    break;
                }
                case 3:{
                    listarAutores();
                    break;
                }
                case 4:{
                    listarAutoresPorAnio();
                    break;
                }
                case 5:{
                    listarLibrosPorIdioma();
                    break;
                }
                case 0:{
                    System.out.println("Cerrando la aplicacion...");
                    break;
                }
            }
        }
    }
    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el titulo del libro a buscar: ");
        var titulo = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);
        boolean seEncontro = false;
        List<Libro> libroExistente = libroRepository.findByTituloContainingIgnoreCase(titulo);
        seEncontro = !libroExistente.isEmpty();

        Optional<DatosLibro> libroEncontrado = datos.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst();

        if (libroEncontrado.isPresent() && !seEncontro) {
            Libro libro = new Libro(libroEncontrado.get());
            libroRepository.save(libro);
            System.out.println("Libro guardado: " + libro);
        } else {
            System.out.println("Libro no encontrado o repetido");
        }
    }

    private void listarLibros() {
        var libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores() {
        var libros = libroRepository.findAll();
        var autores = libros.stream()
                .flatMap(l -> l.getAutores().stream())
                .distinct()
                .collect(Collectors.toList());

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            //System.out.println("Autores registrados:");
            //autores.forEach(System.out::println);
            List<String> librosAutor = new ArrayList<>();
            for(Autor autor : autores){
                for(Libro libro : libros){
                    if(libro.getAutores().contains(autor)){
                        librosAutor.add(libro.getTitulo());
                    }
                }
                System.out.println(autor);
                System.out.println("Libros: ");
                librosAutor.forEach(titulo -> System.out.println(titulo + ","));
                System.out.println();
            }
        }
    }

    private void listarAutoresPorAnio() {
        System.out.print("Ingrese el anio a buscar: ");
        var anioString = teclado.nextLine();
        int anio;
        try {
            anio = Integer.parseInt(anioString);
        } catch (NumberFormatException e) {
            System.out.println("Anio invalido");
            return;
        }
        var libros = libroRepository.findAll();
        var autores = libros.stream()
                .flatMap(l -> l.getAutores().stream())
                .filter(autor ->{
                    try{
                        int nacimiento = Integer.parseInt(autor.getFechaNacimiento());
                        String fallecimientoString = autor.getFechaFallecimiento();
                        int fallecimiento;
                        if(fallecimientoString == null || fallecimientoString.isEmpty()){
                            fallecimiento = Integer.MAX_VALUE;
                        }else{
                            fallecimiento = Integer.parseInt(fallecimientoString);
                        }
                        return anio >= nacimiento && anio <= fallecimiento;
                    }catch(NumberFormatException e){
                        return false;
                    }
                })
                .distinct()
                .collect(Collectors.toList());

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores en ese anio");
        } else {
            System.out.println("Autores encontrados en " + anio + ":");
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el idioma para buscar los libros: ");
        System.out.print("\nes - espanhol ");
        System.out.print("\nen - ingles ");
        System.out.print("\nfr - frances ");
        System.out.print("\npt - portuguese ");
        System.out.print("\nIngrese idioma: ");
        var idioma = teclado.nextLine();

        var libros = libroRepository.findByIdiomasContainingIgnoreCase(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            System.out.println("Libros en idioma '" + idioma + "':");
            libros.forEach(System.out::println);
        }
    }
}
