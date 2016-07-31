/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica;
import Logica_Juego.user_true;
import Logica_Juego.Partida;
import Logica_Juego.top;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
/**
 *
 * @author Juan Carlos
 */
@WebServlet(urlPatterns = {"/comprobar"})
public class comprobar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      PrintWriter out = response.getWriter();

        /*
            String date = (String)request.getParameter("valores");
            out.println(date);
       */
      
        if(request.getParameter("valores").isEmpty()){
             String error = "Aun no ha comenzado a Jugar";
             request.setAttribute("error", error);
             RequestDispatcher dispatcher = request.getRequestDispatcher("sudoku.jsp");
             dispatcher.forward(request,response);              
        }
      
        String[] interests = request.getParameterValues("valores");
        int datos[][] = new int[9][9];
        int cont=0;
        for (int t = 0 ; t < 9 ; t++){
            for(int i = 0; i < 9; i++){
                if(interests[cont].isEmpty()){
                    interests[cont]="0";
                }
                try{ 
                datos[t][i]  = Integer.parseInt(interests[cont]);
                }catch(NumberFormatException e){ 
                    String error = "Has insertado un numero no valido";
                    request.setAttribute("error", error);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("sudoku.jsp");
        dispatcher.forward(request,response);  
                }
                cont++;
            }
        }
        Partida res = new Partida();
        res.setTaulaReq(datos);
        res.setRespuestas(res.comprueba());
        request.setAttribute("res", res);

        
        if(res.comprueba()==0){        
            top nuevoTop = new top();
            nuevoTop.setUsuario(request.getParameter("user1"));
            nuevoTop.setPuntuacion(request.getParameter("result"));

            nuevoTop.agregar_puntuacion();
        }
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
