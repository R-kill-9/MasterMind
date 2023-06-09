package main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class HistorialPartidas {
    private static ArrayList<Pair<String,Date>> partidas;
    
    
    public HistorialPartidas() {
        partidas = new ArrayList<Pair<String,Date>>();
    }
    
    public static void agregarPartida(String username, Date dataIni) {
    	Pair<String,Date> infoPartida = new Pair<String, Date>(username,dataIni);
        partidas.add(infoPartida);
    }
    
    public static ArrayList<Pair<String,Date>> getPartidas() {
        return partidas;
    }

	public static boolean borrarPartida(String username, Date dataIni) {
		Pair<String,Date> infoPartida = new Pair<String, Date>(username, dataIni);
	    Iterator<Pair<String, Date>> it = partidas.iterator();
	    while (it.hasNext()) {
	        Pair<String, Date> partida = it.next();
	        if (partida.getFirst().equals(username) && partida.getSecond().equals(dataIni)) {
	            it.remove();
	            return true;
	        }
	    }
	    return false;
	}
}
