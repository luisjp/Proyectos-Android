/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Juan Carlos
 */
public class Jugador implements Serializable{
    private String name;
    private String pass;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Jugador(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public boolean verifica_pass(String pass){
        if(pass.length()>6){
            return true;
        }else{
           return false; 
        }
     
    }
  
    public boolean verifica_user(String us) throws FileNotFoundException, IOException{
        String cad="";
        String nombre="";
        
        BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\luisjp\\Documents\\NetBeansProjects\\Sudoku\\users\\usuarios.txt"));
        while((cad = bf.readLine()) != null  ) {
            int i = 0;
            String [] seps = cad.split(" ");
            nombre = seps[i];
            if(us.equals(nombre)){
                return false;
               
            }
                
        }
        return true;
    }
        
    
    
    
    public boolean agregar_usuario() throws IOException{
        File archivo = new File("C:\\Users\\luisjp\\Documents\\NetBeansProjects\\Sudoku\\users\\usuarios.txt");

        //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
        FileWriter escribir=new FileWriter(archivo,true);

        //Escribimos en el archivo con el metodo write
        escribir.write(getName() + " " + getPass() + " " + getEmail() + " \r\n");
        
        escribir.close();
        return true;
 
    }
    
}
