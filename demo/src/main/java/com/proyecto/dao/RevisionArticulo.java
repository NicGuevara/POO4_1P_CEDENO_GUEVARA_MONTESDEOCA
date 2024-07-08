package com.proyecto.dao;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.proyecto.utils.GestionArchivos;

public class RevisionArticulo {
    private String codUnico;
    private String codUnicoArticulo;
    private String codUnicoAsignado;
    private String estadoRevision;
    private String comentario;
    private final String SEPARATOR = "|";
    
    public RevisionArticulo(String codUnicoArticulo, String codUnicoAsignado, String estadoRevision, String comentario) {
        UUID uuid = UUID.randomUUID();
        this.codUnico = uuid.toString();
        this.codUnicoArticulo = codUnicoArticulo;
        this.codUnicoAsignado = codUnicoAsignado;
        this.estadoRevision = estadoRevision;
        this.comentario = comentario;
    }
    //Getters y setters
    public String getCodUnico() {
        return codUnico;
    }
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    public String getCodUnicoArticulo() {
        return codUnicoArticulo;
    }
    public void setCodUnicoArticulo(String codUnicoArticulo) {
        this.codUnicoArticulo = codUnicoArticulo;
    }
    public String getCodUnicoAsignado() {
        return codUnicoAsignado;
    }
    public void setCodUnicoAsignado(String codUnicoAsignado) {
        this.codUnicoAsignado = codUnicoAsignado;
    }
    public String getEstadoRevision() {
        return estadoRevision;
    }
    public void setEstadoRevision(String estadoRevision) {
        this.estadoRevision = estadoRevision;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

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
