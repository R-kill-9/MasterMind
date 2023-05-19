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
    private ButtonGroup grupoOpciones = new ButtonGroup();;
    private JRadioButton opcion1, opcion2, opcion3, opcion4, opcion5, opcion6, opcion7, opcion8, opcion9, opcion10;

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

		setText(gbc);
    	
        configGroupButtons(gbc);
        
        if (partidas.size() > 1) contentPanel.add(acceptButton, gbc);
        
        contentPanel.add(Box.createVerticalGlue());
  
        contentPanel.add(exitButton, gbc);
    }
    
    
    //asigna el nombre correspondiente a cada botón y lo añade al grupo y al panel
    private void configGroupButtons(GridBagConstraints gbc) {
    	contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Colocar los componentes de forma vertical
    	if(partidas.size() >= 1) {
    		opcion1 = new JRadioButton(partidas.get(partidas.size()-1));
    		grupoOpciones.add(opcion1);
    		gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion1, gbc);
    		
    	}
    	if(partidas.size() >= 2) {
    		opcion2 = new JRadioButton(partidas.get(partidas.size()-2));
    		grupoOpciones.add(opcion2);
    		gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion2, gbc);
    	}
    	if(partidas.size() >= 3) {
    		opcion3 = new JRadioButton(partidas.get(partidas.size()-3));
    		grupoOpciones.add(opcion3);
    		gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion3, gbc);
    		
    	}
    	if(partidas.size() >= 4) {
    		opcion4 = new JRadioButton(partidas.get(partidas.size()-4));
    		grupoOpciones.add(opcion4);
    		gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion4, gbc);
    	}
    	if(partidas.size() >= 5) {
    		opcion5 = new JRadioButton(partidas.get(partidas.size()-5));
    		grupoOpciones.add(opcion5);
    		gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion5, gbc);
    	}
    	if(partidas.size() >= 6) {
    		opcion6 = new JRadioButton(partidas.get(partidas.size()-6));
    		grupoOpciones.add(opcion6);
    		gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion6, gbc);
    	}
    	if(partidas.size() >= 7) {
    		opcion7 = new JRadioButton(partidas.get(partidas.size()-7));
    		grupoOpciones.add(opcion7);
    		gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion7, gbc);
    	}
    	if(partidas.size() >= 8) {
    		opcion8 = new JRadioButton(partidas.get(partidas.size()-8));
    		grupoOpciones.add(opcion8);
    		gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion8, gbc);
    	}
    	if(partidas.size() >= 9) {
    		opcion9 = new JRadioButton(partidas.get(partidas.size()-9));
    		grupoOpciones.add(opcion9);
    		gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion9, gbc);
    	}
    	if(partidas.size() >= 10) {
    		opcion10 = new JRadioButton(partidas.get(partidas.size()-10));
    		grupoOpciones.add(opcion10);
    		gbc.gridx = 0;
            gbc.gridy = 11;
            gbc.fill = GridBagConstraints.CENTER;
    		contentPanel.add(opcion10, gbc);
    	}
    }
   
    //configura la ventana
    private void configWindow() {
    	setTitle("Cargar Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
  //setea el texto dependiendo de si hay partidas existentes o no
    private void setText(GridBagConstraints gbc) {
    	gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        if (partidas.size() >= 1) add(new JLabel("Escoge la partida: "), gbc);
        else add(new JLabel("No hay partidas guardadas "), gbc);
        
    }
    
    
    //
    private void configDesign(GridBagConstraints gbc) {
    	setContentPane(contentPanel);
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
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
                if (opcion1.isSelected()) {
                    CtrlPresentacion.cargarPartida(partidas[0]);
                } else if (opcion2.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[1]);
                } else if (opcion3.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[2]);
                } else if (opcion4.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[3]);
                } else if (opcion5.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[4]);
                } else if (opcion6.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[5]);
                } else if (opcion7.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[6]);
                } else if (opcion8.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[7]);
                } else if (opcion9.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[8]);
                } else if (opcion10.isSelected()) {
                	CtrlPresentacion.cargarPartida(partidas[9]);
                }
            }
        });
    }
}