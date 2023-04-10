package main.domain;

public interface Maquina {
	public void setSolucion(Combinacion solution);
	public int resolve(Combinacion solucionUsuario);
}
