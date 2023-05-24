package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton easyButton = new JButton("Fácil");
    private JButton mediumButton = new JButton("Medio");
    private JButton hardButton = new JButton("Alto");
    private JButton personalButton = new JButton("Ranking Personal");
    private JButton exitButton = new JButton("Salir");
    private JLabel messageLabel = new JLabel("Consultar Rankings");
    private JLabel levelLabel = new JLabel("Selecciona el nivel");
    private Font labelFont = messageLabel.getFont();
    private JPanel contentPane = new JPanel();
    
    public Ranking() {
    	initComponents();
        initListeners();
    }
    
    void initComponents() {
    	//configurar la ventana
    	configWindow();
        
    	//configura las fuentes de los label
    	configFonts();
        
    	GridBagConstraints gbc = new GridBagConstraints();
    	
        //configura el administrador de diseño
    	configDesign(gbc);
        
        //añade los componentes
    	addComponents(gbc);
    }
    
    // Configura la ventana indicando su título y tamaño.
    private void configWindow() {
    	setTitle("Ranking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
    //configura las fuentes de los distintos labels
    private void configFonts() {
    	messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        levelLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
    }
    
    
    //Configura el diseño de la ventana, reduciendo los márgenes verticales.
    private void configDesign(GridBagConstraints gbc) {
    	setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        gbc.insets = new Insets(25, 5, 5, 5); 
    }
    
    
    //Añade todos los componentes creados al panel.
    private void addComponents(GridBagConstraints gbc	) {
    	gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);
        
        gbc.insets = new Insets(5, 5, 5, 5); 
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(levelLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.ipadx = 30;
        gbc.ipady = 20;
        Color easyBlue = new Color(0, 0, 150);
        easyButton.setBackground(easyBlue);
        easyButton.setForeground(Color.WHITE);
        easyButton.setFocusPainted(false);
        easyButton.setBorderPainted(false);
        
        easyButton.setOpaque(true);
        contentPane.add(easyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        Color mediumBlue = new Color(0, 0, 100);
        mediumButton.setBackground(mediumBlue);
        mediumButton.setForeground(Color.WHITE);
        mediumButton.setFocusPainted(false);
        mediumButton.setBorderPainted(false);
        
        mediumButton.setOpaque(true);
        contentPane.add(mediumButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        Color darkBlue = new Color(0, 0, 10);
        hardButton.setBackground(darkBlue);
        hardButton.setForeground(Color.WHITE);
        hardButton.setFocusPainted(false);
        hardButton.setBorderPainted(false);
        
        hardButton.setOpaque(true);
        contentPane.add(hardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        Color greenColor = new Color(0, 200, 0);
        personalButton.setBackground(greenColor);
        personalButton.setForeground(Color.WHITE);
        personalButton.setFocusPainted(false);
        personalButton.setBorderPainted(false);
        
        personalButton.setOpaque(true);
        contentPane.add(personalButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        Color redColor = new Color(200, 0, 0);
        exitButton.setBackground(redColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        
        exitButton.setOpaque(true);
        contentPane.add(exitButton, gbc);
    }
    
    
    //Inicializa el listener para ver los rankings fácil, medio, difícil, personal o salir.
    private void initListeners() {
    	// Configurar el ActionListener para los botones de nivel
        easyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking1();
                    setVisible(false);
            }
        });

        mediumButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking2();
                    setVisible(false);
            }
        });

        hardButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking3();
                    setVisible(false);
            }
        });

        // Configurar el ActionListener para el botón de ranking personal
        personalButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRankingPersonal();
                    setVisible(false);
            }
        });
        
     // Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
    }
}
