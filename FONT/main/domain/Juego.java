package main.domain;


public class Juego {
	 //clase que representa el juego con dos strings informacionSistema y  informacionPuntuacion
    private String informacionSistema = "El juego consta de dos turnos en el que en cada uno se tendrá un rol distinto."
    		+ "En el caso de haber escogido ser CodeMaker en el primer turno el usuario deberá crear un código que el "
    		+ "ordenador intentará descifrar en un máximo de 10 intentos. Dependiendo de lo que tarde en adivinarlo se le "
    		+ "asignará al jugador una puntuación u otra."
    		+ "En el siguiente turno el jugador tomará el rol de CodeBreaker. Ahora será el ordenador el que generará"
    		+ "un código y el usuario tendrá 10 intentos para descifrarlo. Cuando el jugador descifre el código o use"
    		+ "los 10 intentos se le asignará la puntuación correspondiente, que se sumará a la puntuación del turno anterior."
    		+ "El jugador puede activar el sistema de ayuda para la partida, pero su puntuación se verá reducida si se activa "
    		+ "la ayuda.";
    private String informacionPuntuacion = "SISTEMA DE PUNTUACIÓN:\n"
    		+ "-NIVEL DIFICULTAD BAJO: 1000 * IntentosOrdenador + (10 - IntentosJugador + 1)  * 1000\n"
    		+ "-NIVEL DIFICULTAD MEDIO: 10000 * IntentosOrdenador + (10 - IntentosJugador + 1)  * 10000\n"
    		+ "-NIVEL DIFICULTAD ALTO: 100000 * IntentosOrdenador + (10 - IntentosJugador + 1)  * 100000\n"
    		+ "\nIntentosOrdenador = Intentos empleados por el ordenador para descifrar el código del jugador.\n"
    		+ "IntentosJugador = Intentos empleados por el jugador para descifrar el código del ordenador.\n";
    		
    private static Juego INSTANCE;

    /**
     * Constructor privado de la clase Juego
     * @param informacionSistema informacion del sistema
     * @param informacionPuntuacion informacion de la puntuacion
     */
    private Juego() {}
    /**
     * Devuelve la única instancia de la clase Juego
     * @param informacionSistema informacion del sistema
     * @param informacionPuntuacion informacion de la puntuacion
     */
    public static Juego getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Juego();
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
