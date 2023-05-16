package main.presentation;

import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class RankingFacil extends JFrame {
    private static final long serialVersionUID = 1L;
    private TreeMap<String, Integer> ranking = new TreeMap<>();
    private JButton exitButton = new JButton("Salir");
    private JLabel messageLabel = new JLabel("Ranking Nivel Fácil:");    
    private Font labelFont = messageLabel.getFont();
    private JPanel contentPane = new JPanel();

    public RankingFacil(TreeMap<String, Integer> gettedRanking) {
    	initComponents(gettedRanking);
    	initListeners();
    }
    
    private void initComponents(TreeMap<String, Integer> gettedRanking) {
    	// Configurar la ventana
        setTitle("Ranking NIvel Fácil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
        ranking = gettedRanking;

        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        
        //configurar el administrador de diseño
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
        
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);

        int i = 1;
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
        	System.out.println(entry.getKey() + " " + entry.getValue());
            JLabel recordLabel = new JLabel("#" + entry.getKey() + ": " + entry.getValue());
            recordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = i+1;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(recordLabel, gbc);
            ++i;
        }

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        contentPane.add(exitButton, gbc);
    }
    
    private void initListeners() {
    	// Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking();
                    setVisible(false);
            }
        });
    }
}
