package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton configButton;
    private JButton rankingButton;
    private JButton rulesButton;
    private JButton partidasButton;
    
    public Menu() {
    	initComponents();
    	initListeners();
    }
    
    private void initComponents() {
    	// Configurar la ventana
        setTitle("Menú Principal");
        setSize(470, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Crear los componentes
        configButton = new JButton("Nueva Partida");
        partidasButton = new JButton("Cargar Partida");
        rankingButton = new JButton("Ver Rankings");
        rulesButton = new JButton("Cómo jugar");
        JLabel menuLabel = new JLabel("Menú");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 30));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        setLayout(new GridBagLayout());
        
        // Añadir el texto de menú
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 10, 0, 0);
        add(menuLabel, gbc);
        

        // Añadir el botón de Crear una nueva Partida
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(configButton, gbc);

        // Añadir el botón de Cargar Partida
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(partidasButton, gbc);

        // Añadir el botón de Ver Rankings
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(rankingButton, gbc);

        // Añadir el botón de Cómo jugar
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(rulesButton, gbc);
    }
    
    private void initListeners() {
    	// Configurar el ActionListener para el botón de configuración
        configButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaConfiguracion();
                setVisible(false);
            }
        });

        // Configurar el ActionListener para el botón de ranking
        rankingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking();
                setVisible(false);
            }
        });

        // Configurar el ActionListener para el botón de rules
        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String juegoInfo = CtrlPresentacion.getJuegoInfo();
            	String juegoInfoPuntuacion = CtrlPresentacion.getJuegoInfoPuntuacion();
            	
            	JOptionPane.showMessageDialog(null, "SISTEMA DE JUEGO:" + System.lineSeparator() + juegoInfo + System.lineSeparator() +	System.lineSeparator() + "SISTEMA DE PUNTUACIÓN: "+ System.lineSeparator() + juegoInfoPuntuacion,
                        "Rules", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });

        // Configurar el ActionListener para el botón de partidas
        partidasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaCargarPartida();
                setVisible(false);
            }
        });
    }
}
