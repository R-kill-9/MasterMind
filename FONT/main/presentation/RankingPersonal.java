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
    	configWindow();
        
        
        records = gettedRecords;
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        //configurar el administrador de diseño
        configDesign(gbc);
        
        //configura el mensaje 
        setMessage(gbc);
        
        //configura los records
        setRecords(gbc);
        
        //configura el botón de salida
        setExitButton(gbc);
    }
    
    
    //Configura la ventana indicando su título y tamaño.
    private void configWindow() {
    	setTitle("Records Personales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
    //Configura el diseño de la ventana, reduciendo los márgenes verticales.
    private void configDesign(GridBagConstraints gbc) {
    	setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
    }
    
    
    //Crea un JLabel para cada una de las cinco mejores puntuaciones del usuario. En caso de no tenerlas son 0.
    private void setRecords(GridBagConstraints gbc) {
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
    }
    
    
    //Configura el botón de salida, que te regresa a la ventana de Rankings y añade el botón al control Panel.
    private void setExitButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        contentPane.add(exitButton, gbc);
    }
    
    
    //Configura el mensaje que indica que estamos en la ventana de Records personales y lo añade al control Panel.
    private void setMessage(GridBagConstraints gbc) {
    	messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(messageLabel, gbc);
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
