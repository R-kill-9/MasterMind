package main.domain;


public class Juego {
	 //clase que representa el juego con dos strings informacionSistema y  informacionPuntuacion
    private String informacionSistema;
    private String informacionPuntuacion;
    private static Juego INSTANCE;

    /**
     * Constructor privado de la clase Juego
     * @param informacionSistema informacion del sistema
     * @param informacionPuntuacion informacion de la puntuacion
     */
    private Juego(String informacionSistema, String informacionPuntuacion) {
        this.informacionSistema = informacionSistema;
        this.informacionPuntuacion = informacionPuntuacion;
    }
    /**
     * Devuelve la única instancia de la clase Juego
     * @param informacionSistema informacion del sistema
     * @param informacionPuntuacion informacion de la puntuacion
     */
    public static Juego getInstance(String informacionSistema, String informacionPuntuacion) {
        if(INSTANCE == null) {
            INSTANCE = new Juego(informacionSistema, informacionPuntuacion);
        }
        return INSTANCE;
    }
    
    /**
     * Devuelve la informacion del sistema
     * @return informacionSistema
     */
    public String getInformacionSistema() {
        return informacionSistema;
    }
    
    /**
     * Introduce la informacion del sistema
     * @param informacionSistema informacion del sistema
     */
    public void setInformacionSistema(String informacionSistema) {
        this.informacionSistema = informacionSistema;
    }
    
    /**
     * Devuelve la informacion de la puntuacion
     * @return informacionPuntuacion
     */
    public String getInformacionPuntuacion() {
        return informacionPuntuacion;
    }
    
    /**
     * Introduce la informacion de la puntuacion
     * @param informacionPuntuacion informacion de la puntuacion
     */
    public void setInformacionPuntuacion(String informacionPuntuacion) {
        this.informacionPuntuacion = informacionPuntuacion;
    }
    
    /**
     * Devuelve un string con la informacion del sistema y la de la puntuacion
     */
    @Override
    public String toString() {
        return "Juego [informacionSistema=" + informacionSistema + ", informacionPuntuacion=" + informacionPuntuacion + "]";
    }
}   
