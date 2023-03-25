package domain.classes;


public class Jugador {
    
    /*
     * Username del jugador
     */ 
    
    private String username;

    /* 
     * Constructora de la clase Jugador
     */
    
    public Jugador(String username) {
        this.username = username;
    }

    /*
     * Devuelve el username del jugador
     */ 

    public String getUsername() {
        return username;
    }
}

public class CodeMaker extends Jugador {
    
    /*
     * Constructora de la clase CodeMaker
     */
    
    public CodeMaker(String username) {
        super(username);
    }
}

public class CodeBreaker extends Jugador {
    
    /*
     * Constructora de la clase CodeBreaker
     */
    
    public CodeBreaker(String username) {
        super(username);
    }
}

