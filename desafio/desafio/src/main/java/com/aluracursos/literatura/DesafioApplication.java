package com.aluracursos.literatura;

import com.aluracursos.literatura.principal.Principal;
import com.aluracursos.literatura.repositorio.ILibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
	private ILibroRepositorio libroRepositorio;
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepositorio);
		principal.mostrarElMenu();
	}
}
