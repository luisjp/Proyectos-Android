<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
         <link rel="shortcut icon" href="favicon.ico" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sudoku</title>
        <script src="jquery-2.0.3.min.js"></script>
        <script src="script.js"  type="text/javascript"></script>
         <link type = "text/css" href="style.css" rel="stylesheet"></link>
    </head>
    <body>
        <a href="#" id="mostrar"><center>DESCRIPCION</center></a>
        <div id="aprende">
            <p>El objetivo del sudoku es rellenar una cuadrícula de 9 × 9 celdas (81 casillas) dividida en subcuadrículas de 3 × 3 (también llamadas "cajas" o "regiones") con las cifras del 1 al 9 partiendo de algunos números ya dispuestos en algunas de las celdas.  Lo que importa, es que sean nueve elementos diferenciados, que no se deben repetir en una misma fila, columna o subcuadrícula. Al confirmarse el login, se mostrara por pantalla la tabla de sudoku, para comenzar a jugar, hacemos Click al Boton "Comenzar". Si se ha terminado, hacemos click en "Comprobar", que mostrara los errores que ha habido o de lo contrario dara 0 errores y se añadira al top5</p>
        </div>
        <div id="container">
           
            <div id="header">
                <H1> EL SUDOKU</h1>
            </div>
            
            <div id="entrada" > 
                 
                <div id="foto1">
                    <img  class="fotos" src="1.jpg" />
                </div>
                <div id="form">
                    <form class="form" method="Post" action="entrar">
                        <p>Usuario:</p> <input type="text" name="user">
                        <p>Contraseña:</p> <input type="password" name="pass"><br><br>
                        <input class="button" type="submit" name="entrar" value="Entrar"/>
                    </form>
                </div>
                <div id="foto2">
                    <img  class="fotos" src="2.jpg" />
                </div>
                <input class="button" type="button" id="botonpruebafade" value="Registrate Aquí" style="font-size:14px;cursor:pointer;margin:15px;padding:5px;"/>
                <div class="err_log"><p><% 
                    String err = (String) request.getAttribute("error_log");
                    if(err==null){
                        out.println(" ");
                    }else{
                        out.println(err);
                    }
                    %></p></div>
                <div id="divquesemuestra" class="register">
                    <form class="form2" method="GET" action="registrar">
                        <table border="0">
                            <tr>
                                <td>Nombre de Usuario:</td><td><input type="text" size="30" name="new-user"/></td>
                               </tr><tr>
                                   
                               <td>Password:</td><td> <input type="password" size="30" name="new-pass"/></td>
                               </tr><tr>
                                   <td>Correo Electronico:</td><td> <input type="text" size="30" name="new-email"/></td>
                              </tr><tr>
                                  <td>
                                  <input class="button" type="submit" name="registrar" value="Registrar"/>
                                  </td>
                              </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
