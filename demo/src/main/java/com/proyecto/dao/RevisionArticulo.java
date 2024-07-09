package com.proyecto.dao;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.proyecto.utils.GestionArchivos;

    /**
     * Clase que representa la revisión de un artículo.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class RevisionArticulo {
    private String codUnico;
    private String codUnicoArticulo;
    private String codUnicoAsignado;
    private String estadoRevision;
    private String comentario;
    private final String SEPARATOR = "|";
    
    /**
     * Constructor para la clase RevisionArticulo.
     * 
     * @param codUnicoArticulo Código único del artículo.
     * @param codUnicoAsignado Código único de la persona asignada para la revisión.
     * @param estadoRevision Estado de la revisión.
     * @param comentario Comentario sobre la revisión.
     */
    public RevisionArticulo(String codUnicoArticulo, String codUnicoAsignado, String estadoRevision, String comentario) {
        UUID uuid = UUID.randomUUID();
        this.codUnico = uuid.toString();
        this.codUnicoArticulo = codUnicoArticulo;
        this.codUnicoAsignado = codUnicoAsignado;
        this.estadoRevision = estadoRevision;
        this.comentario = comentario;
    }
    //Getters y setters
    
    /**
     * Obtiene el código único de la revisión.
     * 
     * @return El código único de la revisión.
     */
    public String getCodUnico() {
        return codUnico;
    }
    
    /**
     * Establece el código único de la revisión.
     * 
     * @param codUnico El código único de la revisión.
     */
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    
    /**
     * Obtiene el código único del artículo.
     * 
     * @return El código único del artículo.
     */
    public String getCodUnicoArticulo() {
        return codUnicoArticulo;
    }
    
    /**
     * Establece el código único del artículo.
     * 
     * @param codUnicoArticulo El código único del artículo.
     */
    public void setCodUnicoArticulo(String codUnicoArticulo) {
        this.codUnicoArticulo = codUnicoArticulo;
    }
    
    /**
     * Obtiene el código único asignado para la revisión.
     * 
     * @return El código único asignado para la revisión.
     */
    public String getCodUnicoAsignado() {
        return codUnicoAsignado;
    }
    
    /**
     * Establece el código único asignado para la revisión.
     * 
     * @param codUnicoAsignado El código único asignado para la revisión.
     */
    public void setCodUnicoAsignado(String codUnicoAsignado) {
        this.codUnicoAsignado = codUnicoAsignado;
    }
    
    /**
     * Obtiene el estado de la revisión.
     * 
     * @return El estado de la revisión.
     */
    public String getEstadoRevision() {
        return estadoRevision;
    }
    
    /**
     * Establece el estado de la revisión.
     * 
     * @param estadoRevision El estado de la revisión.
     */
    public void setEstadoRevision(String estadoRevision) {
        this.estadoRevision = estadoRevision;
    }
    
    /**
     * Obtiene el comentario sobre la revisión.
     * 
     * @return El comentario sobre la revisión.
     */
    public String getComentario() {
        return comentario;
    }
    
    /**
     * Establece el comentario sobre la revisión.
     * 
     * @param comentario El comentario sobre la revisión.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Asigna revisores y guarda la información de la revisión en un archivo.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void asignarRevisores() throws IOException 
    {
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathTxtArticulos = urlTxt.getPath() + "/revisiones.txt";
        GestionArchivos gestionArchivoArticulos = new GestionArchivos(pathTxtArticulos);
        String lineaArticuloParaArchivo =  this.codUnico + SEPARATOR
                + this.codUnicoArticulo + SEPARATOR
                + this.codUnicoAsignado + SEPARATOR
                + this.estadoRevision + SEPARATOR
                + this.comentario;
        gestionArchivoArticulos.addLineFile(lineaArticuloParaArchivo);
    }
}
