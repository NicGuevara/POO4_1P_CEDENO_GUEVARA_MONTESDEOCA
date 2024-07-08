package com.proyecto.dao;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.proyecto.utils.GestionArchivos;

public class Autor {
    private String codUnico;
    private String nombre;
    private String apellido;
    private String correo;
    private String institucion;
    private String campoInvestigacion;
    private final String SEPARATOR = "|";

    //Creación de getters y setters
    public String getCodUnico() {
        return codUnico;
    }
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getInstitucion() {
        return institucion;
    }
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }
    public void setCampoInvestigacion(String campoInvestigacion) {
        this.campoInvestigacion = campoInvestigacion;
    }
    //Constructor inicializando variables de autor
    public Autor(String nombre, String apellido, String correo, String institucion, String campoInvestigacion) {
        UUID uuid = UUID.randomUUID();
        this.codUnico = uuid.toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    public void addAutor() throws IOException {
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathTxtAutores = urlTxt.getPath() + "/autores.txt";
        GestionArchivos gestionArchivoAutores = new GestionArchivos(pathTxtAutores);
        String lineaAutorParaArchivo =  this.codUnico + SEPARATOR
                                        + this.nombre + SEPARATOR
                                        + this.apellido + SEPARATOR
                                        + this.correo + SEPARATOR
                                        + this.institucion + SEPARATOR
                                        + this.campoInvestigacion;
        gestionArchivoAutores.addLineFile(lineaAutorParaArchivo);
    }
}
