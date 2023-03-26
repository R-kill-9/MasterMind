package domaincontrollers;
import java.util.ArrayList;
import domain.Usuario;

/**
 * Clase que representa el controlador de dominio de la clase Usuario.
 */
public class CtrlUsuario {

    /**
     * Array que contiene los usuarios del sistema.
     */
    private final ArrayList<Usuario> usuarios;

    /**
     * Constructora por defecto.
     */
    public CtrlUsuario() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Añade un usuario al sistema.
     * @param username Nombre de usuario.
     */
    public void addUsuario(String username) {
        Usuario u = new Usuario(username);
        this.usuarios.add(u);
    }

    /**
     * Obtenemos el usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return Usuario con el nombre de usuario dado.
     */
    public Usuario getUsuario(String username) {
        for (Usuario u : this.usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * En caso de que la puntuación sea mayor que la máxima que ha conseguido el usuario, se actualiza el record.
     */
    public void setRecord(String username, int puntuacion) {
        Usuario u = this.getUsuario(username);
        if (u != null) {
            u.setMaxScore(puntuacion);
        }
    }

    /**
     * Obtenemos el record del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return Record del usuario con el nombre de usuario dado.
     */
    public int getRecord(String username) {
        Usuario u = this.getUsuario(username);
        if (u != null) {
            return u.getMaxScore();
        }
        return 0;
    }


    /**
     * Añade una partida al usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @param p Partida.
     */
    public void setPartida(String username, domain.Partida p) {
        Usuario u = this.getUsuario(username);
        if (u != null) {
            u.addPartida(p);
        }
    }


    /**
     * Obtenemos el ArrayList de partidas del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return ArrayList de partidas del usuario con el nombre de usuario dado.
     */
    public ArrayList<domain.Partida> getPartidas(String username) {
        Usuario u = this.getUsuario(username);
        if (u != null) {
            return u.getPartidasGuardadas();
        }
        return null;
    }

    /**
     * Borra la partida con el identificador dado del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @param fecha Fecha de la partida.
     */
    public void deletePartida(String username, String fecha) {
        Usuario u = this.getUsuario(username);
        if (u != null) {
            u.deletePartida(fecha);
        }
    }


}