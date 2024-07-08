package com.proyecto.dao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.proyecto.utils.GestionArchivos;

public class Articulo {

    private String codUnico;
    private String titulo;
    private String resumen;
    private String contenido;
    private String estadoPublicacion;
    private ArrayList<String> palabrasClave = new ArrayList<>();
    private String codUnicoAutor;
    private String codUnicoEditor;
    private final String SEPARATOR = "|";

    public Articulo(String titulo, String resumen, String contenido, ArrayList<String> palabrasClave, String codUnicoAutor) {
        this.codUnico = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.codUnicoAutor = codUnicoAutor;
        this.estadoPublicacion = "Pendiente";
    }
    public Articulo(String codUnico, String titulo, String resumen, String contenido, ArrayList<String> palabrasClave, String codUnicoAutor,String estadoPublicacion, String codUnicoEditor) {
        this.codUnico = codUnico;
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.codUnicoAutor = codUnicoAutor;
        this.estadoPublicacion = estadoPublicacion;
        this.codUnicoEditor = codUnicoEditor;
    }
    //Getters y setters
    public String getCodUnico() {
        return codUnico;
    }
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }
    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    public String getCodUnicoAutor() {
        return codUnicoAutor;
    }
    public void setCodUnicoAutor(String codUnicoAutor) {
        this.codUnicoAutor = codUnicoAutor;
    }
    public String getCodUnicoEditor() {
        return codUnicoEditor;
    }
    public void setCodUnicoEditor(String codUnicoEditor) {
        this.codUnicoEditor = codUnicoEditor;
    }
    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }
    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public void addArticulo() throws IOException {
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathTxtArticulos = urlTxt.getPath() + "/articulos.txt";
        GestionArchivos gestionArchivoArticulos = new GestionArchivos(pathTxtArticulos);
        String lineaArticuloParaArchivo =  this.codUnico + SEPARATOR
                + this.titulo + SEPARATOR
                + this.resumen + SEPARATOR
                + this.contenido + SEPARATOR
                + String.join(",", this.palabrasClave) + SEPARATOR
                + this.codUnicoAutor + SEPARATOR
                + this.estadoPublicacion + SEPARATOR;
        gestionArchivoArticulos.addLineFile(lineaArticuloParaArchivo);
    }

}
