package com.proyecto;


import java.io.IOException;

import com.proyecto.console.MenuConsole;
import com.proyecto.utils.Mail;

public class Main {

    public static void main(String[] args) throws IOException {
        Mail.inicializarSistemaCorreo();
        MenuConsole menu = new MenuConsole();
        menu.run();
    }
}