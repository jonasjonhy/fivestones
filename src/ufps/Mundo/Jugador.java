/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Mundo;

/**
 *
 * @author arjc & ropoman
 */
public class Jugador {
    
    
    private String nombre;
    private byte puntos;
    private String imgFicha;
    
    /**
     * Constructor vacio de la clase
     */
    public Jugador() {
    }

    /**
     * Constructor de la clase recibe los atributos de nombre e imagen pertenecientes al
     * jugador y se los asigna.
     * @param nombre cadena que contiene el nombre del jugador.
     * @param dir cadena que contiene la direccion de la imagen que selecciono el jugador
     */
    public Jugador(String nombre , String dir) {
        this.nombre=nombre;
        
        this.imgFicha = dir;
        
    }
    
    /**
     * Obtiene la direccion de la imagen correspondiente al jugador.
     * @return Devuelve una cadena que contiene la direccion de imagen
     */
    public String getImaFicha() {
        return imgFicha;
    }
    /**
     * Obtiene el nombre del jugador
     * @return cadena con el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Permite modificar el nombre del jugador, asignandole uno nuevo
     * @param nombre cadena con el nuevo nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene los puntos del jugador en el momento.
     * @return byte con la cantidad de puntos que tiene el jugador.
     */
    public byte getPuntos() {
        return puntos;
    }
    
    /**
     * Permite modificar la cantidad de puntos pertenecientes al jugador.
     * @param puntos byte con la nueva cantidad de puntos que se le asignara al jugador.
     */
    public void setPuntos(byte puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
  }
