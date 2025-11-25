package com.Davivienda;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca el servidor Spring Boot.
 */
@SpringBootApplication
public class FinanzasBackend implements CommandLineRunner {

public static void main(String[] args) {
	SpringApplication.run(FinanzasBackend.class, args);
}

/**
 * Método opcional que se ejecuta cuando el servidor arranca.
 * Útil si quieres insertar datos de prueba o hacer logs.
 */
@Override
public void run(String... args) throws Exception {
	System.out.println("🚀 Servidor Finanzas iniciado correctamente");
}
}
