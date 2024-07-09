package com.proyecto.dao;

    /**
     * La clase "Usuario" definida como abstract sirve para generar 
     * variables y metodos abstractos que puedan implementar sus clases hijas.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public abstract class Usuario {
    
    protected String codUnicoPersona;
    protected String user;
    protected String password;
    protected String rol;

    //Getters y setters
    public String getCodUnicoPersona() {
        return codUnicoPersona;
    }
    public void setCodUnicoPersona(String codUnicoPersona) {
        this.codUnicoPersona = codUnicoPersona;
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
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    /**
     *  Constructor para crear un objeto Usuario
     * 
     * @param user El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param rol El rol del usuario.
     * @param codUnicoPersona El código único de la persona asociada al usuario.
     */
    public Usuario(String user, String password, String rol, String codUnicoPersona){
        this.user =user;
        this.password = password;
        this.rol = rol;
        this.codUnicoPersona = codUnicoPersona;
    }
      
    //Métodos abstractos que van a ser implementados en las clases hijas
    
    /**
     * Genera un correo relacionado con un artículo
     * 
     * @param articulo El artículo relacionado
     * @param correo La direccion de correo electrónico a la que se enviará.
     * @param estado El estado del correo.
     */
    public abstract void generarCorreo(String articulo, String correo, String estado);

    /**
     * Decide sobre la publicación de un artículo.
     * 
     * @param articulo El artículo en cuestión.
     * @param estadoPublicacion El estado de la publicación.
     * @param codUnicoEditor  El código único del editor que toma la decisión.
     */
    public abstract void decidirSobreArticulo(Articulo articulo, String estadoPublicacion, String codUnicoEditor);
    


}