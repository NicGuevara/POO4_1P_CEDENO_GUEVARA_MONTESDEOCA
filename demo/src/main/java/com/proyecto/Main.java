package com.proyecto;


import java.io.IOException;

import com.proyecto.console.MenuConsole;
import com.proyecto.utils.Mail;

     /**
     * Clase principal que inicializa el sistema de correo 
     * y ejecuta el menú de la consola.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Inicializa el sistema de correo y ejecuta el menú de la consola
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     * @throws IOException Si ocurre un error al inicializar el sistema de correo o al ejecutar el menú de la consola.
     */
    public static void main(String[] args) throws IOException {
        Mail.inicializarSistemaCorreo();
        MenuConsole menu = new MenuConsole();
        menu.run();
    }
}