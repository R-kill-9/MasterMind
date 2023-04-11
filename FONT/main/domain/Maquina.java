package main.domain;

import java.util.List;

public interface Maquina {
	public void setSolucion(List<Integer> solution);
	public List<List<Integer>> resolve(List<Integer> solucionUsuario);
}
