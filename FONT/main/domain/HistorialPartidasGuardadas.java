package main.domain;

import java.util.ArrayList;
import java.util.Date;

public class HistorialPartidasGuardadas {
    private static ArrayList<Pair<String,Date>> partidas;
    
    public HistorialPartidasGuardadas() {
        partidas = new ArrayList<Pair<String,Date>>();
    }
    
    public static void agregarPartidaGuardada(String username, Date dataIni) {
    	Pair<String,Date> infoPartida = new Pair<String, Date>(username,dataIni);
        partidas.add(infoPartida);
    }
    
    public static ArrayList<Pair<String,Date>> getPartidas(String username) {
    	ArrayList<Pair<String,Date>> partidasUsuario = new ArrayList<Pair<String,Date>>();
        for(Pair<String,Date> partida : partidas) {
            if(partida.getFirst().equals(username)) {
                partidasUsuario.add(partida);
            }
        }
        return partidasUsuario;
    }

	public static void borrarPartidaGuardada(String username, Date dataIni) {
		Pair<String,Date> infoPartida = new Pair<String, Date>(username,dataIni);
		Integer posPartida = partidas.indexOf(infoPartida);
		partidas.remove(posPartida);
	}
}
