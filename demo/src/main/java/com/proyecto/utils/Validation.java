package com.proyecto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean contieneSoloLetras(String texto)
    {
        return texto.matches("[A-Za-z]+");
    }
    public boolean contieneSoloLetrasYNums(String texto)
    {
        return texto.matches("[A-Za-z0-9]+");
    }
    public boolean esCorreoValido(String correo)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(correo);
        return m.matches();
    }
    public boolean contienePalabrasClavesyDelimitador(String texto)
    {
        return texto.matches("[A-Za-z0-9,]+");
    }
    public boolean contieneLetrasNumsCharsSpecials(String texto)
    {
        return texto.matches("[A-Za-z0-9,& ]+");
    }

}
