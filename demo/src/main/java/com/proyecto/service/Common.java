package com.proyecto.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.proyecto.dao.Articulo;
import com.proyecto.dao.Editor;
import com.proyecto.dao.RevisionArticulo;
import com.proyecto.dao.Revisor;
import com.proyecto.utils.GestionArchivos;
import com.proyecto.utils.PrintConsole;

public class Common {
    GestionArchivos gestionArchivos;

    private final String SEPARATOR = "|";
    

    public List<Revisor> cargarRevisores()
    {
        String lineaRevisor ;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoRevisores = urlTxt.getPath() + "/revisores.txt";
        List<Revisor> listadoRevisores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoRevisores));

            String[] arrayCamposRevisor;
            String codUnicoAutor;
            while ((lineaRevisor = reader.readLine()) != null)
            {
                arrayCamposRevisor = lineaRevisor.split("\\|");
                codUnicoAutor = arrayCamposRevisor[0];
                
                Revisor revisor = new Revisor(codUnicoAutor,
                        arrayCamposRevisor[1],
                        arrayCamposRevisor[2],
                        arrayCamposRevisor[3],
                        arrayCamposRevisor[4],
                        Integer.parseInt(arrayCamposRevisor[5]));
                listadoRevisores.add(revisor);
            }
            reader.close();
            listadoRevisores.sort(Comparator.comparing(Revisor::getNumArticulosAsignados));

        } catch (Exception e) {
            e.getStackTrace();
        }
        return listadoRevisores;
    }
    
    public Boolean asignarRevisores(String codigoArticulo)
    {
        Boolean asignoRevision = false;
        List<Revisor> listadoRevisores = new ArrayList<>();
        try {
            listadoRevisores = cargarRevisores();
            guardarAsignacionEnArchivo(codigoArticulo, listadoRevisores.get(0));
            listadoRevisores.get(0).generarCorreo(codigoArticulo, listadoRevisores.get(0).getCorreoElectronico(),"Pendiente");
            guardarAsignacionEnArchivo(codigoArticulo, listadoRevisores.get(1));
            listadoRevisores.get(0).generarCorreo(codigoArticulo, listadoRevisores.get(1).getCorreoElectronico(),"Pendiente");
            asignoRevision = true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return asignoRevision;
    }
    
    public String guardarAsignacionEnArchivo(String codigoArticulo, Revisor revisor)
    {
        try{
            RevisionArticulo revisionArticulo = new RevisionArticulo(codigoArticulo, revisor.getCodigoUnico(), "Pendiente", "Revision asignada");
            revisionArticulo.asignarRevisores();
            revisor.aumentarArticulosAsignados();
            return revisionArticulo.getCodUnico();
        }
        catch(IOException ex){
            System.out.println (ex.toString());
            System.out.println("Ocurrió un error al guardar la asignación");
            PrintConsole.o("Finalizando el programa...");
            return null;
        }
    }

    public void modificarLinea(String nombreArchivo, int numeroLinea, String linea) {
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivo = urlTxt.getPath() + "/"+nombreArchivo+".txt";
        try {
            // Leer todas las líneas del archivo
            Path path = new File(pathArchivo).toPath(); 
            List<String> lines = Files.readAllLines(path);
            // Modificar la línea específica
            if (numeroLinea >= 0 && numeroLinea < lines.size()) {
                lines.set(numeroLinea, linea);
            } else {
                System.out.println("El número de línea especificado está fuera de rango.");
            }

            // Escribir el contenido modificado de nuevo al archivo
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aumentarArticulosAsignados(String articulos,int numeroLinea, String linea){
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivo = urlTxt.getPath() + "/"+articulos+".txt";
        try {
            // Leer todas las líneas del archivo
            Path path = new File(pathArchivo).toPath(); 
            List<String> lines = Files.readAllLines(path);
            
            // Modificar la línea específica
            if (numeroLinea >= 0 && numeroLinea < lines.size()) {
                lines.set(numeroLinea, linea);
            } else {
                System.out.println("El número de línea especificado está fuera de rango.");
            }

            // Escribir el contenido modificado de nuevo al archivo
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Object> inicioSesion(String user, String password)
    {
        String lineaUsuario;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoUsuarios = urlTxt.getPath() + "/usuarios.txt";
        List<Object> listadoUsuario = new ArrayList<Object>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoUsuarios));

            String[] arrayCamposUsuario;
            String rolUsuario;
            String codUnicoPersona;
            String userUsuario;
            String passwordUsuario;
            boolean continuaBusqueda = true;
            while ((lineaUsuario = reader.readLine()) != null && continuaBusqueda)
            {
                arrayCamposUsuario = lineaUsuario.split("\\|");
                userUsuario = arrayCamposUsuario[1];
                passwordUsuario = arrayCamposUsuario[2];
                if(userUsuario.equals(user) && passwordUsuario.equals(password))
                {
                    rolUsuario = arrayCamposUsuario[3];
                    codUnicoPersona = arrayCamposUsuario[4];
                    if(rolUsuario.equals("R"))
                    {
                        Revisor revisor = new Revisor(
                                arrayCamposUsuario[1],
                                arrayCamposUsuario[2],
                                arrayCamposUsuario[3],
                                codUnicoPersona);
                        listadoUsuario.add(revisor);
                    }
                    else if(rolUsuario.equals("E"))
                    {
                        Editor editor = new Editor(
                                arrayCamposUsuario[1],
                                arrayCamposUsuario[2],
                                arrayCamposUsuario[3],
                                codUnicoPersona);
                        listadoUsuario.add(editor);
                    }
                    continuaBusqueda = false;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listadoUsuario;
    }

     public boolean registrarDecisionFinal(String codUnicoArticulo){
        boolean registraDecisionFinal = false;
        String lineaAsignacion ;
        String nombreArchivo= "revisiones";
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoAsignaciones = urlTxt.getPath() + "/"+nombreArchivo+".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoAsignaciones));

            String[] arrayCamposRevision;
            String codUnicoArticuloArchivo;
            int contRevision = 0;
            int contRevisionPendiente = 0;
            while ((lineaAsignacion = reader.readLine()) != null)
            {
                arrayCamposRevision = lineaAsignacion.split("\\|");
                codUnicoArticuloArchivo = arrayCamposRevision[1];
                if(codUnicoArticuloArchivo.equalsIgnoreCase(codUnicoArticulo)){
                    if(contRevision == 0)
                    {
                        PrintConsole.o("A continuación se visualiza la información de revisiones del artículo: \n");
                    }
                    PrintConsole.o("Estado de Revisión #"+(contRevision+1)+": "+arrayCamposRevision[3]);
                    if(arrayCamposRevision[3].equals("Pendiente"))
                    {
                        contRevisionPendiente++;
                    }
                    PrintConsole.o("Comentario de Revisión #"+(contRevision+1)+" "+arrayCamposRevision[4]+"\n");
                    contRevision++;
                }
            }
            reader.close();
            if(contRevision == 0)
            {
                PrintConsole.o("No existen revisiones asociadas al artículo con código "+codUnicoArticulo+" \n");
            } else if (contRevisionPendiente > 0) {
                PrintConsole.o("Aún existen revisiones Pendiente del artículo con código "+codUnicoArticulo+", por lo cual no puede decidir sobre este artículo \n");
            }
            else {
                registraDecisionFinal = true;
            }
        } catch (Exception e) {
            System.out.println("entro error: "+e.getMessage());
            e.getStackTrace();
        }
        return registraDecisionFinal;
    }


    public String actualizarArticuloXEditor(String user, String password, String rol, String codUnicoPersona, String codUnicoArticulo, String estadoPublicacion, String codUnicoEditor) {
        try {
            Articulo articulo = new Articulo(codUnicoArticulo, null, null, null, null, null, estadoPublicacion, codUnicoEditor);
            Editor editor = new Editor(user, password, rol, codUnicoPersona);
            editor.decidirSobreArticulo(articulo, estadoPublicacion, codUnicoEditor);
            return articulo.getCodUnico();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Ocurrió un error al actualizar articulo");
            PrintConsole.o("Finalizando el programa...");
            return null;
        }
    }

    public void actualizarAsignacion(int linea, String lineaRevisionesParaArchivo) {
        try {
            new Common().modificarLinea("revisiones", linea, lineaRevisionesParaArchivo);
            System.out.println("Revision actualizada correctamente");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Ocurrió un error al actualizar revision");
            PrintConsole.o("Finalizando el programa...");
        }
    }

    public void registrarDecisionRevisor(String codUnicoArticulo, String codigoUnico ){
        String lineaRevisor ;
        String nombreArchivo= "revisiones";
        int linea=0;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoRevisores = urlTxt.getPath() + "/"+nombreArchivo+".txt";
        Boolean encontroArticuloAsignado = false;
        String lineaRevisionesParaArchivo =  "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoRevisores));

            String[] arrayCamposRevisor;
            String codUnicoAutor;
            String codUnicoArt;
            String estado;
            while ((lineaRevisor = reader.readLine()) != null)
            {
                arrayCamposRevisor = lineaRevisor.split("\\|");
                codUnicoArt = arrayCamposRevisor[1];
                codUnicoAutor = arrayCamposRevisor[2];
                estado = arrayCamposRevisor[3];
                if(codigoUnico.equalsIgnoreCase(codUnicoAutor) &&
                   codUnicoArticulo.equalsIgnoreCase(codUnicoArt) &&
                   estado.equalsIgnoreCase("Pendiente")
                   ){
                    encontroArticuloAsignado = true;
                    
                lineaRevisionesParaArchivo =  arrayCamposRevisor[0] + SEPARATOR
                                                + arrayCamposRevisor[1] + SEPARATOR
                                                + arrayCamposRevisor[2] + SEPARATOR;
                    break;
                }
                linea++;
            }
            reader.close();

            if(encontroArticuloAsignado)
            {
                String estadoPublicacion = "";
                PrintConsole.o("Ingrese su decisión sobre este artículo:");
                PrintConsole.o("1) APROBAR");
                PrintConsole.o("2) RECHAZAR");


                Scanner usuarioInput = new Scanner( System.in );
                String decisionFinal = usuarioInput.next();
                if (decisionFinal.equals("1")) {
                    estadoPublicacion = "Aprobado";  
                } else if (decisionFinal.equals("2")) {
                    estadoPublicacion = "Rechazado";
                }
                PrintConsole.o("Ingrese su comentario:");
                Scanner comentario = new Scanner(System.in);
                String comentarioRevisor = comentario.nextLine();
                lineaRevisionesParaArchivo =  lineaRevisionesParaArchivo
                                                + estadoPublicacion + SEPARATOR
                                                + comentarioRevisor;
                                                
                actualizarAsignacion(linea, lineaRevisionesParaArchivo);
                PrintConsole.o("Su revision ha sido guardada exitosamente");
            } else {
                PrintConsole.o("No se encontro asignacion");
            }
        } catch (Exception e) {
            System.out.println("entro error: "+e.getMessage());
            e.getStackTrace();
        }
    }
     public String obtenerCorreoAutor(String codUnico)
    {
        String correoAutor = "";
        String lineaAutor;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoAutores = urlTxt.getPath() + "/autores.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathArchivoAutores));
            String[] arrayCamposAutor;
            boolean continuaBusqueda = true;
            while ((lineaAutor = reader.readLine()) != null && continuaBusqueda)
            {
                arrayCamposAutor = lineaAutor.split("\\|");
                if(codUnico.equals(arrayCamposAutor[0]) )
                {
                    correoAutor = arrayCamposAutor[3];
                    continuaBusqueda = false;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return correoAutor;
    } 
}
