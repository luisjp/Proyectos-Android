/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Login.Jugador;

/**
 *
 * @author Juan Carlos
 */
@WebServlet(urlPatterns = {"/registrar"})
public class registrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Jugador newPersona = new Jugador(request.getParameter("new-user"), request.getParameter("new-pass"),
                request.getParameter("new-email"));
        
        if(newPersona.verifica_pass(request.getParameter("new-pass")) && newPersona.verifica_user(request.getParameter("new-user"))){
            if(newPersona.agregar_usuario()){
               RequestDispatcher dispatcher = request.getRequestDispatcher("registrat.jsp");
               dispatcher.forward(request,response);            
           } 
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request,response);  
        }          
        


    }

        
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
