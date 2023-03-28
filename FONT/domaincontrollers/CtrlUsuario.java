package domaincontrollers;
import java.util.ArrayList;
import domain.Usuario;
import domain.Partida;
import domaincontrollers.CtrlPartida;

/**
 * Clase que representa el controlador de dominio de la clase Usuario.
 */
public class CtrlUsuario {

    /**
     * Array que contiene los usuarios del sistema.
     */
    private final ArrayList<Usuario> usuarios;
    private Usuario userAct;
    private CtrlPartida partidaController;
    /**
     * Constructora por defecto.
     */
    public CtrlUsuario() {
        this.usuarios = new ArrayList<>();
        this.userAct = new Usuario();
        this.partidaController = new CtrlPartida();
    }

    /**
     * Añade un usuario al sistema.
     * @param username Nombre de usuario.
     */
    public void addUsuario(String username) {
        Usuario user = new Usuario(username);
        this.usuarios.add(user);
    }
    
    public void loginUser(String username) {
    	Usuario user = usuarios.getUser(username);
    	if (user != null) userAct = user;
    	else throw new Exception("user is not exists");
    }

    /**
     * Obtenemos el usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return Usuario con el nombre de usuario dado.
     */
    public Usuario getUsuario(String username) {
        for (Usuario user : this.usuarios) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * En caso de que la puntuación sea mayor que la máxima que ha conseguido el usuario, se actualiza el record.
     */
    public void setRecord(String username, int puntuacion) {
        this.userAct.setMaxScore(puntuacion);
    }

    /**
     * Obtenemos el record del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return Record del usuario con el nombre de usuario dado.
     */
    public int getRecord() {
        return this.userAct.getMaxScore();
    }

    /**
     * Añade una partida al usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @param p Partida.
     */
    public void addPartida(String username, Partida p) {
        this.userAct.addPartida(p);
    }


    /**
     * Obtenemos el ArrayList de partidas del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @return ArrayList de partidas del usuario con el nombre de usuario dado.
     */
    public ArrayList<Partida> getPartidas() {
        return userAct.getPartidasGuardadas();

    }

    /**
     * Borra la partida con el identificador dado del usuario con el nombre de usuario dado.
     * @param username Nombre de usuario.
     * @param fecha Fecha de la partida.
     */
    public void deletePartida(Date fecha) {
       this.userAct.deletePartida(fecha);
    }
}