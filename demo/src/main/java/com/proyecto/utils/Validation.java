package com.proyecto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Validation proporciona métodos para validar diferentes tipos de texto.
 * Incluye validaciones para verificar si un texto contiene solo letras, letras y números, 
 * un correo electrónico válido, palabras clave con delimitadores, y caracteres especiales permitidos.
 * 
 * @author Nicole Guevara
 * @version 1.0
 * @since 2024-07-14
 */
public class Validation {

    /**
     * Verifica si el texto contiene solo letras (A-Z, a-z).
     * 
     * @param texto El texto a verificar.
     * @return true si el texto contiene solo letras, false en caso contrario.
     */
    public boolean contieneSoloLetras(String texto)
    {
        return texto.matches("[A-Za-z]+");
    }
    
    /**
     * Verifica si el texto contiene solo letras y números (A-Z, a-z, 0-9).
     * 
     * @param texto El texto a verificar.
     * @return true si el texto contiene solo letras y números, false en caso contrario.
     */
    public boolean contieneSoloLetrasYNums(String texto)
    {
        return texto.matches("[A-Za-z0-9]+");
    }
    
    /**
     * Verifica si el correo electrónico tiene un formato válido.
     * 
     * @param correo El correo electrónico a verificar.
     * @return true si el correo tiene un formato válido, false en caso contrario.
     */
    public boolean esCorreoValido(String correo)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(correo);
        return m.matches();
    }
    
    /**
     * Verifica si el texto contiene solo letras, números y el delimitador ','.
     * 
     * @param texto El texto a verificar.
     * @return true si el texto contiene solo letras, números y el delimitador ',', false en caso contrario.
     */
    public boolean contienePalabrasClavesyDelimitador(String texto)
    {
        return texto.matches("[A-Za-z0-9,]+");
    }
    
    /**
     * Verifica si el texto contiene solo letras, números, caracteres especiales '&' y espacios.
     * 
     * @param texto El texto a verificar.
     * @return true si el texto contiene solo letras, números, '&' y espacios, false en caso contrario.
     */
    public boolean contieneLetrasNumsCharsSpecials(String texto)
    {
        return texto.matches("[A-Za-z0-9,& ]+");
    }

}
