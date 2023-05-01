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
	private static Boolean setAyuda;
	private static Integer lastRonda = 0;
	
	private static ArrayList<main.domain.Color> transformCombination(Color[] colors){
		ArrayList<main.domain.Color> intento = new ArrayList<main.domain.Color>();
		Color red = Color.red;
		for(Color c: colors) {
		    if(c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.RED);
		    } else if(c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.GREEN);
		    } else if(c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) {
		        intento.add(main.domain.Color.BLUE);
		    } else if(c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 255) {
		        intento.add(main.domain.Color.CYAN);
		    }else if(c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 255) {
		        intento.add(main.domain.Color.PURPLE);
		    } else if(c.getRed() == 255 && c.getGreen() == 200 && c.getBlue() == 0) {
		        intento.add(main.domain.Color.ORANGE);
		    }
		}
		return intento;
	}
	

	public static void carregarvistaLogin() {
		LoginScreen vistaLogin = new LoginScreen();
	}
	public static void carregarvistaMastermindGame() {
		System.out.println(CtrlDominio.getNumRounds());
		MastermindGame vistaGame = new MastermindGame(numCols, setAyuda);
		vistaGame.setVisible(true);
	}
	
	public static void loginUser(String username) throws Exception {
		controladorDominio.loginUser(username);
	}
	public static void carregarEndGame() {
		Integer score = CtrlUsuario.getScore();
		EndGame endG = new EndGame(score);
		endG.setVisible(true);
	}
	public static void carregaCodeMaker() {
		CodeMaker vistaCM = new CodeMaker(numCols);
		vistaCM.setVisible(true);
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
		setAyuda = ayuda;
		controladorDominio.crearPartida(level, rol, ayuda);
		System.out.println("Num rondas " + CtrlDominio.getNumRounds());


	}
	
	public static Color[] submit(Color[] colors) {
		ArrayList<main.domain.Color> intento = new ArrayList<main.domain.Color>();
		intento = transformCombination(colors);
		try {
			ArrayList<ColorFeedBack> fb = controladorDominio.newCombinacion(intento);
			Color[] feedBack = new Color[fb.size()];
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
			return feedBack;
			
		} catch (Exception e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(),
                      "Error", JOptionPane.ERROR_MESSAGE);
			  
		}
		return null;
		
	}
	public static void reiniciarPartida() {
		CtrlDominio.reiniciarPartida();
		MastermindGame vistaGame = new MastermindGame(numCols, setAyuda);
		vistaGame.setVisible(true);
	}
	
	public static void setAyudaPartida() {
		CtrlDominio.solicitarAyuda();
	}
	
	
	public static Integer setSolution(Color[] colors) {
		ArrayList<main.domain.Color> intento = new ArrayList<main.domain.Color>();
		intento = transformCombination(colors);
		try {
			return CtrlDominio.setSolution(intento);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public static void finRonda(String turnoAct) {
		Integer numRounds = CtrlDominio.getNumRounds();
		++lastRonda;
		if(lastRonda >= 2) carregarEndGame();
		else {
			if(numRounds <= 2 && turnoAct.equals("CB")) {
				carregaCodeMaker();
			}
			else if(numRounds <= 2 && turnoAct.equals("CM")) {
				carregarvistaMastermindGame();
			}
		}
	}
	
	public static void cargaCombMaquina() {
		ArrayList<ArrayList<main.domain.Color>> combinations = CtrlDominio.getAllCombLastTurno();
		Color[][] colores = new Color[combinations.size()][combinations.get(0).size()];
	    for (int i = 0; i < combinations.size(); i++) {
	        for (int j = 0; j < combinations.get(i).size(); j++) {
	            main.domain.Color c = combinations.get(i).get(j);
	            switch (c) {
	                case RED:
	                    colores[i][j] = Color.RED;
	                    break;
	                case GREEN:
	                    colores[i][j] = Color.GREEN;
	                    break;
	                case BLUE:
	                    colores[i][j] = Color.BLUE;
	                    break;
	                case CYAN:
	                    colores[i][j] = Color.CYAN;
	                    break;
	                case PURPLE:
	                    colores[i][j] = Color.MAGENTA; // Color púrpura personalizado
	                    break;
	                case ORANGE:
	                    colores[i][j] = Color.ORANGE;
	                    break;
	            }
	        }
	    }
	    TurnosMaquina turnosM = new TurnosMaquina(colores);
	    turnosM.setVisible(true);
	}
	
}
