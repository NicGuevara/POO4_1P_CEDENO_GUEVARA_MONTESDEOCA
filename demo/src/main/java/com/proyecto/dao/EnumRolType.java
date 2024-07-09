package com.proyecto.dao;


    /**
     * Enumeración que define los tipos de roles y sus respectivas tareas 
     * en el sistema.
     * 
     * @author Nicole Guevara
     * @version 1.0
     * @since 2024-07-07
     */

public enum EnumRolType {

    /**
     * Rol de Revisor, encargado de la revisión de artículos.
     */
    R("REVISOR", "Revisión de Artículo"),
    
    /**
     * Rol de Editor, encargado del registro de la decisión final sobre artículos.
     */
    E("EDITOR", "Registro de decisión final sobre artículo");

    private final String rol;
    private final String tarea;

    /**
     * Constructor para la enumeración EnumRolType.
     * 
     * @param rol El nombre del rol.
     * @param tarea La tarea asociada con el rol.
     */
    EnumRolType(String rol, String tarea) {
        this.rol = rol;
        this.tarea = tarea;
    }

    public String getRol() {
        return rol;
    }

    public String getTarea() {
        return tarea;
    }
}
