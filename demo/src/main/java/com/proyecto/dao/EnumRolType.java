package com.proyecto.dao;

public enum EnumRolType {

    R("REVISOR", "Revisión de Artículo"),
    E("EDITOR", "Registro de decisión final sobre artículo");

    private final String rol;
    private final String tarea;

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
