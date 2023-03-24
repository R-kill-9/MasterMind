package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** Ejemplo de una clase de Dominio. **/
public class Alumno {
	
	/** Atributos **/
	
	private String nombre;
	private Map<String,Asignatura> asignaturas;
	private Map<Asignatura, Double> notas;

	/** Constructora **/
	
	public Alumno(String nombre) {
		this.nombre = nombre;
		asignaturas = new HashMap<String,Asignatura>();
		notas = new HashMap<Asignatura, Double>();
	}
	
	/** Métodos públicos **/
	
     // comparte referencia a asignatura con el controlador, pero no hay problema porque no se modifica
	public void anadirAsignatura(Asignatura asignatura, Double nota) {
		asignaturas.put(asignatura.toString(),asignatura);
		notas.put(asignatura, nota);
		asignatura.anadirAlumno(this);
	}
	
	public Double calcularMedia() {
		Double media = 0.0;
		Collection<Double> notas = this.notas.values();
		for (Double nota : notas)
			media += nota;
		return media/((double) notas.size());
	}
	
	public Double getNota(String nombreAsignatura) {
		Double nota = notas.get(asignaturas.get(nombreAsignatura));
		if (nota == null) return 0.0;
		return nota;
	}
	
	/** Métodos redefinidos **/
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
}
