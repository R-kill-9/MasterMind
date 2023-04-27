package main.presentation;
import javax.swing.*;
import java.awt.*;

public class PantallaConfiguracion extends JFrame {

	private static final long serialVersionUID = 1L;

	public PantallaConfiguracion() {
	        // Configurar la ventana
	        setTitle("Configurar Nueva Partida");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 560);
	        
	        // Crear el panel principal
	        JPanel panel = new JPanel();
	        panel.setLayout(new GridBagLayout()); // Usamos un GridBagLayout para centrar los componentes

	        // Configurar el GridBagConstraints para centrar los componentes
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.insets = new Insets(5, 5, 5, 5); // Agregamos margen entre los componentes

	        // Agregar el título con fuente más grande y centrado
	        JLabel lblTitulo = new JLabel("Configurar Nueva Partida");
	        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); // Cambiar la fuente y el tamaño
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2;
	        gbc.anchor = GridBagConstraints.CENTER; // Centrar horizontalmente
	        panel.add(lblTitulo, gbc);

	        // Agregar el desplegable para el nivel de dificultad
	        JLabel lblDificultad = new JLabel("Nivel de Dificultad:");
	        JComboBox<String> cmbDificultad = new JComboBox<>();
	        cmbDificultad.addItem("1");
	        cmbDificultad.addItem("2");
	        cmbDificultad.addItem("3");
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        gbc.gridwidth = 1;
	        panel.add(lblDificultad, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        panel.add(cmbDificultad, gbc);

	        // Agregar el checkbox para la opción de ayuda
	        JLabel chkAyuda = new JLabel("Ayuda");
	        JComboBox<String> cmbAyuda = new JComboBox<>();
	        cmbAyuda.addItem("Yes");
	        cmbAyuda.addItem("No");
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        gbc.gridwidth = 2;
	        panel.add(chkAyuda, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        panel.add(cmbAyuda, gbc);
	        
	       


	        // Agregar el desplegable para el rol
	        JLabel lblRol = new JLabel("Rol:");
	        JComboBox<String> cmbRol = new JComboBox<>();
	        cmbRol.addItem("CodeMaker");
	        cmbRol.addItem("CodeBreaker");
	        gbc.gridx = 0;
	        gbc.gridy = 3;
	        gbc.gridwidth = 1;
	        panel.add(lblRol, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        panel.add(cmbRol, gbc);

	        // Agregar el botón de aceptar
	        JButton btnAceptar = new JButton("Aceptar");
	        btnAceptar.addActionListener(e -> {
	            // Obtener los valores seleccionados
	            String nivelDificultad = (String) cmbDificultad.getSelectedItem();
	            String conAyuda =  (String) cmbAyuda.getSelectedItem();
	            String rol = (String) cmbRol.getSelectedItem();
	            Integer nivel = 1;
	            if(nivelDificultad == "2") nivel = 2;
	            else if(nivelDificultad == "3") nivel = 3;
	            CtrlPresentacion.newGame(nivel, conAyuda == "Yes",  rol == "CodeMaker");

	            // Crear la instancia de mastermindGame
	            CtrlPresentacion.carregarvistaMastermindGame();
	            this.setVisible(false);
	            dispose();
	        });
	        gbc.gridx = 0;
	        gbc.gridy = 4;
	        gbc.gridwidth = 2;
	        panel.add(btnAceptar, gbc);

	        
	        // Agregar el panel al contenido de la ventana
	        setContentPane(panel);
    }

    public static void main(String[] args) {
        PantallaConfiguracion pantalla = new PantallaConfiguracion();
        pantalla.setVisible(true);
    }
}
