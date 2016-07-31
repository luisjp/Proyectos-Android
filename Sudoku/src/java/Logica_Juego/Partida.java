/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Juego;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Juan Carlos
 */
public class Partida implements Serializable{
    private int[][] taula = {
            {6, 3, 2, 7, 8, 1, 9, 4, 5},
            {4, 8, 7, 5, 9, 6, 2, 1, 3},
            {5, 1, 9, 2, 4, 3, 8, 7, 6},
            {8, 6, 4, 3, 5, 2, 7, 9, 1},
            {7, 5, 1, 9, 6, 8, 3, 2, 4},
            {2, 9, 3, 1, 7, 4, 6, 5, 8},
            {9, 4, 5, 6, 3, 7, 1, 8, 2},
            {1, 7, 6, 8, 2, 5, 4, 3, 9},
            {3, 2, 8, 4, 1, 9, 5, 6, 7}
        };
    
    private int respuestas ;
    
    private int[][] taulaReq;

    
    
    
    public void setRespuestas(int respuestas) {
        this.respuestas = respuestas;
        
    }

    public int getRespuestas() {
        return respuestas;
    }
    
    public int[][] getTaula() {
        return taula;
    }   

    public void setTaulaReq(int[][] taulaReq) {
        this.taulaReq = taulaReq;
    }

    public int[][] getTaulaReq() {
        return taulaReq;
    }

    public int comprueba(){
        int errores = 0;
        //int errors = equal(getTaula(), getTaulaReq());
            for (int i = 0; i < 9; i++) {
                for(int t = 0 ; t < 9 ; t ++){
                    
                    if(taula[i][t] != taulaReq[i][t]){
                        errores++;
                    }
                    
                }

            }      
        return errores;
    }

 
    


    
}

