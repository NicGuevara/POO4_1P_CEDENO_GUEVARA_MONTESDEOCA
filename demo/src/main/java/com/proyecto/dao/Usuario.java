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

    private String codUnicoPersona;
    private String user;
    private String password;
    private String rol;

    //Getters y setters
    
    /**
     * Obtiene el código único persona.
     * 
     * @return El código único persona.
     */
    public String getCodUnicoPersona() {
        return codUnicoPersona;
    }
    
    /**
     * Establece el código único persona.
     * 
     * @param codUnicoPersona El código único persona.
     */
    public void setCodUnicoPersona(String codUnicoPersona) {
        this.codUnicoPersona = codUnicoPersona;
    }
    /**
     * Obtiene el nombre del user del usuario.
     *
     * @return El user del usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre del user del usuario.
     *
     * @param user El nombre del user del usuario.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Obtiene el rol del usuario.
     * 
     * @return El rol del Usuario.
     */
    public String getRol() {
        return rol;
    }
    
    /**
     * Establece el rol del usuario.
     * 
     * @param rol El rol del Usuario.
     */
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