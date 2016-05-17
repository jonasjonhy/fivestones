/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ufps.Mundo.Ficha;

/**
 *
 * @author Rosemberg
 */
public class TableroDeJuego extends JPanel {
private FormTablero panelDeJuego;
private boolean atributo=false;
private String dirFondo="";
    
    public TableroDeJuego(){
    super();
    this.setSize(300, 300);
    
}

    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        pintarFondo(g);
        
        if(atributo==false)
        return;
        g.setColor(Color.WHITE);
        pintarTablero(g);
        
        pintarCuadros(g);
        g.setColor(Color.BLACK);
    }
    
    public void setDirFondo(String dir){
        this.dirFondo=dir;
    }

    public void setAtributo(boolean atributo) {
        this.atributo = atributo;
    }
    
    public void setPanelDeJuego(FormTablero panel){
        this.panelDeJuego=panel;
    }
    
    public void pintarTablero(Graphics g){
        
        int val=this.getHeight()/10;
        System.out.println(val+" Valor");
        
        int xIni=val;
        int yIni=0;
        int xFin=val;
        int yFin=0;
        
        for( int x=0; x<9; x++){

        g.drawLine(xIni, yIni, xFin, this.getHeight());
        xIni+=val;
        xFin+=val;
        
        }
         
        xIni=xFin=0;
        yIni=yFin=val;
        
        for( int x=0; x<9; x++ ){
            
        g.drawLine(xIni, yIni, this.getHeight(), yFin);
        yIni+=val;
        yFin+=val;
            
        }
        
    }

    private void pintarCuadros(Graphics g) {
        int i=0; 
        int j=0;
        Ficha[][]myFichas=this.panelDeJuego.getMyJuego().getMyFichas();
        for(Ficha x[]: myFichas){
            for(Ficha fichita: x){
                if(fichita.isActiva()){
                    pintarCuadro(fichita, g);
                }
                j++;
            }
            j=0;
            i++;
        }
    }

    private void pintarCuadro(Ficha fichita, Graphics g) {
             
       ImageIcon ima= new ImageIcon(this.panelDeJuego.getMyJuego().buscarJugador(fichita.getDirImagen()).getImaFicha());
       
       g.drawImage( ima.getImage() ,fichita.getX()+1 , fichita.getY()+1 , null);
    }

    private void pintarFondo(Graphics g) {
        if(dirFondo.isEmpty())
            return;
        ImageIcon ima= new ImageIcon(this.dirFondo);
        g.drawImage(ima.getImage(), 2, 2, this);
    }

}