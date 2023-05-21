package main.presentation;

import javax.swing.*;
import java.text.SimpleDateFormat;
import main.domain.Pair;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CargarPartida extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton acceptButton = new JButton("Jugar");;
    private JButton exitButton = new JButton("Salir");;
    private ArrayList<String>  partidas = CtrlPresentacion.getPartidasGuardadas();
    private JPanel contentPanel = new JPanel();
    private ButtonGroup grupoOpciones = new ButtonGroup();
    private JRadioButton[] radioButtons = new JRadioButton[10];

    public CargarPartida() {
    	initComponents();
        initListeners();
    }
    
    private void initComponents() {
    	// Configurar la ventana
        configWindow();
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configurar el administrador de diseño
        configDesign(gbc);
        
        gbc.insets = new Insets(100, 0, 0, 0);
        // Configurar el texto de salida
		setText(gbc);
    	
		// Configurar los botones de opción
        configGroupButtons(gbc);
        
        // Configurar botón de jugar
        configPlayButton(gbc);
        
        // Configurar botón de salir
        configExitButton(gbc);
    }
    
    
    //asigna el nombre correspondiente a cada botón y lo añade al grupo y al panel
    private void configGroupButtons(GridBagConstraints gbc) {
    	int size = partidas.size();
    	for (int i = 0; i < 10 && i < size; ++i) {
    		radioButtons[i] = new JRadioButton(partidas.get(partidas.size()-1));
    		grupoOpciones.add(radioButtons[i]);
    		gbc.gridx = 0;
    	    gbc.gridy = 2+i;
    		gbc.anchor = GridBagConstraints.CENTER;
    		if (i == 0) gbc.insets = new Insets(35, 0, 5, 0);
    		else gbc.insets = new Insets(5, 0, 5, 0);
    		contentPanel.add(radioButtons[i], gbc);
    	}
    }
   
    
    //configura la ventana
    private void configWindow() {
    	setTitle("Cargar Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
  //setea el texto dependiendo de si hay partidas existentes o no
    private void setText(GridBagConstraints gbc) {
        JLabel messageLabel = new JLabel();
        Font labelFont = messageLabel.getFont();
        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        
        if (partidas.size() >= 1) messageLabel.setText("Escoge partida: ");

        else messageLabel.setText("No hay partidas guardadas");

        contentPanel.add(messageLabel, gbc);
    }
    
    
    //configura el diesño de la ventana
    private void configDesign(GridBagConstraints gbc) {
    	setContentPane(contentPanel);
        contentPanel.setLayout(new GridBagLayout());
    }
    
    
    
    //configura el botón de jugar(aceptar) y lo añade en el panel
    private void configPlayButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
	    gbc.gridy = 12;
	    gbc.insets = new Insets(20, 0, 100, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		acceptButton.setPreferredSize(new Dimension(175, 35));
        // Añadir el botón de aceptar solo si se pueden seleccionar partidas
        if (partidas.size() >= 1) contentPanel.add(acceptButton, gbc);
    }
    
    
    //configura el botón de salir y lo añade al panel
    private void configExitButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
	    gbc.gridy = 20;
        contentPanel.add(exitButton, gbc);
    }
    
    
    //inicializa los listeners del boton de exit y del grupo de botones
    private void initListeners() {
    	// Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
       
  
     // Configurar el ActionListener para el botón de jugar con sus múltiples opciones
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (int i = 0; i < 10 && i < partidas.size(); ++i) {
            		if (radioButtons[i].isSelected()) CtrlPresentacion.cargarPartida(partidas.get(partidas.size()-(i+1)));
            	}
            }
        });
    }
}