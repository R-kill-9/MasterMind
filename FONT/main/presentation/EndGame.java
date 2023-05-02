package main.presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EndGame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JLabel titleLabel;
    private JLabel scoreLabel;
    private JButton backButton;
    
    public EndGame(Integer score) {
        super("Fin del juego");
        // Crear los componentes
        titleLabel = new JLabel("Fin del juego");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        scoreLabel = new JLabel("<html>Puntuación obtenida:<br><span style='font-size: 36px; color: red;'></span></html>");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setText(scoreLabel.getText().replace("</span>", score.toString() + "</span>"));
        
        backButton = new JButton("Volver al menu principal");
        
       
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	CtrlPresentacion.carregarVistaMenu();
            	setVisible(false);
            }});
        // Crear el panel principal y añadir los componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(backButton);
        mainPanel.add(Box.createVerticalGlue());

        // Añadir el panel principal a un panel intermedio que use FlowLayout para centrar los componentes
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(mainPanel);

        // Añadir el panel intermedio a la ventana
        getContentPane().add(centerPanel);

        // Centrar la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - getHeight()) / 2;
        setLocation(centerX, centerY);

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
}
