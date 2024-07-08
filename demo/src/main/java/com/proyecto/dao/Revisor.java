package com.proyecto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import com.proyecto.service.Common;
import com.proyecto.utils.Mail;

public class Revisor extends Usuario implements Comparable<Revisor> {

    private String codigoUnico;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String especialidad;
    private int numArticulosAsignados;
    private final String SEPARATOR = "|";
    
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

    public Revisor(String user, String password, String rol, String codUnicoPersona){
        super(user,password,rol,codUnicoPersona);
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
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
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public int getNumArticulosAsignados() {
        return numArticulosAsignados;
    }
    public void setNumArticulosAsignados(int numArticulosAsignados) {
        this.numArticulosAsignados = numArticulosAsignados;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // overriding the compareTo method of Comparable class
    @Override public int compareTo(Revisor compareRevisor) {
        int compareNumArticulosAsignados = ((Revisor) compareRevisor).getNumArticulosAsignados();

        //  For Ascending order
        return this.numArticulosAsignados - compareNumArticulosAsignados;

        // For Descending order do like this
        // return compareNumArticulosAsignados-this.numArticulosAsignados;
    }

    @Override
    public void generarCorreo(String articulo, String correo, String estado) {
        String feedback = Mail.sendMail(correo, "Articulo asignado", "El siguiente articulo fue asignado: "+ articulo + ", Estado: "+estado);
        System.out.println(feedback);
    }

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
