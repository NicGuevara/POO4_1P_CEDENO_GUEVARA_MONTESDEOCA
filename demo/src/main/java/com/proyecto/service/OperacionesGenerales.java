package com.proyecto.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.proyecto.constants.Constants;
import com.proyecto.dao.*;
import com.proyecto.utils.GestionArchivosTxt;
import com.proyecto.utils.PrintConsole;

    /**
     *  La clase OperacionesGenerales se encarga de los procesos compartidos
     *  entre las clases y facilita la gestión de archivos.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public class OperacionesGenerales {

    /**
     *  Carga la lista de revisores desde un archivo
     * 
     * @return Listado de Revisores.
     */
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
                        Integer.parseInt(arrayCamposRevisor[5]),
                        "", "", "", "");
                listadoRevisores.add(revisor);
            }
            reader.close();
            listadoRevisores.sort(Comparator.comparing(Revisor::getNumArticulosAsignados));

        } catch (Exception e) {
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
        return listadoRevisores;
    }
    
    /**
     * Asigna revisores a un artículo dado su código único.
     * 
     * @param codigoArticulo El código único del artículo.
     * @return true si la asignación es exitosa, de lo contrario, false.
     */
    public Boolean asignarRevisores(String codigoArticulo)
    {
        Boolean asignoRevision = false;
        List<Revisor> listadoRevisores = new ArrayList<>();
        try {
            listadoRevisores = cargarRevisores();
            guardarAsignacionEnArchivo(codigoArticulo, listadoRevisores.get(0));
            System.out.println("Revisor #1 asignado:");
            System.out.println(listadoRevisores.get(0).toString());
            listadoRevisores.get(0).generarCorreo(codigoArticulo, listadoRevisores.get(0).getCorreoElectronico(),"Pendiente");
            System.out.println("Revisor #2 asignado:");
            guardarAsignacionEnArchivo(codigoArticulo, listadoRevisores.get(1));
            System.out.println(listadoRevisores.get(1).toString());
            listadoRevisores.get(0).generarCorreo(codigoArticulo, listadoRevisores.get(1).getCorreoElectronico(),"Pendiente");
            asignoRevision = true;
        } catch (Exception e) {
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
        return asignoRevision;
    }
    
    /**
     * Guarda la asignación de un revisor a un artículo en un archivo.
     * 
     * @param codigoArticulo El código único del artículo.
     * @param revisor El revisor asignado al artículo.
     * @return El código único de la revisión si la asignación es exitosa, de lo contrario, null.
     */
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


    /**
     * Aumenta el número de artículos asignados a un revisor.
     * 
     * @param articulos El nombre del archivo que contiene los artículos.
     * @param numeroLinea El número de la línea a modificar.
     * @param linea La nueva información para reemplazar la línea existente.
     */
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

    /**
     * Inicia sesión con el nombre de usuario y la contraseña proporcionados.
     * 
     * @param user El nombre de usurio.
     * @param password La contraseña del usuario.
     * @return Una lista de objetos que contiene el usuario 
     *         si la autenticación es exitosa, de lo contrario, una lista vacía.
     */
    public List<Usuario> inicioSesion(String user, String password)
    {
        String lineaUsuario;
        URL urlTxt = this.getClass().getClassLoader().getResource("txt");
        String pathArchivoUsuarios = urlTxt.getPath() + "/usuarios.txt";
        List<Usuario> listadoUsuario = new ArrayList<Usuario>();
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
                        Usuario revisor = new Revisor(
                                "", "", "", "", "", 0,
                                arrayCamposUsuario[1],
                                arrayCamposUsuario[2],
                                arrayCamposUsuario[3],
                                codUnicoPersona);
                        listadoUsuario.add(revisor);
                    }
                    else if(rolUsuario.equals("E"))
                    {
                        Usuario editor = new Editor(
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
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
        return listadoUsuario;
    }

    /**
     * Registra la decisión final sobre un artículo dado su código único.
     * 
     * @param codUnicoArticulo El código único del artículo.
     * @return true si la decisión final se registra exitosamente, de lo contrario, false.
     */
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
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
        return registraDecisionFinal;
    }


    /**
     * Actualiza el estado de un artículo por parte de un editor.
     * 
     * @param user El nombre de usuario del editor.
     * @param password La contraseña del editor. 
     * @param rol El rol del editor.
     * @param codUnicoPersona El código único de la persona.
     * @param codUnicoArticulo El código único del artículo.
     * @param estadoPublicacion El nuevo estado de publicación del artículo.
     * @param codUnicoEditor El código único del editor.
     * @return El código único del artículo si la actualización es exitosa, de lo contrario, null.
     */
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

    /**
     *  Actualiza la asignación de una revisión en el archivo de revisiones.
     * 
     * @param linea El número de la línea a actualizar.
     * @param lineaRevisionesParaArchivo La nueva información de la revisión para reemplazar la línea existente.
     */
    public void actualizarAsignacion(int linea, String lineaRevisionesParaArchivo) {
        try {
            new GestionArchivosTxt("revisiones").modificarLinea(linea, lineaRevisionesParaArchivo);
            System.out.println("Revision actualizada correctamente");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Ocurrió un error al actualizar revision");
            PrintConsole.o("Finalizando el programa...");
        }
    }

    /**
     * Registra la decisión del revisor 
     * 
     * @param codUnicoArticulo El código único artículo para registrar la decisión del revisor.
     * @param codigoUnico El código único del revisor.
     */
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
                    
                lineaRevisionesParaArchivo =  arrayCamposRevisor[0] + Constants.SEPARATOR
                                                + arrayCamposRevisor[1] + Constants.SEPARATOR
                                                + arrayCamposRevisor[2] + Constants.SEPARATOR;
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
                                                + estadoPublicacion + Constants.SEPARATOR
                                                + comentarioRevisor;
                                                
                actualizarAsignacion(linea, lineaRevisionesParaArchivo);
                PrintConsole.o("Su revision ha sido guardada exitosamente");
            } else {
                PrintConsole.o("No se encontro asignacion");
            }
        } catch (Exception e) {
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
    }


    /**
     * Guarda la información de un autor en un archivo
     *
     * @param nombreAutor El nombre del autor
     * @param apellidoAutor El apellido del autor
     * @param correoAutor El correo del autor
     * @param institucionAutor La institución del autor
     * @param campoInvestigacionAutor El campo de investigación del autor
     * @return El código único del autor si se guarda correctamente,
     *         de lo contrario, retorna null.
     */
    public String guardarAutorEnArchivo(String nombreAutor, String apellidoAutor, String correoAutor, String institucionAutor, String campoInvestigacionAutor)
    {
        try{
            Autor autor = new Autor(nombreAutor, apellidoAutor, correoAutor, institucionAutor, campoInvestigacionAutor);
            autor.addAutor();
            return autor.getCodUnico();
        }
        catch(IOException ex){
            System.out.println("Ocurrió un error al guardar el autor ingresado");
            return null;
        }
    }
    
    /**
     * Obtiene el correo electrónico de un autor dado su código único.
     * 
     * @param codUnico El código único del autor.
     * @return un Correo de Autor.
     */
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
            System.out.println(Constants.MSJ_GENERAL_ERROR+"\n\n");
        }
        return correoAutor;
    }



    /**
     * Guarda la información de un articulo en un archivo.
     *
     * @param titulo El título del artículo.
     * @param resumen El resumen del artículo.
     * @param contenido El contenido del artículo.
     * @param palabrasClave Las palabras claves de articulo, separadas por comas.
     * @param codUnicoAutor El código único del autor
     * @return El código único del articulo si se guarda correctamente ,
     *         de lo contrario, retorna null.
     */
    public String guardarArticuloEnArchivo(String titulo, String resumen, String contenido, String palabrasClave, String codUnicoAutor)
    {
        try{
            String[] arrayPalabrasClave = palabrasClave.split(",");
            ArrayList<String> listPalabrasClave = new ArrayList<>(Arrays.asList(arrayPalabrasClave));
            Articulo articulo = new Articulo(titulo, resumen, contenido, listPalabrasClave, codUnicoAutor);
            articulo.addArticulo();
            return articulo.getCodUnico();
        }
        catch(IOException ex){
            System.out.println("Ocurrió un error al guardar el artículo ingresado");
            return null;
        }
    }


}
