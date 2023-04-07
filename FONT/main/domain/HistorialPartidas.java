package main.domain;

import java.util.ArrayList;
import java.util.Date;

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
		Pair<String,Date> infoPartida = new Pair<String, Date>(username,dataIni);
		Integer posPartida = partidas.indexOf(infoPartida);
		partidas.remove(posPartida);
		return posPartida == null ? false : true;
	}
}