package com.proyecto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import com.proyecto.service.Common;
import com.proyecto.utils.Mail;

    /**
     * La clase "Revisor" representa a un revisor que extiende la clase "Usuario".
     * Esta clase incluye información específica del revisor, como su especialidad 
     * y el número de artículos asignados, y métodos para gestionar su estado.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class Revisor extends Usuario implements Comparable<Revisor> {

    private String codigoUnico;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String especialidad;
    private int numArticulosAsignados;
    private final String SEPARATOR = "|";
    
    /**
     * Constructor que inicializa un revisor con todos sus atributos
     * 
     * @param codigoUnico El código único del revisor.
     * @param nombre El nombre del revisor.
     * @param apellido El apellido del revisor.
     * @param correoElectronico El correo electrónico del revisor.
     * @param especialidad La especialidad del revisor.
     * @param numArticulosAsignados El número de artículos asignados al revisor.
     */
    public Revisor(String codigoUnico, String nombre,
            String apellido, String correoElectronico, String especialidad, int numArticulosAsignados) {
        super(null, null, null, null);
        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.especialidad = especialidad;
        this.numArticulosAsignados = numArticulosAsignados;
    }

    /**
     * Constructor que inicializa un revisor con las credenciales de usuario.
     * 
     * @param user El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param rol El rol del usuario.
     * @param codUnicoPersona El código único de la persona.
     */
    public Revisor(String user, String password, String rol, String codUnicoPersona){
        super(user,password,rol,codUnicoPersona);
    }

    //Getters y setters
    
    /**
     * Obtiene el código único del revisor.
     * 
     * @return El código único del revisor.
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    
    /**
     * Establece el código único del revisor.
     * 
     * @param codigoUnico El código único del revisor.
     */
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    
    /**
     * Obtiene el nombre del revisor. 
     * 
     * @return El nombre del revisor.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del revisor. 
     * 
     * @param nombre El nombre del revisor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    
    /**
     * Obtiene el apellido del revisor.
     * 
     * @return El apellido del revisor.
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Establece el apellido del revisor.
     * 
     * @param apellido El apellido del revisor.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    /**
     * Obtiene el nombre de usuario del revisor.
     * 
     * @return El nombre de usuario del revisor.
     */
    public String getUser() {
        return user;
    }
    
    /**
     * Establece el nombre de usuario del revisor.
     * 
     * @param user El nombre de usuario del revisor.
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    /**
     * Obtiene la contraseña del revisor.
     * 
     * @return La contraseña del revisor.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Establece la contraseña del revisor.
     * 
     * @param password La contraseña del revisor.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Obtiene la especialidad del revisor.
     * 
     * @return La especialidad del revisor.
     */
    public String getEspecialidad() {
        return especialidad;
    }
    
    /**
     * Establece la especialidad del revisor.
     * 
     * @param especialidad La especialidad del revisor.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    /**
     * Obtiene el número de artículos asignados al revisor.
     * 
     * @return El número de artículos asignados al revisor.
     */
    public int getNumArticulosAsignados() {
        return numArticulosAsignados;
    }
    
    /**
     * Establece el número de artículos asignados al revisor.
     * 
     * @param numArticulosAsignados El número de artículos asignados al revisor.
     */
    public void setNumArticulosAsignados(int numArticulosAsignados) {
        this.numArticulosAsignados = numArticulosAsignados;
    }

    /**
     * Obtiene el correo electrónico del revisor.
     * 
     * @return El correo electrónico del revisor.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del revisor.
     * 
     * @param correoElectronico El correo electrónico del revisor.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Compara el número de artículos asignados entre dos revisores.
     * 
     * @param compareRevisor El revisor con el cual se va a comparar.
     * @return La diferencia en el número de artículos asignados.
     */
    @Override public int compareTo(Revisor compareRevisor) {
        int compareNumArticulosAsignados = ((Revisor) compareRevisor).getNumArticulosAsignados();

        // Para orden ascendente
        return this.numArticulosAsignados - compareNumArticulosAsignados;

        // For Descending order do like this
        // return compareNumArticulosAsignados-this.numArticulosAsignados;
    }

     /**
     * Genera un correo notificando que un artículo ha sido asignado.
     * 
     * @param articulo El código único del artículo.
     * @param correo El correo electrónico del destinatario.
     * @param estado El estado del artículo.
     */
    @Override
    public void generarCorreo(String articulo, String correo, String estado) {
        String feedback = Mail.sendMail(correo, "Articulo asignado", "El siguiente articulo fue asignado: "+ articulo + ", Estado: "+estado);
        System.out.println(feedback);
    }

    /**
     * Aumenta el número de artículos asignados al revisor en uno y actualiza el archivo de revisores.
     */
    public void aumentarArticulosAsignados(){
        String lineaRevisor ;
        String nombreArchivo= "revisores";
        int linea=0;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoRevisores = urlTxt.getPath() + "/"+nombreArchivo+".txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoRevisores));

            String[] arrayCamposRevisor;
            String codUnicoAutor;
            while ((lineaRevisor = reader.readLine()) != null)
            {
                arrayCamposRevisor = lineaRevisor.split("\\|");
                codUnicoAutor = arrayCamposRevisor[0];
                if(codUnicoAutor.equalsIgnoreCase(codigoUnico)){
                    lineaRevisor = arrayCamposRevisor[0] + SEPARATOR +  arrayCamposRevisor[1] 
                                                 + SEPARATOR +  arrayCamposRevisor[2]
                                                 + SEPARATOR +  arrayCamposRevisor[3]
                                                 + SEPARATOR +  arrayCamposRevisor[4]
                                                 + SEPARATOR +   (Integer.parseInt(arrayCamposRevisor[5])+1);
                    break;
                }
                linea++;
            }
            reader.close();
            
            new Common().modificarLinea(nombreArchivo, linea, lineaRevisor);
        } catch (Exception e) {
            System.out.println("entro error: "+e.getMessage());
            e.getStackTrace();
        }
    }

    /**
     * Decide sobre el estado de publicación de un artículo y actualiza el archivo de artículos.
     * 
     * @param articulo El artículo a evaluar.
     * @param estadoPublicacion El estado de publicación del artículo.
     * @param codUnicoEditor El código único del editor que tomó la decisión.
     */
    @Override
    public void decidirSobreArticulo(Articulo articulo, String estadoPublicacion, String codUnicoEditor){
        String lineaArticulo ;
        String nombreArchivo= "articulos";
        int linea=0;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoAsignaciones = urlTxt.getPath() + "/"+nombreArchivo+".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoAsignaciones));
            String[] arrayCamposArticulos;
            String codUnicoArticuloArchivo;
            while ((lineaArticulo = reader.readLine()) != null)
            {
                arrayCamposArticulos = lineaArticulo.split("\\|");
                codUnicoArticuloArchivo = arrayCamposArticulos[0];
                if(codUnicoArticuloArchivo.equalsIgnoreCase(articulo.getCodUnico())){
                    lineaArticulo = arrayCamposArticulos[0] + SEPARATOR +  arrayCamposArticulos[1]
                            + SEPARATOR +  arrayCamposArticulos[2]
                            + SEPARATOR +  arrayCamposArticulos[3]
                            + SEPARATOR +  arrayCamposArticulos[4]
                            + SEPARATOR +  arrayCamposArticulos[5]
                            + SEPARATOR +  estadoPublicacion
                            + SEPARATOR +  codUnicoEditor;
                    ;
                    break;
                }
                linea++;
            }
            reader.close();
            new Common().modificarLinea(nombreArchivo, linea, lineaArticulo);
        } catch (Exception e) {
            System.out.println("entro error: "+e.getMessage());
            e.getStackTrace();
        }
    }

}
