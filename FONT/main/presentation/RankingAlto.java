package main.presentation;

import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class RankingAlto extends JFrame {
    private static final long serialVersionUID = 1L;
    private TreeMap<String, Integer> ranking = new TreeMap<>();

    public RankingAlto(TreeMap<String, Integer> gettedRanking) {
        // Configurar la ventana
        setTitle("Ranking Nivel Alto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
        
        ranking = gettedRanking;
        
        // Crear los componentes
        JButton exitButton = new JButton("Salir");
        JLabel messageLabel = new JLabel("Ranking Nivel Alto:");    
        Font labelFont = messageLabel.getFont();
        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        
        // Crear el panel contenedor y configurar el administrador de diseño
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
        
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);
        
        /*int[] values = new int[10];
        for(int i = 0; i < 10; ++i) values[i] = -1;
        //obtenemos los valores del treemap
        for (int i = 0; i < 10; i++) {
            if (ranking.containsKey(Integer.toString(i))) {
                values[i] = ranking.get(Integer.toString(i));
            }
        }
        
        //obtenemos los nombres de los jugadores
        String[] nicknames = new String[10];
        for(int i = 0; i < 10; ++i) nicknames[i] = "unvalidNickname";
        int count = 0;
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
            if (count >= 10 || count >= ranking.size()) {
                break;
            }
            nicknames[count] = entry.getKey();
            count++;
        }

        for (int i = 0; i < 10; ++i) {
            if (values[i] == -1) {
                break;
            }
            JLabel recordLabel = new JLabel("#" + i + ": " + nicknames[i] + " - " + values[i]);
            recordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 18));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = i++;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(recordLabel, gbc);
        }*/

        int i = 1;
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
        	System.out.println(entry.getKey() + " " + entry.getValue());
            JLabel recordLabel = new JLabel("#" + entry.getKey() + ": " + entry.getValue());
            recordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = i++;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(recordLabel, gbc);
        }
        
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        contentPane.add(exitButton, gbc);


        
     // Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaRanking();
                    setVisible(false);
            }
        });
    }
}
