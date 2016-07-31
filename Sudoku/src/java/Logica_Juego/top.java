/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Juego;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Juan Carlos
 */
public class top implements Serializable{
    private String puntuacion;
    private String usuario;


    public top(){
        
    }
    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

     public boolean agregar_puntuacion() throws IOException{
        
        File archivo = new File("C:\\Users\\luisjp\\Documents\\NetBeansProjects\\Sudoku\\users\\top.txt");

        //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
        
        FileWriter escribir = new FileWriter(archivo,true);

        //Escribimos en el archivo con el metodo write
        escribir.write(getUsuario() + " " + getPuntuacion() + " \r\n");
        
        escribir.close();
        return true;
 
    }
}
