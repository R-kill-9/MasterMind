package main.presentation;

import javax.swing.*;
import java.text.SimpleDateFormat;
import main.domain.Pair;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class CargarPartida extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField gameField;
    private JButton acceptButton;
    private JButton exitButton;
    private JButton partidasButton;

    public CargarPartida() {
        // Configurar la ventana
        setTitle("Cargar Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 600);
        
        setLocationRelativeTo(null);
        // Crear los componentes
        exitButton = new JButton("Salir");
        acceptButton = new JButton("Jugar");
        partidasButton = new JButton("Ver Partidas Guardadas");
        gameField = new JTextField(20);    
        
        
        // Crear el panel contenedor y configurar el administrador de diseño
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // reducir los márgenes verticales
        
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(new JLabel("Introduce el nombre de la partida: "), gbc);
        
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(gameField, gbc);
        
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(acceptButton, gbc);
        
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(partidasButton, gbc);
        
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 5, 5, 5);
        add(exitButton, gbc);
        
        

        
     // Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
        
     // Configurar el ActionListener para el botón de partidas
        partidasButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<Pair<String, Date>>  partidas = CtrlPresentacion.getPartidasGuardadas();
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
            	
            	JOptionPane.showMessageDialog(null, "PARTIDAS GUARDADAS:" + System.lineSeparator() +  totalPartidas,
                        "Partidas Guardadas", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
        
     // Configurar el ActionListener para el botón de accept/jugar
        acceptButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String username = gameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(CargarPartida.this, "Por favor ingresa un nombre de partida.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	//AÑADIR FUNCION PARA CARGAR PARTIDA
                    setVisible(false);
                }
            }
        });
    }
    
    private String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
    public static void main(String[] args) {
        CargarPartida partida = new CargarPartida();
        partida.setVisible(true);
    }
}
