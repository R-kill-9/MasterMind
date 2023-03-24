package domain;

import java.util.HashSet;
import java.util.Set;

/** Ejemplo de una clase de Dominio. **/
public class Asignatura {
	
	/** Atributos **/
	
	private String nombre;
	private Set<Alumno> alumnos;
	
	/** Constructora **/
	
	public Asignatura(String nombre) {
		this.nombre = nombre;
		alumnos = new HashSet<Alumno>();
	}
	
	/** Métodos públicos **/
	
	public void anadirAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}

     public void eliminarAlumno (Alumno alumno) {
           alumnos.remove(alumno);
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
