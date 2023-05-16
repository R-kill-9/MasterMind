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
    private JTextField gameField;
    private JButton acceptButton;
    private JButton exitButton;
    private ArrayList<Pair<String, Date>>  partidas = CtrlPresentacion.getPartidasGuardadas();
    private JPanel contentPane = new JPanel();

    public CargarPartida() {
    	initComponents();
        initListeners();
    }
    
    private String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    private void initComponents() {
    	// Configurar la ventana
        setTitle("Cargar Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);
        
        // Crear los componentes
        exitButton = new JButton("Salir");
        acceptButton = new JButton("Jugar");
        gameField = new JTextField(20);    
        
        
        // Configurar el administrador de diseño
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
        

        
		ArrayList<Date> fechas = new ArrayList<>();
		for (Pair<String, Date> partida : partidas) {
		    fechas.add(partida.getSecond());
		}
		String totalPartidas = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		for (int i = 0; i < partidas.size(); ++i) {
			String fecha = sdf.format(fechas.get(i));
			totalPartidas = totalPartidas + System.lineSeparator() + fecha;
		}
		
		for (int i = 0; i < 10 && i < fechas.size(); ++i) {
        	System.out.println(i + " " + fechas.get(fechas.size()-1-i));
            JLabel partidaLabel = new JLabel("#" + (i+1) + ": " + fechas.get(fechas.size()-1-i));
            partidaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = i++;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(partidaLabel, gbc);
        }
        
		gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(new JLabel("Introduce número de partida: "), gbc);
        
		gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(gameField, gbc);
        
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        add(acceptButton, gbc);
        
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 5, 5, 5);
        add(exitButton, gbc);
    }
    
    private void initListeners() {
    	// Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
       
        
     // Configurar el ActionListener para el botón de accept/jugar
        acceptButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String partida = gameField.getText();
                if (partida.isEmpty()) {
                    JOptionPane.showMessageDialog(CargarPartida.this, "Por favor ingresa un nombre de partida.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	//AÑADIR FUNCION PARA CARGAR PARTIDA
                    setVisible(false);
                }
            }
        });
    }
}
