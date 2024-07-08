package com.proyecto.dao;

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
    
    public Usuario(String user, String password, String rol, String codUnicoPersona){
        this.user =user;
        this.password = password;
        this.rol = rol;
        this.codUnicoPersona = codUnicoPersona;
    }
    //Métodos abstractos que van a ser implementados en las clases hijas
    public abstract void generarCorreo(String articulo, String correo, String estado);

    public abstract void decidirSobreArticulo(Articulo articulo, String estadoPublicacion, String codUnicoEditor);
    


}