/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.backEnd;

import java.io.Serializable;

/**
 *
 * @author erick
 */
public class Usuario implements Serializable{
    private String Nombre;
    private String Contraseña;
    private int Rol;
    // rol : 1 admin
           //2 usuario normal

    public int getRol() {
        return Rol;
    }

    public void setRol(int Rol) {
        this.Rol = Rol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
        
    public Usuario(int Rol,String Nombre, String Contraseña){
        this.Rol=Rol;
        this.Nombre = Nombre;
        this.Contraseña= Contraseña;        
    }
    public Usuario(){}
    
            
}
