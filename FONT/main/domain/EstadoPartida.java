package main.domain;

public class EstadoPartida {
    
    /**
     * Estado de la partida que será o bien "Running" o "Paused" o "Saved"
     */
    private PossiblesEstadosPartida estado; 

    /**
     * Constructora de la clase EstadoPartida
     * @param estado 
     * @param estado Estado de la partida que al iniciarla será "Running"
     */
    public EstadoPartida(String estado) {
        this.estado = PossiblesEstadosPartida.RUNNING;
    }

    /**
     * Devuelve el estado de la partida
     */
    public PossiblesEstadosPartida getEstado() {
        return this.estado;
    }

    /**
     * Introduce el estado de la partida
     */
    public void setEstado(String estado) {
    	switch(estado) {
			case "running":
				this.estado = PossiblesEstadosPartida.RUNNING;
				break;
			case "paused":
				this.estado = PossiblesEstadosPartida.PAUSED;
				break;
			case "saved":
				this.estado = PossiblesEstadosPartida.SAVED;
				break;
			default:
				throw new  IllegalArgumentException("Estado de partida: running, paused or saved");
    	}
    }

}
