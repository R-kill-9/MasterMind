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
    private JLabel messageLabel = new JLabel("Consultar Rankings Globales");
    private JLabel levelLabel = new JLabel("Selecciona el nivel");
    private JLabel personalRankingLabel = new JLabel("Consultar Ranking Personal");
    private Font labelFont = messageLabel.getFont();
    private JPanel contentPane = new JPanel();
    
    public Ranking() {
    	initComponents();
        initListeners();
    }
    
    void initComponents() {
    	// Configurar la ventana
        setTitle("Consultar Ranking");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);

        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        levelLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
        personalRankingLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));

        // Crear el panel contenedor y configurar el administrador de diseño
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);

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
        contentPane.add(easyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(mediumButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(hardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(personalRankingLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(personalButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        contentPane.add(exitButton, gbc);
    }
    
    void initListeners() {
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
