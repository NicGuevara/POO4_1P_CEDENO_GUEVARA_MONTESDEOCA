package com.proyecto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import com.proyecto.constants.Constants;
import com.proyecto.service.OperacionesGenerales;
import com.proyecto.utils.GestionArchivosTxt;
import com.proyecto.utils.Mail;

    /**
     * La clase Editor extiende la clase Usuario y maneja las operaciones 
     * específicas relacionadas con los editores, como la generación de correos 
     * y la decisión sobre artículos.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class Editor extends Usuario  {
    
    private String codUnico;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String nombreJournal;


    /**
     * Constructor para la clase Editor.
     *
     * @param user El nombre de usurio del editor.
     * @param password La contraseña del editor.
     * @param rol El rol del editor.
     * @param codUnicoPersona El código único de la persona.
     */
    // Sobrecarga: Constructor para inicializar con diferentes parámetros
    public Editor(String user, String password, String rol, String codUnicoPersona){
        super(user,password,rol,codUnicoPersona);
    }

    /**
     * Devuelve una representación en cadena de los atributos del objeto,
     * concatenados con el separador definido en la clase {@link Constants}.
     *
     * <p>La cadena resultante incluye los atributos {@code nombre}, {@code apellido},
     * {@code correoElectronico} y {@code nombreJournal}, separados por
     * el valor de {@link Constants#SEPARATOR}.</p>
     *
     * @return una cadena de texto que representa los atributos del objeto, separados por el valor de {@link Constants#SEPARATOR}.
     * @see Constants#SEPARATOR
     */
    @Override
    public String toString() {
        return this.nombre + Constants.SEPARATOR + this.apellido + Constants.SEPARATOR + this.correoElectronico + Constants.SEPARATOR + this.nombreJournal;
    }

    //Getters y setters
    
    /**
     * Obtiene el correo electrónico del editor.
     * 
     * @return El correo electrónico del editor.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    /**
     * Establece el correo electrónico del editor.
     * 
     * @param correoElectronico El correo electrónico del editor.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el código único del editor.
     * 
     * @return El código único del editor.
     */
    public String getCodUnico() {
        return codUnico;
    }
    
    /**
     * Establece el código único del editor. 
     * 
     * @param codUnico El código único del editor.
     */
    public void setCodUnico(String codUnico) {
        this.codUnico = codUnico;
    }

    /**
     * Obtiene el nombre del editor.
     * 
     * @return El nombre del editor.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del editor.
     * 
     * @param nombre El nombre del editor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el apellido del editor.
     * 
     * @return El apellido del editor.
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Establece el apellido del editor.
     * 
     * @param apellido El apellido del editor.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el nombre del journal del editor.
     * 
     * @return El nombre del journal del editor.
     */
    public String getNombreJournal() {
        return nombreJournal;
    }
    
    /**
     * Establece el nombre del journal del editor.
     * 
     * @param nombreJournal El nombre del journal del editor.
     */
    public void setNombreJournal(String nombreJournal) {
        this.nombreJournal = nombreJournal;
    }


    /**
     * Genera un correo para notificar sobre el estado de un artículo.
     * 
     * @param articulo El artículo sobre el cual se generará el correo.
     * @param correo El correo electrónico del destinatario.
     * @param estado El estado del artículo.
     */
    @Override
    public void generarCorreo(String articulo, String correo, String estado) {
        String feedback = Mail.sendMail(correo, "Articulo procesado", "El siguiente articulo fue procesado: "+ articulo+", Estado: "+estado);
        System.out.println(feedback);
    }
    
    /**
     * Decide sobre el estado de un artículo y actualiza la información en el archivo.
     * 
     * @param articulo El artículo sobre el cual se tomará la decisión.
     * @param estadoPublicacion El estado de la publicación.
     * @param codUnicoEditor El código único del editor.
     */
    @Override
    public void decidirSobreArticulo(Articulo articulo, String estadoPublicacion, String codUnicoEditor){
        String lineaArticulo ;
        String nombreArchivo= "articulos";
        boolean existeArticulo = false;
        int linea=0;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoAsignaciones = urlTxt.getPath() + "/"+nombreArchivo+".txt";
        String correoAutor = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoAsignaciones));
            String[] arrayCamposArticulos;
            String codUnicoArticuloArchivo;
            String codUnicoAutorArticulo = "";
            while ((lineaArticulo = reader.readLine()) != null)
            {
                arrayCamposArticulos = lineaArticulo.split("\\|");
                codUnicoArticuloArchivo = arrayCamposArticulos[0];
                if(codUnicoArticuloArchivo.equalsIgnoreCase(articulo.getCodUnico())){
                    lineaArticulo = arrayCamposArticulos[0] + Constants.SEPARATOR +  arrayCamposArticulos[1]
                            + Constants.SEPARATOR +  arrayCamposArticulos[2]
                            + Constants.SEPARATOR +  arrayCamposArticulos[3]
                            + Constants.SEPARATOR +  arrayCamposArticulos[4]
                            + Constants.SEPARATOR +  arrayCamposArticulos[5]
                            + Constants.SEPARATOR +  estadoPublicacion
                            + Constants.SEPARATOR +  codUnicoEditor;
                    ;
                    codUnicoAutorArticulo =  arrayCamposArticulos[5];
                    existeArticulo = true;
                    break;
                }
                linea++;
            }
            reader.close();
            if(existeArticulo) {
                OperacionesGenerales claseComun = new OperacionesGenerales();
                correoAutor = claseComun.obtenerCorreoAutor(codUnicoAutorArticulo);
                GestionArchivosTxt gestionArchivoArticulos = new GestionArchivosTxt(nombreArchivo);
                gestionArchivoArticulos.modificarLinea(linea, lineaArticulo);
                this.generarCorreo(articulo.getCodUnico(), correoAutor, estadoPublicacion);
            }
        } catch (Exception e) {
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
    }


}
