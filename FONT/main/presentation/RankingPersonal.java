package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingPersonal extends JFrame {
    private static final long serialVersionUID = 1L;
    private static int[] records;
    private JButton exitButton = new JButton("Salir");
    private JLabel messageLabel = new JLabel("Tus mejores puntuaciones son:");    
    private Font labelFont = messageLabel.getFont();
    private JPanel contentPane = new JPanel();

    public RankingPersonal(int[] gettedRecords) {
    	initComponents(gettedRecords);
    	initListeners();
    }
    
    private void initComponents(int[] gettedRecords) {
    	// Configurar la ventana
        setTitle("Records Personales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
        
        records = gettedRecords;
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
        
        for (int i = 0; i < records.length; i++) {
            int recordValue = records[i];
            JLabel recordLabel = new JLabel("#" + (i + 1) + ": " + recordValue);
            recordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 18));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 2; // Set to the center column index
            gbc.gridy = i+1;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(recordLabel, gbc);
        }
        
        gbc.gridx = 0;
        gbc.gridy = 8;
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
