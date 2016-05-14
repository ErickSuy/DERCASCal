/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.backEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class InicioSesion {
    String archivo ="sysUser.dat";
    public ArrayList<Usuario> lista;
    
    public InicioSesion(){
        lista=cargarLista(archivo);
        recorrer();
    }
    
    
    private void recorrer(){
        if(lista!=null){
           for ( Usuario usr : lista ) {
               System.out.println(usr.getNombre()+ " --- "+ usr.getContraseña());
           }
        }
      
    }
    
    
    public boolean buscarUsuario(String Nombre, String Pass){
        if(lista!=null){
           for ( Usuario usr : lista ) {
               if(usr.getNombre().equals(Nombre) && usr.getContraseña().equals(Pass))
                   return true;
           } 
        }
        return false;
    }
    
    public int registraUsuario(String nombre, String pass1, String pass2){
        /*
        * 1 - si se registro bien
        * 2 - si las contraseñas no coinciden
        * 3 - si el limite de registro esta completo
        * 4 - lista no existe
        */
        if(lista!=null){
            if(lista.size()<=2){
                if(pass1.equals(pass2)){
                    lista.add(new Usuario(2,nombre,pass1));
                    this.guardarLista();
                }
                return 2;
            }
            return 3;
        }
        return 4;
    }
    
    public boolean guardarLista(){
               
           try {
            ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(new File(archivo)));
            ficheroSalida.writeObject(lista);
            ficheroSalida.flush();
            ficheroSalida.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error al grabar personas: El fichero " + archivo + " no existe. ");
            return false;
        } catch (IOException ioe) {
            System.out.println("Error: Falló la escritura de las personas en el fichero" + archivo + ". dat");
            return false;
        }
        return true;
        
    }
    
     public ArrayList<Usuario> cargarLista(String ficheroPath){
        ArrayList<Usuario> lista = null;
        File archivo = new File (ficheroPath);
        try {
            ObjectInputStream ficheroEntrada = new ObjectInputStream(new FileInputStream(archivo));
            lista = (ArrayList) ficheroEntrada.readObject();
            ficheroEntrada.close();
            if (lista == null) {                
                lista = new ArrayList();
                lista.add(new Usuario(1,"Administrador","123"));
                System.out.println("datos cargados con Exito");
            } else {
               System.out.println("datos cargados con Exito");
            }
            return lista;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
            return null;
        } catch (FileNotFoundException fnfe) {
            this.guardarLista();
            this.cargarLista(ficheroPath);
            System.out.println(fnfe.getMessage());
            return null;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return null;
        }
    }
}
    
   
