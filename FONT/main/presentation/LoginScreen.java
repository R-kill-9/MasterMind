package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class LoginScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField = new JTextField(20);;
    private JButton loginButton = new JButton("Iniciar Sesión");;
    private JLabel welcomeIcon = new JLabel("         \u265A");;
    private JLabel welcomeLabel = new JLabel("Welcome to Mastermind!");;
    

    public LoginScreen() {
    	initComponents();
    }
    
    private void initComponents() {
    	// Configurar la ventana
    	configWindow();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 5, 10); 

        setLayout(new GridBagLayout());
        
        configComponents(gbc);

        initLogInListener();
    }

    //Configura la ventana
    private void configWindow() {
    	setTitle("Inicio de Sesión");
        setSize(470, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    //Configura los componentes que conforman la ventana
    private void configComponents(GridBagConstraints gbc) {
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
   
        
        welcomeLabel.setOpaque(false);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 24));
        welcomeIcon.setFont(welcomeIcon.getFont().deriveFont(Font.BOLD, 50));
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);
        
        
        gbc.gridy = 1;
        gbc.gridx = 0; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeIcon, gbc);
    }
    
    
    //Configura el listener para el botón de login
    private void initLogInListener() {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Por favor ingresa un nombre de usuario.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	CtrlPresentacion.carregarVistaMenu();
                	try {
						CtrlPresentacion.loginUser(username);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                	
                    setVisible(false);
                    repaint();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    }

}
