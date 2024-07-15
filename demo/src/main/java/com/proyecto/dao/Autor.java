package com.proyecto.dao;

import java.io.IOException;
import java.util.UUID;

import com.proyecto.constants.Constants;
import com.proyecto.utils.GestionArchivosTxt;


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
    private String correoElectronico;
    private String institucion;
    private String campoInvestigacion;

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
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    /**
     * Establece el correo electrónico del autor.
     * 
     * @param correoElectronico El correo electrónico del autor.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        this.correoElectronico = correo;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    
    /**
     * Devuelve una representación en cadena de los atributos del objeto,
     * concatenados con el separador definido en la clase {@link Constants}.
     *
     * <p>La cadena resultante incluye los atributos {@code nombre}, {@code apellido}
     * y {@code correoElectronico}, separados por el valor de {@link Constants#SEPARATOR}.</p>
     *
     * @return una cadena de texto que representa los atributos del objeto, separados por el valor de {@link Constants#SEPARATOR}.
     * @see Constants#SEPARATOR
     */
    @Override
    public String toString() {
        return this.nombre + Constants.SEPARATOR + this.apellido + Constants.SEPARATOR + this.correoElectronico;
    }

    /**
     * Añade la información del autor a un archivo.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void addAutor() throws IOException {
        GestionArchivosTxt gestionArchivoAutores = new GestionArchivosTxt("autores");
        String lineaAutorParaArchivo =  this.codUnico + Constants.SEPARATOR
                                        + this.nombre + Constants.SEPARATOR
                                        + this.apellido + Constants.SEPARATOR
                                        + this.correoElectronico + Constants.SEPARATOR
                                        + this.institucion + Constants.SEPARATOR
                                        + this.campoInvestigacion;
        gestionArchivoAutores.agregarLinea(lineaAutorParaArchivo);
    }
}
