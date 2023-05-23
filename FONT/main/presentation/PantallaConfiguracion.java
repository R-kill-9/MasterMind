package main.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaConfiguracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblRol = new JLabel("Rol:");
	private JPanel  panel = new JPanel();
	private JComboBox<String> cmbRol = new JComboBox<>();
	private JButton btnAceptar = new JButton("Aceptar");
	private JLabel lblTitulo = new JLabel("Configurar Nueva Partida");
	private JButton exitButton = new JButton("Salir");
	private JLabel chkAyuda = new JLabel("Ayuda:");
	private JComboBox<String> cmbAyuda = new JComboBox<>();
	private JLabel lblDificultad = new JLabel("Nivel de Dificultad:");
	private JComboBox<String> cmbDificultad = new JComboBox<>();
	
	public PantallaConfiguracion() {
	        // Configurar la ventana
			configWindow();

	        // Crear el panel principal
	        panel.setLayout(new GridBagLayout()); // Usamos un GridBagLayout para centrar los componentes
	        GridBagConstraints gbc = new GridBagConstraints();
	        
	        //Configurar diseño ventana
	        configDesign(gbc);
	        
	        setTitle(gbc);
	        
	        setLevel(gbc);
	        
	        setHelp(gbc);
	        
	        setRol(gbc);
	       
	        setAccept(gbc);

	        // Agregar el panel al contenido de la ventana
	        setContentPane(panel);
	        
	        setExit(gbc);
    }
	
	//Configura la ventana
	private void configWindow() {
		setTitle("Configurar Nueva Partida");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
	}

	
	//Configura el diseño de la ventana
	private void configDesign(GridBagConstraints gbc) {
		// Crear el panel principal
        panel.setLayout(new GridBagLayout()); // Usamos un GridBagLayout para centrar los componentes
        // Configurar el GridBagConstraints para centrar los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Agregamos margen entre los componentes
	}
	
	
	//configura y añade el título al panel
	private void setTitle(GridBagConstraints gbc) {
		// Agregar el título con fuente más grande y centrado
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); // Cambiar la fuente y el tamaño
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar horizontalmente
        panel.add(lblTitulo, gbc);
	}
	
	
	//Configura y añade el desplegable para seleccionar nivel al panel
	private void setLevel(GridBagConstraints gbc) {
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
	}
	
	
	//Configura y añade el checkbox para la opción de ayuda en el panel
	private void setHelp(GridBagConstraints gbc) {
        cmbAyuda.addItem("Yes");
        cmbAyuda.addItem("No");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(chkAyuda, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cmbAyuda, gbc);
	}
	
	
	//Configura y añade el desplegable para escoger el rol en el panel
	private void setRol(GridBagConstraints gbc) {
        cmbRol.addItem("CodeMaker");
        cmbRol.addItem("CodeBreaker");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(lblRol, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(cmbRol, gbc);
	}
	
	
	//Configura y añade el botón de aceptar en el panel, también configura su listener
	private void setAccept(GridBagConstraints gbc) {
		 // Agregar el botón de aceptar
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
            if(rol == "CodeBreaker") CtrlPresentacion.carregarvistaMastermindGame();
            else CtrlPresentacion.carregaCodeMaker();
            setVisible(false);
            dispose();
            repaint();
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnAceptar, gbc);
	}
	
	
	//Configura el botón de salida y su listener correspondiente
	private void setExit(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(exitButton, gbc);
        
     // Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
	}
}
