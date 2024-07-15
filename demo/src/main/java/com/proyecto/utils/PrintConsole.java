package com.proyecto.utils;

/**
 * La clase PrintConsole proporciona un método estático para imprimir mensajes en la consola.
 * 
 * @author Nicole Guevara
 * @version 1.0
 * @since 2024-07-14
 */
public class PrintConsole {
    
    /**
     * Imprime un objeto en la consola.
     * 
     * @param s El objeto a imprimir. Se llama al método toString 
     * del objeto para obtener la representación en cadena.
     */
    public static void o(Object s)
    {
        System.out.println(s);
    }
}
