package com.proyecto.dao;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.proyecto.utils.GestionArchivos;


    /**
     * La clase Autor representa a un autor con su información personal y académica.
     * Proporciona métodos para obtener y establecer la información del autor, así como
     * para añadir la información del autor a un archivo.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class Autor {
    private String codUnico;
    private String nombre;
    private String apellido;
    private String correo;
    private String institucion;
    private String campoInvestigacion;
    private final String SEPARATOR = "|";

    //Getters y setters
    
    /**
     * Obtiene el código único del autor.
     * 
     * @return El código único del autor.
     */
    public String getCodUnico() {
        return codUnico;
    }
    
    /**
     * Establece el código único del autor.
     * 
     * @param codUnico
     */
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }
    
    /**
     * Obtiene el nombre del autor.
     * 
     * @return El nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del autor.
     * 
     * @param nombre El nombre del autor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el apellido del autor.
     * 
     * @return El apellido del autor.
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Establece el apellido del autor.
     * 
     * @param apellido El apellido del autor.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    /**
     * Obtiene el correo electrónico del autor.
     * 
     * @return El correo electrónico del autor.
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Establece el correo electrónico del autor.
     * 
     * @param correo El correo electrónico del autor.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Obtiene la institución del autor.
     * 
     * @return La institución del autor.
     */
    public String getInstitucion() {
        return institucion;
    }
    
    /**
     * Establece la institución del autor.
     * 
     * @param institucion La institución del autor.
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    
    /**
     * Obtiene el campo de investigación del autor
     * 
     * @return El campo de investigación del autor
     */
    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }
    
    /**
     * Establece el campo de investigación del autor
     * 
     * @param campoInvestigacion El campo de investigación del autor
     */
    public void setCampoInvestigacion(String campoInvestigacion) {
        this.campoInvestigacion = campoInvestigacion;
    }
    
    /**
     * Constructor para crear un nuevo objeto Autor
     * 
     * @param nombre El nombre del autor.
     * @param apellido El apellido del autor.
     * @param correo El correo electrónico.
     * @param institucion La institución a la que pertenece el autor.
     * @param campoInvestigacion El campo de investigación del autor.
     */
    public Autor(String nombre, String apellido, String correo, String institucion, String campoInvestigacion) {
        UUID uuid = UUID.randomUUID();
        this.codUnico = uuid.toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    /**
     * Añade la información del autor a un archivo.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
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
