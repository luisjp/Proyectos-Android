/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import Logica_Juego.user_true;
import Login.Jugador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Carlos
 */
@WebServlet(urlPatterns = {"/entrar"})
public class entrar extends HttpServlet {


    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
        try { 
            
            String usr = req.getParameter("user");
            String pass = req.getParameter("pass");
          
            boolean entrada=false;
            String name="";
            String passSaved="";
            

            BufferedReader bf =new BufferedReader(new FileReader("C:\\Users\\luisjp\\Documents\\NetBeansProjects\\Sudoku\\users\\usuarios.txt"));
            String sCadena = "";
            while((sCadena = bf.readLine()) != null) {
                int i = 0;
                String [] split = sCadena.split(" ");
                name = split[i];
                i++;
                passSaved = split[i];
                
                if(name.equals(usr) && passSaved.equals(pass)){
                    entrada=true;
                    break;
                    
                }
                
            }
            
            
            if(entrada){
                HttpSession session = req.getSession();
                 user_true persona = new user_true();
                 persona.setUser(usr);
                 
                 session.setAttribute("usuario", persona);
                 
                 RequestDispatcher dispatcher = req.getRequestDispatcher("sudoku.jsp");
                 dispatcher.forward(req,resp); 
                 
                 
            }else{
                String error_log = "Login Incorrecto, vuelva a intentarlo";
                req.setAttribute("error_log", error_log);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req,resp);
               
            }
         } catch (Exception e) {
               e.printStackTrace();
         } finally {
            out.close();
         }
    }    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            
    }

}
