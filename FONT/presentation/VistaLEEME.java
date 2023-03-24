package presentation;

import java.awt.*;
import javax.swing.*;

/** Ejemplo de Vista **/
public class VistaLEEME {

	/** Componentes de la interficie grafica **/
	
	private JDialog dialogOptionPane;

	/** Constructora **/
	
	public VistaLEEME() {
		inicializarComponentes();
	}

	/** Métodos públicos **/
	
	public void hacerVisible() {
		dialogOptionPane.pack();
		dialogOptionPane.setVisible(true);
	}

	/** Resto de metodos privados **/
	
	private void inicializarComponentes() {
		String strTitulo = "LEEME";
		String[] strBotones = { "Ok" };
		String strTexto = "Este ejemplo no pretende ser un modelo universal, sino un posible punto"
				+ "\n"
				+ "de partida para la construcción de una arquitecura en tres capas, en Java. "
				+ "\n"
				+ "\n"
				+ "Las arquitecturas en tres capas se pueden hacer de muchas maneras, y  "
				+ "\n"
				+ "ésta es una de ellas, pero en ningún momento se sugiere que haya que "
				+ "\n"
				+ "hacerlo así o que ésta sea la mejor manera de hacerlo.\n";
		JOptionPane optionPane = new JOptionPane(strTexto,
				JOptionPane.INFORMATION_MESSAGE);
		optionPane.setOptions(strBotones);
		optionPane.setBackground(Color.orange);
		dialogOptionPane = optionPane.createDialog(new JFrame(), strTitulo);
		dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
