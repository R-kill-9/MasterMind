package main.presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private JButton acceptButton = new JButton("Jugar");;
    private JButton exitButton = new JButton("Salir");;
    private ArrayList<String>  partidas = CtrlPresentacion.getPartidasGuardadas();
    private JPanel contentPanel = new JPanel();
    private ButtonGroup grupoOpciones = new ButtonGroup();
    private JRadioButton[] radioButtons = new JRadioButton[10];
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> dateList = new JList<>(listModel);
    private Integer selectedIndex = null; 
    private JScrollPane scrollPane = new JScrollPane(dateList);
    private JLabel selectedDateLabel = new JLabel(); 
    
    public CargarPartida() {
    	initComponents();
        initListeners();
    }
    
    private void initComponents() {
    	// Configurar la ventana
        configWindow();
        
    	selectedDateLabel.setText("<html><font color='red'><i>Por favor, seleccione una partida para continuar</i></font></html>");                     
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configurar el administrador de diseño
        
        configDesign(gbc);
        
        // 
        // Configurar el texto de salida
		setText(gbc);
    	
		// Configurar los botones de opción
        configGroupButtons(gbc);
        
        // Configurar botón de jugar
        configPlayButton(gbc);
        
        // Configurar botón de salir
        configExitButton(gbc);
    }
    
    
    //asigna el nombre correspondiente a cada botón y lo añade al grupo y al panel
    private void configGroupButtons(GridBagConstraints gbc) {
        int size = partidas.size();
        for (int i = 0; i < 10 && i < size; ++i) {
            radioButtons[i] = new JRadioButton(partidas.get(partidas.size() - 1 - i));
            grupoOpciones.add(radioButtons[i]);
            gbc.gridx = 0;
            gbc.gridy = 2 + i;
            gbc.anchor = GridBagConstraints.CENTER;
            if (i == 0) gbc.insets = new Insets(35, 0, 5, 0);
            else gbc.insets = new Insets(5, 0, 5, 0);
            
            String dateTime = partidas.get(partidas.size() - 1 - i);
            String[] parts = dateTime.split("_");
            String date = parts[0];
            String time = parts[1];
            String[] partsTime = time.split("-");
            String newTime = partsTime[0] + ":" + partsTime[1] + ":" + partsTime[2];

            String elementToAdd = "<html><b>Fecha:</b> " + date + "    <b>Hora:</b> " + newTime + "</html>";

            listModel.addElement(elementToAdd);
            

        }
        contentPanel.add(scrollPane);
    }
   
    
    //configura la ventana
    private void configWindow() {
    	setTitle("Cargar Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(470, 600);
        setLocationRelativeTo(null);
    }
    
    
  //setea el texto dependiendo de si hay partidas existentes o no
    private void setText(GridBagConstraints gbc) {
        JLabel messageLabel = new JLabel();
        Font labelFont = messageLabel.getFont();
        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
        
        if (partidas.size() >= 1) messageLabel.setText("Partida seleccionada: ");

        else messageLabel.setText("No hay partidas guardadas");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(messageLabel, gbc);
    }
    
    
    //configura el diesño de la ventana
    private void configDesign(GridBagConstraints gbc) {
        setContentPane(contentPanel);
        contentPanel.setLayout(new GridBagLayout());

        // Configurar el tamaño preferido del JScrollPane
        scrollPane.setPreferredSize(new Dimension(230, partidas.size() * 25));

        // Agregar "Escoge partida" en la primera fila
       
        
        // Agregar el JLabel de fecha seleccionada en la segunda fila
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(selectedDateLabel, gbc);

        // Agregar el JScrollPane en la tercera fila
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);
    }
    
    
    
    //configura el botón de jugar(aceptar) y lo añade en el panel
    private void configPlayButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
	    gbc.gridy = 12;
	    gbc.insets = new Insets(20, 0, 100, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		acceptButton.setPreferredSize(new Dimension(200, 35));
		Color greenColor = new Color(0, 200, 0);
		acceptButton.setBackground(greenColor);
		acceptButton.setForeground(Color.WHITE);
		acceptButton.setFocusPainted(false);
        acceptButton.setBorderPainted(false);
        
        acceptButton.setOpaque(true);

        // Añadir el botón de aceptar solo si se pueden seleccionar partidas
        if (partidas.size() >= 1) contentPanel.add(acceptButton, gbc);
    }
    
    
    //configura el botón de salir y lo añade al panel
    private void configExitButton(GridBagConstraints gbc) {
    	gbc.gridx = 0;
	    gbc.gridy = 20;
        Color redColor = new Color(200, 0, 0);

	    exitButton.setBackground(redColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        
        exitButton.setOpaque(true);
        contentPanel.add(exitButton, gbc);
    }
    
    
    //inicializa los listeners del boton de exit y del grupo de botones
    private void initListeners() {
    	// Configurar el ActionListener para el botón de exit
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.carregarVistaMenu();
                    setVisible(false);
            }
        });
        
        dateList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	 int selectedI = dateList.getSelectedIndex();
                	 selectedIndex = selectedI;
                }
            }
        });
        
        dateList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedI = dateList.getSelectedIndex();
                    selectedIndex = selectedI;

                    if (selectedI != -1) {
                        String selectedDate = partidas.get(partidas.size() - 1 - selectedI);
                        String[] parts = selectedDate.split("_");
                        String date = parts[0];
                        String time = parts[1];
                        String[] partsTime = time.split("-");
                        String newTime = partsTime[0] + ":" + partsTime[1] + ":" + partsTime[2];
                        String elementToAdd = date + " - "+ newTime;selectedDateLabel.setText("<html><font color='#404040'><i>Partida seleccionada, empezó: " + elementToAdd + "</i></font></html>");

                    } else {
                    	selectedDateLabel.setText("<html><font color='red'>Por favor, seleccione una partida para continuar</font></html>");                     
                    }
                }
            }
        });
       
  
     // Configurar el ActionListener para el botón de jugar con sus múltiples opciones
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (selectedIndex != null) CtrlPresentacion.cargarPartida(partidas.get(selectedIndex));
            }
        });
    }
}