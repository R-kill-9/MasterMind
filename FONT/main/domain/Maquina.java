package main.domain;

import java.util.List;

public interface Maquina {
	public void setSolucion(Combinacion solution);
	public List<Combinacion> resolve(Combinacion solucionUsuario);
}
