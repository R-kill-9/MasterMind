package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class LoginScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JButton loginButton;

    public LoginScreen() {
        // Configurar la ventana
        setTitle("Inicio de Sesión");
        setSize(400, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 5, 10); // Agregamos margen entre los componentes

        // Crear los componentes
        usernameField = new JTextField(20);
        
        loginButton = new JButton("Iniciar Sesión");
        JButton rankingButton = new JButton("Ver ranking");
        rankingButton.setForeground(Color.RED); // RGB: blanco, alpha: 0
        rankingButton.setOpaque(false);
        rankingButton.setContentAreaFilled(false); // Hacer el área de contenido transparente
        rankingButton.setBorderPainted(false); // Quitar el bord
        rankingButton.setFont(rankingButton.getFont().deriveFont(Font.BOLD, 18));
        JLabel welcomeIcon = new JLabel("         \u265A");
        JLabel welcomeLabel = new JLabel("Welcome to Mastermind!");

        // Configurar el diseño con GridBagLayout
        setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(new JLabel("Nombre de Usuario: "), gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(usernameField, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
   
        gbc.gridy = 6;
        gbc.gridx = 0; // Agregar esta línea para establecer la posición en la columna 0
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Cambiar el valor de anchor a CENTER
        add(rankingButton, gbc);
        
        welcomeLabel.setOpaque(false);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 24));
        welcomeIcon.setFont(welcomeIcon.getFont().deriveFont(Font.BOLD, 50));
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);
        
        
        gbc.gridy = 1;
        gbc.gridx = 0; // Agregar esta línea para establecer la posición en la columna 0
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeIcon, gbc);

        // Configurar el ActionListener para el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Por favor ingresa un nombre de usuario.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Crear una instancia del juego Mastermind
                	CtrlPresentacion.carregarVistaConfiguracion();
                	try {
						CtrlPresentacion.loginUser(username);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    setVisible(false);
                }
            }
        });
    }



    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    }
}
