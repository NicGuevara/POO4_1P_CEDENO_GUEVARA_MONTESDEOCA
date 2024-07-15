package com.proyecto.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * La clase GestionArchivosTxt proporciona métodos para gestionar archivos de texto,
 * incluyendo la adición y modificación de líneas.
 * 
 * @author Nicole Guevara
 * @version 1.0
 * @since 2024-07-14
 */
public class GestionArchivosTxt {
    URL urlTxt = this.getClass().getClassLoader().getResource("txt");
    private String pathArchivo;
    private String nombreArchivo;

    /**
     * Obtiene el nombre del archivo.
     * 
     * @param nombreArchivo El nombre del archivo sin extensión.
     */
    public GestionArchivosTxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo +".txt";
        this.pathArchivo = this.urlTxt.getPath() + "/" + this.nombreArchivo;
    }

    /**
     * Establece el nombre del archivo.
     * 
     * @return El nombre del archivo.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene la ruta del archivo.
     * 
     * @return La ruta del archivo.
     */
    public String getPathArchivo() {
        return pathArchivo;
    }

    /**
     * Establece la ruta del archivo.
     * 
     * @param pathArchivo La nueva ruta del archivo.
     */
    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }


    /**
     * @param line La línea a agregar.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void agregarLinea(String line) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathArchivo, true));
        writer.write(line);
        writer.newLine();
        writer.close();
    }


    /**
     * Modifica una línea específica en un archivo.
     *
     * @param numeroLinea El número de la línea a modificar.
     * @param linea La nueva información para reemplazar la línea existente.
     */
    public void modificarLinea(int numeroLinea, String linea) {
        try {
            // Leer todas las líneas del archivo
            Path path = new File(this.pathArchivo).toPath();
            List<String> lines = Files.readAllLines(path);
            // Modificar la línea específica
            if (numeroLinea >= 0 && numeroLinea < lines.size()) {
                lines.set(numeroLinea, linea);
            } else {
                System.out.println("El número de línea especificado está fuera de rango.");
            }

            // Escribir el contenido modificado de nuevo al archivo
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
