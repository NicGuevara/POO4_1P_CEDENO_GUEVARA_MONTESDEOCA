package com.proyecto.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.proyecto.dao.Articulo;
import com.proyecto.dao.Autor;
import com.proyecto.dao.Editor;
import com.proyecto.dao.EnumRolType;
import com.proyecto.dao.Revisor;
import com.proyecto.service.Common;
import com.proyecto.utils.PrintConsole;
import com.proyecto.utils.Validation;


    /**
     * La clase "Menuconsole" se utiliza para mostrar 
     * las opciones que pueda escoger el Usuario y 
     * guarda la información que este ingrese.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */
public class MenuConsole {
    public boolean isRunning;
    public String[] menuItems;

    /**
     * Constructor de la clase MenuConsole.
     * Inicializa los elementos del menú y establece el estado de ejecución a true 
     */
    public MenuConsole()
    {
        isRunning = true;
        menuItems = new String[]{"Someter Artículo","Iniciar Sesión", "Salir"};
    }

    /**
     * Método principal para ejecutar el menú.
     * Muestra el menú y espera la entrada del usuario en un bucle hasta que el usuario decida salir.
     */
    public void run()
    {
        while(isRunning)
        {
            showMenu() ;
            inputMenuUser();
        }
    }

    /**
     * Muestra el menú principal en la consola.
     */
    public void showMenu()
    {
        PrintConsole.o("*************************************");
        PrintConsole.o("***********MENU PRINCIPAL************");
        PrintConsole.o("*************************************");
        int i=1;
        for(String item : menuItems)
        {
            PrintConsole.o(i+") "+item);
            i++;
        }
    }

    /**
     * Maneja la entrada del usuario para seleccionar una opción del menú.
     * Realiza las acciones correspondientes según la opción seleccionada.
     */
    public void inputMenuUser()
    {
        Scanner userInput = new Scanner( System.in );
        String next = userInput.next();
        if(next.equals("1"))
        {
            inputMenuAutor();
        }
        else if(next.equals("2"))
        {
            inputMenuUsuario();
        }
        else if(next.equals("3"))
        {
            PrintConsole.o("Vuelve pronto...Adiós");
            isRunning = false;
            userInput.close();
        }
        else
        {
            PrintConsole.o("No existe la opción seleccionada. Por favor ingrese la información correctamente.");
        }
    }


    /**
     * Maneja la entrada del autor para someter un artículo.
     * Solicita y valida la información personal y del artículo.
     */
    public void inputMenuAutor()
    {
        Scanner autorInput = new Scanner( System.in );
        try
        {
            Validation validationInputs = new Validation();
            PrintConsole.o("****Elegiste someter un artículo.**** \n");

            PrintConsole.o("Por favor ingresa tu información personal: \n");

            PrintConsole.o("Ingrese su nombre: ");

            String nombreAutor = autorInput.nextLine();
            if(!validationInputs.contieneSoloLetras(nombreAutor))
            {
                throw new Exception("El valor ingresado para nombre no es correcto. Sólo se permiten el ingreso de letras");
            }

            PrintConsole.o("Ingrese su apellido: ");
            String apellidoAutor = autorInput.nextLine();
            if(!validationInputs.contieneSoloLetras(apellidoAutor))
            {
                throw new Exception("El valor ingresado para apellido no es correcto. Sólo se permiten el ingreso de letras");
            }

            PrintConsole.o("Ingrese su correo electrónico: ");
            String correoAutor = autorInput.nextLine();
            if(!validationInputs.esCorreoValido(correoAutor))
            {
                throw new Exception("El valor ingresado para correo no es válido.");
            }

            PrintConsole.o("Ingrese su institución: ");
            String institucionAutor = autorInput.nextLine();
            if(!validationInputs.contieneSoloLetrasYNums(institucionAutor))
            {
                throw new Exception("El valor ingresado para institución no es correcto. Sólo se permiten el ingreso de letras y números");
            }

            PrintConsole.o("Ingrese su campo de investigación: ");
            String campoInvestigacionAutor = autorInput.nextLine();
            if(!validationInputs.contieneSoloLetrasYNums(campoInvestigacionAutor))
            {
                throw new Exception("El valor ingresado para campo de investigación no es correcto. Sólo se permiten el ingreso de letras y números");
            }

            PrintConsole.o("\nGuardando información de Autor... \n");
            String codUnicoAutor = guardarAutorEnArchivo(nombreAutor, apellidoAutor, correoAutor, institucionAutor, campoInvestigacionAutor);
            PrintConsole.o("Información de autor guardada exitosamente.\n");

            PrintConsole.o("\n");
            PrintConsole.o("Por favor ingresa la información de su artículo: \n");

            PrintConsole.o("Ingrese el título de su artículo: ");
            String tituloArticulo = autorInput.nextLine();
            if(!validationInputs.contieneLetrasNumsCharsSpecials(tituloArticulo))
            {
                throw new Exception("El valor ingresado para título no es correcto. Sólo se permiten el ingreso de letras y números");
            }

            PrintConsole.o("Ingrese el resumen de su artículo: ");
            String resumenArticulo = autorInput.nextLine();
            if(!validationInputs.contieneLetrasNumsCharsSpecials(resumenArticulo))
            {
                throw new Exception("El valor ingresado para resumen no es correcto. Sólo se permiten el ingreso de letras y números");
            }

            PrintConsole.o("Ingrese el contenido de su artículo: ");
            String contenidoArticulo = autorInput.nextLine();
            if(!validationInputs.contieneLetrasNumsCharsSpecials(contenidoArticulo))
            {
                throw new Exception("El valor ingresado para contenido no es correcto. Sólo se permiten el ingreso de letras y números");
            }

            PrintConsole.o("Ingrese las palabras claves de su artículo separadas por ,: ");
            String palabrasClavesArticulo = autorInput.nextLine();
            if(!validationInputs.contienePalabrasClavesyDelimitador(palabrasClavesArticulo))
            {
                throw new Exception("El valor ingresado para plabras claves no es correcto. Sólo se permiten el ingreso de letras y números con el delimitador ,");
            }
            String codUnicoArticulo = guardarArticuloEnArchivo(tituloArticulo, resumenArticulo, contenidoArticulo, palabrasClavesArticulo, codUnicoAutor);

            PrintConsole.o("\n");
            PrintConsole.o("¿Desea enviar el artículo a revisión? \n");
            PrintConsole.o("1) SI");
            PrintConsole.o("2) NO");

            String enviarArticuloRevision = autorInput.next();
            if(enviarArticuloRevision.equals("1"))
            {
                Boolean asignoRevisiores = new Common().asignarRevisores(codUnicoArticulo);
                if (asignoRevisiores) {
                    PrintConsole.o("Revisores Asignados correctamente \n");
                } else {
                    PrintConsole.o("Ocurrió un error al asignar revisor \n");
                }
            }
            else if(enviarArticuloRevision.equals("2"))
            {
                PrintConsole.o("Saliendo de opción.... \n");
            }
            else
            {
                PrintConsole.o("No existe la opción seleccionada. Saliendo de opción....");
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage()+"\n\n");
            e.getStackTrace();
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
            System.out.println (ex.toString());
            System.out.println("Ocurrió un error al guardar el autor ingresado");
            PrintConsole.o("Finalizando el programa...");
            isRunning = false;
            return null;
        }
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
            System.out.println (ex.toString());
            System.out.println("Ocurrió un error al guardar el artículo ingresado");
            PrintConsole.o("Finalizando el programa...");
            isRunning = false;
            return null;
        }
    }

    /**
     *Maneja la entrada del usuario para iniciar sesión.
     * Solicita y valida las credenciales del usuario, luego muestra el menú correspondiente a su rol.
     */
    public void inputMenuUsuario()
    {   
        PrintConsole.o("****Iniciando sesión...**** \n");

        PrintConsole.o("Ingrese su user: \n");
        Scanner usuarioInput = new Scanner( System.in );
        String userUsuario = usuarioInput.next();

        PrintConsole.o("Ingrese su contraseña: \n");
        String passwordUsuario = usuarioInput.next();

        PrintConsole.o("****Cargando usuario...**** \n");
        List<Object> listadoUsuario = new Common().inicioSesion(userUsuario, passwordUsuario);
        boolean noExisteUsuario = listadoUsuario.isEmpty();
        String nombreRolPerfil = "";
        String nombreTarea = "";
        String codUnicoPersona = "";
        String rolUsuario = "";
        if (noExisteUsuario)
        {
            PrintConsole.o("****Usuario o Contraseña incorrectas...**** \n");
        }
        else
        {
            for (Object objUsuarioListado: listadoUsuario) {
                if (objUsuarioListado instanceof Revisor) {
                    EnumRolType enumRevisor = EnumRolType.R;
                    nombreRolPerfil = enumRevisor.getRol();
                    nombreTarea = enumRevisor.getTarea();
                    Revisor revisorListado = (Revisor) objUsuarioListado;
                    codUnicoPersona = revisorListado.getCodUnicoPersona();
                    rolUsuario = revisorListado.getRol();
                }
                else if(objUsuarioListado instanceof Editor)
                {
                    EnumRolType enumEditor = EnumRolType.E;
                    nombreRolPerfil = enumEditor.getRol();
                    nombreTarea = enumEditor.getTarea();
                    Editor editorListado = (Editor) objUsuarioListado;
                    codUnicoPersona = editorListado.getCodUnicoPersona();
                    rolUsuario = editorListado.getRol();
                }
                else
                {
                    PrintConsole.o("Su perfil no se encuentra registrado en el sistema \n");
                    break;
                }
            }
            PrintConsole.o("************************************* \n");
            PrintConsole.o("***********MENÚ "+nombreRolPerfil+"*************** \n");
            PrintConsole.o("************************************* \n");
            PrintConsole.o("Tarea Asignada: "+nombreTarea+" \n");
            PrintConsole.o("Ingrese el código del artículo \n");
            Scanner articulosInput = new Scanner( System.in );
            String codUnicoArticulo = usuarioInput.next();
            if(nombreRolPerfil.equals("EDITOR"))
            {
                boolean registraDecisionFinal = new Common().registrarDecisionFinal(codUnicoArticulo);
                if(registraDecisionFinal)
                {
                    String estadoPublicacion = "";
                    PrintConsole.o("Ingrese su decisión final sobre este artículo:");
                    PrintConsole.o("1) PUBLICAR");
                    PrintConsole.o("2) NO PUBLICAR");

                    String decisionFinal = usuarioInput.next();
                    if(decisionFinal.equals("1"))
                    {
                        estadoPublicacion = "Publicada";
                    }
                    else
                    {
                        estadoPublicacion = "No Publicada";
                    }
                    new Common().actualizarArticuloXEditor(userUsuario, passwordUsuario, rolUsuario, codUnicoPersona, codUnicoArticulo, estadoPublicacion, codUnicoPersona);
                    PrintConsole.o("Su decisión ha sido guardada exitosamente");
                }
            }
            else {
                new Common().registrarDecisionRevisor(codUnicoArticulo, codUnicoPersona);
            }
        }
    }
}
