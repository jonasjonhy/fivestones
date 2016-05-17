/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Mundo;

/**
 *
 * @author arjc & ropoman
 */
public class Fivestones {
    
    private Ficha[][]myFichas;
    private Jugador jugador1;
    private Jugador jugador2;
    private int cant;
   
    /**
     * Constructor de la clase Fivestones recibe los nombres y las direcciones
     * de las imagenes respectivos a las fichas de cada jugador
     * 
     * @param nombre1 cadena con el nombre del jugador uno
     * @param nombre2 cadena con el nombre del jugador dos
     * @param ima1 cadena que almacena la direccion de la imagen seleccionada por el jugador 1
     * @param ima2 cadena que almacena la direccion de la imagen seleccionada por el jugador 2
     */
    public Fivestones(String nombre1, String nombre2, String ima1, String ima2) {
        
        this.jugador1 = new Jugador(nombre1 ,getImage(ima1));
        this.jugador2 = new Jugador(nombre2,getImage(ima2));
        this.myFichas=new Ficha[10][10];
            
    }
    
    /**
     * Método que asigna los respectivos objetos de tipo Ficha a la matriz
     * crea 100 objetos de tipo Ficha y los asigna a cada posición de la matriz
     * teniendo en cuenta el tamaño del Jpanel donde se van a pintar los objetos
     * de tipo Ficha
     * @param tam int que contiene el largo del lienzo dibujante
     */
    public void llenarMatrizFicha(int tam){
        int x=0, y=0;
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++ ){
                
                myFichas[i][j]=new Ficha(y, x);
                
                x+=tam/10;
            }
            y+=tam/10;
            x=0;
        }

    }
   
    /**
     * Contiene la lógica  principal del juego permite recibir de la GUI la posición de la
     * ficha seleccionada por el usuario y la dirección de la imagen respectiva al jugador 
     * que tiene el turno y devolver un byte correspondiente a diferentes situaciones.
     * @param dirImagen cadena que almacena la dirección de la imagen respectiva al jugador 
     * que tiene el turno.
     * @param fila int que contiene el índice en el eje horizontal de la posición de la Ficha
     * @param columna int que contiene el índice en el eje vertical de la posición de la Ficha
     * @return Devuelve un byte dependiendo del proceso que corresponda a los datos recibidos, ejemplo:
     * En caso de que reciba la posición recibida sea [-1][-1] se identificara el jugador que se retiro
     * por medio de la cadena dirImagen, y se le otorgaran los puntos al jugador contrario; devolviendo
     * el valor 0.
     * En caso de que se presente un ganador, se identificara a dicho jugador como en el caso anterior 
     * y se le otorgaran los puntos de la partida, se retornara 1 o 2 si el jugador1 o el jugador2 gana 
     * respectivamente.
     * En caso de que la posición recibida fila y columna, corresponda a una posición no "ocupada"
     * se procedera a asignar la Ficha de esa posicion al jugador correspondiente a la dirImagen,
     * el método retornara el valor de 3.
     * En caso de que la posición recibida fila y columna, corresponda a una posición "ocupada"
     * se indicara que no se puede asignar a esa posicion, devolviendo el valor 4.
     * En caso de que la matriz se llene y no se pueda agregar mas fichas, la partida terminara en empate
     * no se le darán puntos a ninguno de los dos jugadores, se retornara el valor 5.
     */
    public byte getJugada(String dirImagen,int fila, int columna){
        
        if(fila == -1 && columna == -1){
           if(dirImagen.equals(this.jugador1.getImaFicha()))
              this.jugador2.setPuntos((byte)(this.jugador2.getPuntos()+10));
           else
              this.jugador1.setPuntos((byte)(this.jugador1.getPuntos()+10));
                     
           return 0;
        }
        
        if(myFichas[fila][columna].isActiva())
            return 4;
               
       cambiarEstado(fila, columna, dirImagen);
              
       if(isGanador(dirImagen,fila,columna)){
           if(dirImagen.equals(this.jugador1.getImaFicha())){
               this.jugador1.setPuntos((byte)(this.jugador1.getPuntos()+10));
               this.restaurarContador();
               return 1;
               
           }else{
           this.jugador2.setPuntos((byte)(this.jugador2.getPuntos()+10));
           this.restaurarContador();
               return 2;
           }
       }
       
       if(this.cant==100){
           this.vaciarMatriz();
           this.restaurarContador();
           return 5;
       }
       
         
          return 3;
    }
    
   /**
     * Valida si al asignar una Ficha en la posición correspondiente se produce
     * un ganador, dado en cualquiera de las posibles alineaciones
     * @param img cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     * @param x int que contiene el índice en el eje horizontal de la posición de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posición de la Ficha
     * @return devuelve true en caso de que se presente una de las alineaciones de 5 Fichas posibles
     * pertenecientes al mismo jugador, retorna false en caso de que no encuentre dicha alineación
     */

    private boolean isGanador(String img,int x, int y){
           
       if(this.vertical(img, x, y)||this.horizontal(img, x, y)||this.diagonal1(img, x, y)||this.diagonal2(img, x, y)){
           this.vaciarMatriz();
           return true;
       }
       return false;
    }
    
   /**
     * Busca si al asignar una Ficha en la posición correspondiente se produce un ganador, de forma
     * vertical, realiza una búsqueda abajo y arriba de la posición, almacenando en una variable
     * local una unidad cada vez que encuentre una Ficha correspondiente al jugador.
     * @param img cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     * @param x int que contiene el índice en el eje horizontal de la posición de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posición de la Ficha
     * @return Un boolean true si la Ficha en la posición recibida hace parte de una alineación
     * de cinco Fichas contiguas verticalmente. De lo contrario retorna false.
     */
    
    private boolean vertical(String img,int x, int y){
        int i=1;
        
        while(i<=4){
           if(y-i>=0 && this.myFichas[x][y-i].isActiva()&& img.equals(this.myFichas[x][y-i].getDirImagen())){
               i++;
             }
           else
               if(y<9 && this.myFichas[x][++y].isActiva()&& img.equals(this.myFichas[x][y].getDirImagen())){
                  i++;
               }else
                return false;
        }
        
        return true;
    }
    
    /**
     * Busca si al asignar una Ficha en la posicion correspondiente se produce un ganador, de forma
     * horizontal, realiza una búsqueda a la derecha e izquierda de la posicion, almacenando en una variable
     * local una unidad cada vez que encuentre una Ficha correspondiente al jugador.
     * @param img cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     * @param x int que contiene el índice en el eje horizontal de la posicion de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posicion de la Ficha
     * @return Un boolean true si la Ficha en la posición recibida hace parte de una alineación
     * de cinco Fichas contiguas horizontalmente. De lo contrario retorna false.
     */

    private boolean horizontal (String img,int x, int y){
        int i=1;
        
      while(i<=4){
           if(x-i>=0 && this.myFichas[x-i][y].isActiva()&& img.equals(this.myFichas[x-i][y].getDirImagen())){
              i++;
             }
           else
               if(x<9 && this.myFichas[++x][y].isActiva()&& img.equals(this.myFichas[x][y].getDirImagen())){
                 i++;
               }
               else
                   return false;
        }
        return true;
    }
   
   /**
     * Busca si al asignar una Ficha en la posicion correspondiente se produce un ganador, de forma
     * diagonal derecha, realiza una búsqueda hacia arriba en el eje y hacia la izquierda 
     * en el eje x, luego hacia abajo en el eje y y hacia la derecha en el eje x, almacenando en una variable
     * local una unidad cada vez que encuentre una Ficha correspondiente al jugador.
     * @param img cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     * @param x int que contiene el índice en el eje horizontal de la posicion de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posicion de la Ficha
     * @return Un boolean true si la Ficha en la posicion recibida hace parte de una alineación
     * de cinco Fichas contiguas de forma diagonal derecha. De lo contrario retorna false.
     */

    private boolean diagonal1(String img,int x, int y){
        int i=1;
         while(i<=4){
           if(x-i>=0 && y-i>=0 && this.myFichas[x-i][y-i].isActiva()&& img.equals(this.myFichas[x-i][y-i].getDirImagen())){
               i++;
           }
           else
               if(x<9 && y<9 &&this.myFichas[++x][++y].isActiva()&& img.equals(this.myFichas[x][y].getDirImagen())){
                i++;
               }
               else
                   return false;
        }
        return true;
    }
    
   /**
     * Busca si al asignar una Ficha en la posicion correspondiente se produce un ganador, de forma
     * diagonal izquierda, realiza una búsqueda hacia la derecha en el eje x y hacia arriba
     * en el eje y luego hacia la izquierda en el eje x y hacia la abajo en el eje y almacenando en una
     * variable local una unidad cada vez que encuentre una Ficha correspondiente al jugador.
     * @param img cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     * @param x int que contiene el índice en el eje horizontal de la posicion de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posicion de la Ficha
     * @return Un boolean true si la Ficha en la posición recibida hace parte de una alineación
     * de cinco Fichas contiguas de forma diagonal izquierda. De lo contrario retorna false.
     */

     private boolean diagonal2(String img,int x, int y){
        int i=1;
         while(i<=4){
           if(x+i<=9 && y-i>=0 && this.myFichas[x+i][y-i].isActiva()&& img.equals(this.myFichas[x+i][y-i].getDirImagen())){
               i++;
           }
           else
               if(x>0 && y<9 &&this.myFichas[--x][++y].isActiva()&& img.equals(this.myFichas[x][y].getDirImagen())){
                 i++;
               }
               else
                   return false;
        }
        return true;
    }
    
    /**
     * Asigna a una Ficha dependiendo de la posición recibida, la dirreccion de la imagen
     * perteneciente al jugador de turno; activa la ficha y aumenta el contador de fichas
     * @param x int que contiene el índice en el eje horizontal de la posicion de la Ficha
     * @param y int que contiene el índice en el eje vertical de la posicion de la Ficha
     * @param dirImagen cadena que almacena la dirección de la imagen respectiva al jugador que tiene el turno
     */
    public void cambiarEstado(int x, int y, String dirImagen){
        myFichas[x][y].activar(dirImagen);
      this.cant++;
    }
    
    /**
     * Devuelve a 0 el contador de fichas, indicando que la cantidad de Fichas activas
     * en la matriz es 0.
     */
    private void restaurarContador(){
        this.cant=0;
    }
    
    /**
     * Obtiene un objeto de la clase Ficha apartir de sus atributos de posición x & y
     * @param x int que contiene el indice en el eje horizontal de la posición de la Ficha
     * @param y int que contiene el indice en el eje vertical de la posición de la Ficha
     * @return Retorna el objeto Ficha de la matriz de Ficha, correspondiente a la
     * poscion recibida
     */
    public Ficha getFicha(int x, int y){
        return this.myFichas[x][y];
    }
    /**
     * Getter del atributo jugador1
     * @return Retorna el objeto jugador1
     */
    public Jugador getJugador1() {
        return jugador1;
    }

    /**
     * Getter del atributo jugador2
     * @return Retorna el objeto jugador2
     */
    public Jugador getJugador2() {
        return jugador2;
    }
    /**
     * Getter del atributo MyFichas
     * @return Retorna la matriz de tipo Ficha
     */
    public Ficha[][] getMyFichas() {
        return myFichas;
    }

    /**
     * Permite obtener la direccion de memoria de la imagen correspondiente a la cadena recibida.
     * @param dir cadena con las denominacion de la moneda "ficha" 
     * @return Retorna una cadena con la direccion de la imagen correspondiente a la
     * denominacion de la moneda "ficha" seleccionada, en casi de que la cadena no contenga una 
     * denominacion existente retornara null.
     */ 
    public final String getImage(String dir){
         /*se utiliza una serie de if debido a que verciones anteriores de la maquina virtual
         * no permite que en el switch se utilze cadenas
         */
         if(dir.equals("50"))
             return ("./src/ufps/imagenes/50.png");
         if(dir.equals("100"))
             return ("./src/ufps/imagenes/100.png");
         if(dir.equals("200"))
             return ("./src/ufps/imagenes/200.png");
         if(dir.equals("500"))
             return ("./src/ufps/imagenes/500.png");
         if(dir.equals("1000"))
             return ("./src/ufps/imagenes/1000.png");
         
         return null;
     }

    /**
     * Obtiene el jugador correspondiente al que pertence el "color" recibido
     * @param dir cadena que contiene la direccion de la imagen del jugador que se
     * quiere obtener
     * @return Un Objeto de tipo Jugador al cual le corresponde la "imagen" recibida;
     * en caso de que no se encuentre jugador retorna null.
     */
    public Jugador buscarJugador(String dir) {
        
        if(this.jugador1.getImaFicha().equals(dir))
        return this.jugador1;
        if(this.jugador2.getImaFicha().equals(dir))
        return this.jugador2;
        return null;
            
    }
    
    /**
     * Cambia el estado de todas las Fichas de la matriz de Fichas a false, se modifica
     * el estado a cada una para simular que no hay ninguna ficha en el tablero
     */
    private void vaciarMatriz() {
       
        for(int i=0; i<this.myFichas.length;i++){
            for(int j=0;j<this.myFichas[0].length;j++){
              
                    this.myFichas[i][j].setEstado(false);
            }
        }
    }
    
    /**
     * Método que permite el juego de la computadora en el modo vs CPU
     */

}
