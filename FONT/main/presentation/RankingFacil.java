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
    	configWindow();
        
        ranking = gettedRanking;
        GridBagConstraints gbc = new GridBagConstraints();
              
        //configurar el administrador de diseño
        configDesign(gbc);
        
        //Configura el mensaje 
        setMessage(gbc);

        //Configura el ranking
        setRanking(gbc);

        //Configura el botón de salida
        setExitButton(gbc);
    }
    
    //Configura la ventana indicando su título y tamaño.
    private void configWindow() {
    	setTitle("Ranking Nivel Fácil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
    //Configura el diseño de la ventana, reduciendo los márgenes verticales.
    private void configDesign(GridBagConstraints gbc) {
    	setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
    }
    
    
    //Configura el mensaje que indica en que nivel de Ranking te encuentras y lo añade al control Panel.
    private void setMessage(GridBagConstraints gbc) {
    	messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
    	gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);
    }
    
    
    //Crea un JLabel para cada posición en  el ranking y lo añade al control Panel.
    private void setRanking(GridBagConstraints gbc) {
    	int i = 1;
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
        	System.out.println(entry.getKey() + " " + entry.getValue());
            JLabel recordLabel = new JLabel("#" + i + " " + entry.getKey() + ": " + entry.getValue());
            recordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 17));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = i+1;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(recordLabel, gbc);
            ++i;
        }
    }
    
    
    //Configura el botón de salida, que te regresa a la ventana de Rankings y añade el botón al control Panel.
    private void setExitButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        contentPane.add(exitButton, gbc);
    }
    
    
    //Inicializa el listener para salir de esta pantalla y volver a la pantalla de rankings al clicar en el botón de salida. 
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
