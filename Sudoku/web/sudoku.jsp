
     <% 
        if(session.getAttribute("usuario")==null){
             
             response.sendRedirect(this.getServletContext().getContextPath() + "/noLogueado.jsp");
        }

    %>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error_sudoku.jsp" %>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="Logica_Juego.user_true"%>
<%@page import="Logica_Juego.Partida"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="favicon.ico" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A Jugar Sudoku !</title>
        <script src="jquery-2.0.3.min.js"></script>
        <script src="scriptSudoku.js"  type="text/javascript"></script>
        <link type = "text/css" href="style-sudoku.css" rel="stylesheet"/>
        
    </head>
    <body>
        
        <jsp:useBean id="persona" scope="session" class="Logica_Juego.user_true" />

        <%--<jsp:useBean id="tablas" class="Logica_Juego.tabla" /> --%>
        <jsp:setProperty name="persona"  property="*"/>
      
        <%--<jsp:setProperty name="tablas" property="*"/>--%>
        <div id="container">

            <div id="header">
                <H1> EL SUDOKU</h1>
            </div>
        
     
            <div id="control" class="control">
                <h2>CONTROL</h2>
               
                <p class="control-text">Bienvenido: ${persona.user} </p>
                <p class="control-text">Errores: ${res.respuestas}                    
                    <% 
                    String variable = (String) request.getAttribute("error");
                    if(variable==null){
                        out.println(" ");
                    }else{
                        out.println(variable);
                    }

                    %> </p>
                <br>
                <p class="control-text"><a href="logout">Desconectar</a></p>
            </div>
            
                <form class="form2" action="comprobar" method="Post" >
                    
                <input type="hidden" id="user1" name="user1" value="${persona.user}" />
                <input type="hidden" value="" id="result" name="result"  />
                
                <table  border="0" cellspacing="0" cellpadding="0">
                <tbody >
                
                <tr>
                    <td><%-- ${tablas.taula[0][0]} --%>
                        <input  maxlength="1" id="val" name="valores" type="text" value="${taulass.taula[0][0]}" autofocus readonly/>
                    </td>
                    <td>
                        <input maxlength="1" type="text" name="valores" value="${taulass.taula[0][1]}" autofocus readonly/>
                    </td>
                    <td class="col">
                    <input maxlength="1" type="text" name="valores"  autofocus />
                    </td>
                    <td>
                    <input maxlength="1" type="text" name="valores" autofocus />
                    </td>
                    <td>
                    <input maxlength="1" type="text" name="valores" value="${taulass.taula[0][4]}" autofocus readonly/>
                    </td>
                    <td class="col">
                    <input maxlength="1" type="text" name="valores"  autofocus />
                    </td>
                    <td>
                    <input maxlength="1"  type="text" name="valores"  value="${taulass.taula[0][6]}" autofocus readonly />
                    </td>
                    <td>
                    <input maxlength="1"  type="text" name="valores"  value="${taulass.taula[0][7]}" autofocus readonly/>
                    </td>
                    <td>
                    <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
               </tr>
               <tr>
                    <td>
                    <input maxlength="1"  type="text" name="valores" value="${taulass.taula[1][0]}" autofocus readonly/>
                    </td>
                    <td>
                    <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[1][3]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[1][4]}" autofocus readonly/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[1][5]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
               </tr>
               <tr>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[2][1]}" autofocus readonly/>
                    </td >
                    <td style="border-right: 2px solid #000000;" class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[2][2]}" autofocus readonly/>
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td style="border-right: 2px solid #000000;" class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[2][7]}" autofocus readonly/>
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
               </tr>
               <tr>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[3][0]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[3][4]}" autofocus readonly/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[3][6]}" autofocus readonly />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[3][8]}" autofocus readonly/>
                    </td>
               </tr>
               <tr>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[4][0]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[4][3]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[4][5]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[4][8]}" autofocus readonly/>
                    </td>
               </tr>
               <tr>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[5][0]}" autofocus readonly/>
                    </td>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[5][1]}" autofocus readonly/>
                    </td>
                    <td style="border-right: 2px solid #000000;" class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[5][4]}" autofocus readonly/>
                    </td>
                    <td style="border-right: 2px solid #000000;" class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td class="bord">
                     <input maxlength="1"  type="text" name="valores"  value="${taulass.taula[5][6]}" autofocus readonly/>
                    </td>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td  class="bord">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[5][8]}" autofocus readonly/>
                    </td>
               </tr>
               <tr>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[6][1]}" autofocus readonly/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[6][4]}" autofocus readonly />
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[6][6]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[6][7]}" autofocus readonly/>
                    </td>
                    <td >
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
               </tr>
               <tr>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[7][0]}" autofocus readonly />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores"/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[7][3]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[7][4]}" autofocus readonly/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[7][5]}" autofocus readonly/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[7][8]}" autofocus readonly/>
                    </td>
               </tr>
               <tr>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[8][1]}" autofocus readonly />
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[8][4]}" autofocus readonly/>
                    </td>
                    <td class="col">
                     <input maxlength="1"  type="text" name="valores"  autofocus/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" />
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[8][7]}"/>
                    </td>
                    <td>
                     <input maxlength="1"  type="text" name="valores" value="${taulass.taula[8][8]}"/>
                    </td>
               </tr>
               </table>

               <div id="Comenzar" ><input style="cursor:pointer;" align="middle" type="button" id="iniciar"  name="Comenzar" value="Comenzar"></div>
               <div id="comprobar"><input style="cursor:pointer;"  align="middle" id="comprobacion" type="submit" name="comprobar" value="Comprobar"></div>
            </form>
                    <div class="tiempo"><span id="minutos">0</span>:<span id="segundos">0</span></div>  
                    
            <div id="top" class="top" >
                <h3>TOP 5</h3>
                <%--<c:forEach var="ds" items="${.}}"> --%>
                <table class="top5" border="0">
                    
                <% 
                    String sCadena = "";
                    String name ="";
                    String punt = "";
                    
                    int t=0;
                    BufferedReader bf =new BufferedReader(new FileReader("C:\\Users\\luisjp\\Documents\\NetBeansProjects\\Sudoku\\users\\top.txt"));
                    while((sCadena = bf.readLine()) != null  ) {
                        int i = 0;
                        String [] sep = sCadena.split(" ");
                        name = sep[i];
                        i++;
                        punt = sep[i];
                        t++;
                       %>
                       <tr>
                           <td><%= name %> </td><td><%= punt %> </td>
                       </tr>
                  <%
                    }

                  %>
                </table>
            </div>
            
                    
            
        
        </div>
    </body>
</html>
