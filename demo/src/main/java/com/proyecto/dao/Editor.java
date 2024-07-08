package com.proyecto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import com.proyecto.service.Common;
import com.proyecto.utils.Mail;

public class Editor extends Usuario  {
    private String codUnico;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String nombreJournal;
    private final String SEPARATOR = "|";

    //Getters y setters
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

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

    public String getNombreJournal() {
        return nombreJournal;
    }
    public void setNombreJournal(String nombreJournal) {
        this.nombreJournal = nombreJournal;
    }
    public Editor(String user, String password, String rol, String codUnicoPersona){
        super(user,password,rol,codUnicoPersona);
    }

    @Override
    public void generarCorreo(String articulo, String correo, String estado) {
        String feedback = Mail.sendMail(correo, "Articulo procesado", "El siguiente articulo fue procesado: "+ articulo+", Estado: "+estado);
        System.out.println(feedback);
    }
    
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
                    lineaArticulo = arrayCamposArticulos[0] + SEPARATOR +  arrayCamposArticulos[1]
                            + SEPARATOR +  arrayCamposArticulos[2]
                            + SEPARATOR +  arrayCamposArticulos[3]
                            + SEPARATOR +  arrayCamposArticulos[4]
                            + SEPARATOR +  arrayCamposArticulos[5]
                            + SEPARATOR +  estadoPublicacion
                            + SEPARATOR +  codUnicoEditor;
                    ;
                    codUnicoAutorArticulo =  arrayCamposArticulos[5];
                    existeArticulo = true;
                    break;
                }
                linea++;
            }
            reader.close();
            if(existeArticulo) {
                Common claseComun = new Common();
                correoAutor = claseComun.obtenerCorreoAutor(codUnicoAutorArticulo);
                claseComun.modificarLinea(nombreArchivo, linea, lineaArticulo);
                this.generarCorreo(articulo.getCodUnico(), correoAutor, estadoPublicacion);
            }
        } catch (Exception e) {
            System.out.println("entro error: "+e.getMessage());
            e.getStackTrace();
        }
    }


}
