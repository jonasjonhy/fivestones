/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arjc & ropoman
 */
public class Lapiz extends Thread {
    
    private Graphics punto;
    private int tamGeneral;
    private boolean tipo;
    private FormTablero pnl;
    
   public Lapiz(Graphics g, int lienzo, boolean tipo, FormTablero pnl){
       this.punto=g;
       this.tipo=tipo;
       this.tamGeneral= lienzo;
       this.pnl=pnl;
       this.punto.setColor(Color.WHITE);
   }
   
   private void dibujarVertical(){
       int val=this.tamGeneral/10;
       //  System.out.println(val);
       int xIni=val;
        int yIni=0;
        int xFin=val;
        int yFin=0;
    
        for( int x=0; x<9; x++){
        
        dibujarHaciaAbajo(xIni, yIni, xFin, yFin, this.tamGeneral);
        
        xIni+=val;
        xFin+=val;
        
        }
   }
    
   private void dibujarHaciaAbajo(int xIni, int yIni, int xFin, int yFin, int tam){
        
        for( int x=0; x<tam; x++){
            this.punto.drawLine(xIni, yIni, xFin, yFin+x);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(FormTablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
   private void dibujarHorizontal(){
        int val=this.tamGeneral/10;
           
        int xIni=0;
        int yIni=val;
        int xFin=0;
        int yFin=val;

       
        for( int x=0; x<9; x++ ){
            
        dibujarHaciaLado(xIni, yIni, xFin, yFin, this.tamGeneral);
        
        yIni+=val;
        yFin+=val;
            
        }
       }
       
   private void dibujarHaciaLado(int xIni, int yIni, int xFin, int yFin, int tam){
        
        for( int x=0; x<tam; x++){
            this.punto.drawLine(xIni, yIni, xFin+x, yFin);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(FormTablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
   
    @Override
   public void run(){
        
        if(tipo)
            dibujarVertical();
        else{
            dibujarHorizontal();
            this.pnl.iniciarPartida();
        }

   }
}
