/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import Logica_Juego.Partida;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Carlos
 */
@WebServlet(urlPatterns = {"/comenzar"})
public class comenzar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            
            
            Partida taulass = new Partida();
            
            request.setAttribute("taulass", taulass);
                 RequestDispatcher dispatcher = request.getRequestDispatcher("sudoku.jsp");
                 dispatcher.forward(request,response); 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
