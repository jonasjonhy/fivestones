/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arjc
 */
public class Temporizador extends Thread {
    private FormTablero myJuego;
    private Graphics g;
    private int segundos;
    private Color texto;
    private Color fondo;
    private boolean activado=true;
    
    public Temporizador(){
        
    }
    
    public Temporizador(Graphics g, int segundos, Color texto, Color fondo, FormTablero myJuego){
        this.g=g;
        this.segundos=segundos;
        this.texto=texto;
        this.fondo=fondo;
        this.myJuego=myJuego;
    }
    
    
    public void cambiarEstado(){
        if(activado)
        this.activado=false;
        else
            activado=true;
    }
    
    public void cambiarDato(byte x){
        this.segundos=x;
    }
    
    public void realizarConteo(){
        int x= segundos;
        String val="";
        this.myJuego.setEstado(false);
        while(activado){
            val="";
            if(segundos/10==0)
                    val="0";
            
                    this.limpiar();
                    g.setColor(texto);
                    g.drawString("00:"+val+segundos--+"", 60 ,20);

                    
            if(segundos<0 ){
                    this.myJuego.setEstado(false);
                    this.limpiar();
                    g.setColor(texto);
                    g.drawString("Tiempo terminado", 20 ,20);
               
        int valX;
        int valY;
        
             do{
                 Random numRandom=new Random();
                   valX=numRandom.nextInt(10);
                   valY=numRandom.nextInt(10);
                 
             }while(this.myJuego.isActivo(valX, valY));
             
             this.myJuego.comprobarMovimiento(valX, valY, true);
             this.segundos=x;
             this.myJuego.setEstado(true);
             this.destroy();
            }
         
         this.dormir(1);
     }
        
   }
        
    
    
    private void dormir(int val){
        try {
            Thread.sleep(val*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpiar(){
        g.setColor(fondo);
        g.fillRect(5, 5, 130, 20);
    }
    
    @Override
    public void run(){
    
        realizarConteo();
        
    }
}
