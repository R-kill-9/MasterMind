package main.presentation;

import main.domain.ColorFeedBack;
import main.domaincontrollers.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/** Ejemplo de Controlador de Presentación. **/
public class CtrlPresentacion {

	/** Atributos **/

	private static CtrlDominio controladorDominio = CtrlDominio.getInstance();
	private static Integer numCols;
	/** Constructor y metodos de inicializacion **/

	

	public static void carregarvistaLogin() {
		LoginScreen vistaLogin = new LoginScreen();
	}
	public static void carregarvistaMastermindGame() {
		MastermindGame vistaGame = new MastermindGame(numCols);
		vistaGame.setVisible(true);
	}
	
	public static void loginUser(String username) throws Exception {
		controladorDominio.loginUser(username);
	}

	public static PantallaConfiguracion carregarVistaConfiguracion() {
		PantallaConfiguracion configuracion = new PantallaConfiguracion();
		configuracion.setTitle("Set up game");
        configuracion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuracion.setVisible(true);
		return null;
	}
	
	public static void newGame(Integer level, Boolean rol, Boolean ayuda) {
		numCols = level == 3 ? 6 : 5;
		controladorDominio.crearPartida(level, rol, ayuda);

	}
	
	public static Color[] submit(Color[] colors) {
		ArrayList<main.domain.Color> intento = new ArrayList<main.domain.Color>();
		Color red = Color.red;
		for(Color c: colors) {
		    if(c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.RED);
		    } else if(c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.GREEN);
		    } else if(c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) {
		        intento.add(main.domain.Color.BLUE);
		    } else if(c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.YELLOW);
		    } else if(c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 255) {
		        intento.add(main.domain.Color.PURPLE);
		    } else if(c.getRed() == 255 && c.getGreen() == 200 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.ORANGE);
		    }
		}
		
		try {
			ArrayList<ColorFeedBack> fb = controladorDominio.newCombinacion(intento);
			Color[] feedBack = new Color[fb.size()];
			System.out.println(fb);
			for(int i = 0; i < fb.size(); i++) {
			    ColorFeedBack fbColor = fb.get(i);
			    if(fbColor == ColorFeedBack.BLACK) {
			        feedBack[i] = new Color(0, 0, 0);
			    } else if(fbColor == ColorFeedBack.WHITE) {
			        feedBack[i] =  new Color(255, 255, 255);
			    } else {
			        feedBack[i] = new Color(128, 128, 128);
			    }
			}
			for (Color c : feedBack) {
			    System.out.println(c);
			}
			return feedBack;
			
		} catch (Exception e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(),
                      "Error", JOptionPane.ERROR_MESSAGE);
			  
		}
		return null;
		
	}
	
}
