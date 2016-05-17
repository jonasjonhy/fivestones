/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Mundo;

/**
 *
 * @author arjc & ropoman
 */
public class Ficha {
    private int x;
    private int y;
    private boolean estado;
    private String dirImagen;
    
    /**
     * Constructor vacio de la clase
     */
    public Ficha(){
        
    }
    
    /**
     * Constructor de la clase Ficha asigna la poscion recibida, y por defecto se asigna
     * false al estado.
     * @param x int que contiene el indice en el eje horizontal de la posicion de la Ficha
     * @param y int que contiene el indice en el eje vertical de la posicion de la Ficha
     */
    public Ficha(int x, int y){
        this.x=x;
        this.y=y;
        this.estado=false;
    }
        
    /**
     * Asigna una direccion de imagen correspondiente al jugador que la selecciona, asigna al
     * estado el valor de true, activando asi la ficha
     * @param dirImagen cadena que almacena la direccion de la imagen que se asignara a la Ficha.
     */
    public void activar(String dirImagen){
        this.estado=true;
        this.dirImagen=dirImagen;
    }
    /**
     * Permite obtener el valor del estado de la Ficha.
     * @return Devuelve el valor del atributo estado.
     */
    public boolean isActiva() {
        return estado;
    }

    /**
     * Permite modificar el valor del estado de la Ficha.
     * @param estado boolean con el nuevo valor que se asignara al estado.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la direccion de la imagen que contiene la Ficha
     * @return Cadena con la direccion de la imagen.
     */
    public String getDirImagen() {
        return dirImagen;
    }
    /**
     * Obtiene el indice en el eje horizontal de la posicion de la Ficha
     * @return devuelve un int que contiene el indice horizontal.
     */
    public int getX() {
        return x;
    }
    /**
     * Obtiene el indice en el eje vertical de la posicion de la Ficha
     * @return devuelve un int que contiene el indice vertical.
     */
    public int getY() {
        return y;
    }
    /**
     * Genera una cadena con los atributos de la poscion de la Ficha
     * @return String identificando el indice horizontal y vertical de la Ficha.
     */
    @Override
    public String toString(){
        return("la pos en x es:"+this.x+" la pos en y es:"+this.y);
    }
}
