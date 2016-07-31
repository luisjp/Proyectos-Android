
    
    window.onload=function(){
     
        if(document.getElementById("val").value>0){
            carga();
        }
        
        var comprobar = document.getElementById("comprobacion");
        comprobar.addEventListener("click",function(){
            detenerse();
            
        },false);
     
        
        document.getElementById('iniciar').addEventListener("click", function(){
            location.href = "comenzar";
            
        }, false); 
    };




var cronometro;

function detenerse()
{
    
    var min = document.getElementById("minutos").innerHTML;
    var seg = document.getElementById("segundos").innerHTML;
    var result = document.getElementById("result");
    result.value = min + seg;
    
    clearInterval(cronometro);

}


function carga()
{

    contador_s =00;
    contador_m =00;
    s = document.getElementById("segundos");
    m = document.getElementById("minutos");

    cronometro = window.setInterval(
    function(){
        if(contador_s==60)
        {
            contador_s=00;
            contador_m++;
            m.innerHTML = contador_m;

            if(contador_m==60)
            {
                contador_m=00;
            }
        }

        s.innerHTML = contador_s;
        contador_s++;

    },1000);

}


   
    


