package com.proyecto.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * La clase Mail proporciona métodos para inicializar el sistema de correo y enviar correos electrónicos.
 * Utiliza la librería jakarta.mail para el envío de correos electrónicos y dotenv para cargar las credenciales desde un archivo de propiedades.
 * 
 * @version 1.0
 * @since 2024-07-14
 */
public class Mail {
    private static String correo;
    private static String clave;
    private static String host;
    private static String puerto;
    private static Properties properties;
    private static Session session;

    /**
     * Inicializa el sistema de correo cargando los datos, 
     * configurando las propiedades y creando la sesión.
     */
    public static void inicializarSistemaCorreo() {
        cargarDatos();
        cargarPropiedades();
        crearSesion();
    }

    /**
     * Obtiene la dirección de correo electrónico 
     * utilizada para enviar los correos.
     * 
     * @return La dirección de correo electrónico.
     */
    public static String getCorreo() {
        return correo;
    }

    /**
     * Carga las credenciales y configuraciones del archivo credenciales.properties.
     */
    private static void cargarDatos() {
        URL urlCredencialesProperties  = Mail.class.getClassLoader().getResource("tmp");
        String pathArchivo = urlCredencialesProperties.getPath();
        Path path = new File(pathArchivo).toPath();
        Dotenv dotenv = Dotenv.configure().directory(path.toString()).filename("credenciales.properties").load();
        correo = dotenv.get("CORREO");
        clave = dotenv.get("CLAVE");
        host = dotenv.get("HOST");
        puerto = dotenv.get("PUERTO");
    }

    /**
     * Configura las propiedades necesarias para la conexión SMTP.
     */
    private static void cargarPropiedades() {
        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.port", puerto);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.socketFactory.port", puerto);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    /**
     * Crea una sesión de correo electrónico utilizando las propiedades configuradas y las credenciales.
     */
    private static void crearSesion() {
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correo, clave);
            }
        });
    }

    /**
     * Envía un correo electrónico con el destinatario, asunto y mensaje especificados.
     * 
     * @param destinatario La dirección de correo electrónico del destinatario.
     * @param asunto El asunto del correo electrónico.
     * @param mensaje El mensaje del correo electrónico.
     * @return Un mensaje indicando el resultado del envío del correo.
     */
    public static String sendMail(String destinatario, String asunto, String mensaje) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
            return "Correo enviado correctamente a " + destinatario;
        } catch (MessagingException e) {
            return "Error al enviar correo a " + destinatario + ": " + e.getMessage();
        }
    }
}
