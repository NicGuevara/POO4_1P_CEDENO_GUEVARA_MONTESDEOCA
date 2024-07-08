package com.proyecto.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class GestionArchivos {
    URL urlTxt = this.getClass().getClassLoader().getResource("txt");
    private String pathArchivo;

    public GestionArchivos(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    public void addLineFile(String line) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathArchivo, true));
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public int countLinesFile()
    {
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.pathArchivo));
            while (reader.readLine() != null) lines++;
            reader.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return lines;

    }
}
