package com.proyecto.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.proyecto.constants.Constants;
import com.proyecto.utils.GestionArchivosTxt;

    /**La clase "Articulo" se encarga de gestionar los datos de un artículo.
     * Proporciona métodos para manipular y guardar articulos en un archivo de texto.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class Articulo {

    private String codUnico;
    private String titulo;
    private String resumen;
    private String contenido;
    private String estadoPublicacion;
    private ArrayList<String> palabrasClave = new ArrayList<>();
    private String codUnicoAutor;
    private String codUnicoEditor;

    /**
     * Constructor que inicializa un nuevo artículo con un código único
     * aleatorio y estado de publicación "Pendiente".
     * 
     * @param titulo El título del artículo.
     * @param resumen Un resumen de artículo.
     * @param contenido El contenido completo del artículo.
     * @param palabrasClave Una lista de palabras claves asociadas al artículo.
     * @param codUnicoAutor Un código único para el autor del artículo.
     */
    public Articulo(String titulo, String resumen, String contenido, ArrayList<String> palabrasClave, String codUnicoAutor) {
        this.codUnico = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.codUnicoAutor = codUnicoAutor;
        this.estadoPublicacion = "Pendiente";
    }

    /**
     * Constructor que inicializa un nuevo articulo con todos los campos especificados.
     * 
     * @param codUnico El código único para el artículo.
     * @param titulo El título del artículo.
     * @param resumen Un resumen del artículo.
     * @param contenido El contenido completo del artículo.
     * @param palabrasClave Una lista de palabras claves, unidas por comas.
     * @param codUnicoAutor Un código único para el autor del artículo.
     * @param estadoPublicacion El estado de la publicación de artículo
     * @param codUnicoEditor Un código único para el Editor
     */
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
    
    /**
     * Obtiene el código único del artículo.
     * 
     * @return El código único del artículo.
     */
    public String getCodUnico() {
        return codUnico;
    }
    
    /**
     * Establece el código único del artículo.
     * 
     * @param codUnico El código único del artículo.
     */
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    
    /**
     * Obtiene el título del artículo.
     * 
     * @return El título del artículo.
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Establece el título del artículo.
     * 
     * @param titulo El título del artículo.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Obtiene el resumen del artículo.
     * 
     * @return El resumen del artículo.
     */
    public String getResumen() {
        return resumen;
    }
    
    /**
     * Establece el resumen del artículo.
     * 
     * @param resumen El resumen del artículo.
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
    /**
     * Obtiene el contenido del artículo.
     * 
     * @return El contenido del artículo.
     */
    public String getContenido() {
        return contenido;
    }
    
    /**
     * Establece el contenido del artículo.
     * 
     * @param contenido el contenido del artículo.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    /**
     * Obtiene una lista de palabras claves.
     * 
     * @return Una lista de palabras claves.
     */
    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }
    
    /**
     * Establece una lista de palabras claves.
     * 
     * @param palabrasClave Una lista de paalabras claves.
     */
    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    
    /**
     * Obtiene un oódigo único del autor del articulo.
     * 
     * @return Un oódigo único del autor del articulo.
     */
    public String getCodUnicoAutor() {
        return codUnicoAutor;
    }
    
    /**
     * Establece un oódigo único del autor del articulo.
     * 
     * @param codUnicoAutor Un oódigo único del autor del articulo.
     */
    public void setCodUnicoAutor(String codUnicoAutor) {
        this.codUnicoAutor = codUnicoAutor;
    }
    
    /**
     * Obtiene un oódigo único del editor del articulo.
     * 
     * @return Un oódigo único del editor del articulo.
     */
    public String getCodUnicoEditor() {
        return codUnicoEditor;
    }
    
    /**
     * Establece un oódigo único de editor.
     * 
     * @param codUnicoEditor Un oódigo único de editor.
     */
    public void setCodUnicoEditor(String codUnicoEditor) {
        this.codUnicoEditor = codUnicoEditor;
    }
    
    /**
     * Obtiene el estado de publicación del artículo.
     * 
     * @return El estado de publicación del artículo.
     */
    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }
    
    /**
     * Establece el estado de publicación del artículo.
     * 
     * @param estadoPublicacion El estado de publicación del artículo.
     */
    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    /**
     * Agrega la informacion del articulo a un archivo de texto llamado "articulos.txt".
     * 
     * @throws IOException Si ocurre un error de entrada/salida
     */
    public void addArticulo() throws IOException {
        GestionArchivosTxt gestionArchivoArticulos = new GestionArchivosTxt("articulos");
        String lineaArticuloParaArchivo =  this.codUnico + Constants.SEPARATOR
                + this.titulo + Constants.SEPARATOR
                + this.resumen + Constants.SEPARATOR
                + this.contenido + Constants.SEPARATOR
                + String.join(",", this.palabrasClave) + Constants.SEPARATOR
                + this.codUnicoAutor + Constants.SEPARATOR
                + this.estadoPublicacion + Constants.SEPARATOR;
        gestionArchivoArticulos.agregarLinea(lineaArticuloParaArchivo);
    }

}
