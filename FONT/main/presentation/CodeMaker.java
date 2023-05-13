package main.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CodeMaker extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel botonWelcome;
	private Color colorSeleccionado;
	private Color[] tablero;
	private static Integer NUMERO_COLUMNAS;
	private final Integer NUMERO_FILAS = 1;
	
	public CodeMaker(Integer numCols) {
	    setTitle("Mastermind");
	    setSize(300, 600); 
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
	    NUMERO_COLUMNAS = numCols-1;
	    JPanel panelTablero = new JPanel(new GridLayout(1, numCols));
	    int margen = 20;
	    int panelTableroWidth = 400;
	    int panelTableroHeight = 100;
	    panelTablero.setPreferredSize(new Dimension(panelTableroWidth, panelTableroHeight));
	    panelTablero.setBackground(new Color(139, 69, 19));
	    panelTablero.setBorder(BorderFactory.createLineBorder(Color.WHITE, margen));

	    tablero = new Color[numCols];
	    for (int i = 0; i < numCols; i++) {
	        tablero[i] = Color.WHITE;
	    }
	    dibujarTablero(panelTablero);

	    botonWelcome = new JLabel("Selecciona un color para empezar");
	    Font font = new Font(botonWelcome.getFont().getName(), Font.BOLD, 15);
	    botonWelcome.setFont(font);

	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.add(panelTablero);


	    JPanel panelBolas = new JPanel(new FlowLayout());
	    Bola bola1 = new Bola(Color.GREEN, false, true, 0, -1);
	    panelBolas.add(bola1);
	    bola1.setOpaque(false);
	    Bola bola2 = new Bola(Color.MAGENTA, false, true, 0, -1);
	    panelBolas.add(bola2);
	    bola2.setOpaque(false);
	    Bola bola3 = new Bola(Color.RED, false, true, 0, -1);
	    panelBolas.add(bola3);
	    bola3.setOpaque(false);
	    Bola bola4 = new Bola(Color.BLUE, false, true, 0, -1);
	    panelBolas.add(bola4);
	    bola4.setOpaque(false);
	    Bola bola5 = new Bola(Color.ORANGE, false, true, 0, -1);
	    panelBolas.add(bola5);
	    bola5.setOpaque(false);
	    Bola bola6 = new Bola(Color.cyan, false, true, -1, -1);
	    panelBolas.add(bola6);
	    bola6.setOpaque(false);
	    panelBolas.setAlignmentX(Component.RIGHT_ALIGNMENT);

	    bola1.addMouseListener(new BolaMouseListener());
	    bola2.addMouseListener(new BolaMouseListener());
	    bola3.addMouseListener(new BolaMouseListener());
	    bola4.addMouseListener(new BolaMouseListener());
	    bola5.addMouseListener(new BolaMouseListener());
	    bola6.addMouseListener(new BolaMouseListener());
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
	    add(botonWelcome, gbc);
	    
	    gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
	    add(panelTablero, gbc);
	    
	    gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
	    add(panelBolas, gbc);
	    
	    JButton botonSalir = new JButton("Volver al menú principal");
	    botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame("Mi Ventana");
                JDialog dialogo = new JDialog(frame, "Confirmación", true);
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                JLabel mensaje = new JLabel("<html><div style='text-align: center;'>¿Quieres guardar la partida?<br></div></html>");
                mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(mensaje);
                panel.add(Box.createVerticalStrut(10));
                JPanel panelBotones = new JPanel();
                panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton botonGuardar = new JButton("Guardar");
                botonGuardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CtrlPresentacion.carregarVistaMenu();
                        dialogo.dispose();
                        setVisible(false);
                    }
                });
                JButton botonNoGuardar = new JButton("No guardar");
                botonNoGuardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	CtrlPresentacion.carregarVistaMenu();
                        dialogo.dispose();
                        setVisible(false);
                    }
                });
                panelBotones.add(botonNoGuardar);
                panelBotones.add(botonGuardar);
                panel.add(panelBotones);
                dialogo.setContentPane(panel);
                dialogo.pack();
                dialogo.setLocationRelativeTo(frame);
                dialogo.setVisible(true);
            }
        });
        JPanel panelSalir = new JPanel(new BorderLayout());
        panelSalir.add(botonSalir, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        add(panelSalir, gbc);

	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.setOpaque(true);
	    botonAceptar.setBackground(Color.white);
	    panelTablero.add(botonAceptar, BorderLayout.EAST);
	    botonAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Integer x = CtrlPresentacion.setSolution(tablero);
	            System.out.println("Num rondas -> "  + x);
	            if(x != null) {
		            CtrlPresentacion.cargaCombMaquina();
		            setVisible(false);
	            }
	        }
	    });
	    pack();
	}
	
	private class BolaMouseListener extends MouseAdapter {
		
        @Override
        public void mouseClicked(MouseEvent e) {
        	 super.mouseClicked(e);
             Bola bola = (Bola) e.getComponent();
             Integer numFila = bola.getFila();
             System.out.println(bola.esColorTablero());
             System.out.println(bola.isDisabled());
             
	         if(bola.esColorTablero() && numFila == 0) {
	         		if(colorSeleccionado != null) {
	         			System.out.println("entro");
	         			bola.setColor(colorSeleccionado);
	         			Integer numColumna = bola.getColumna();
	         			tablero[numColumna] = colorSeleccionado;
	         		}
	         }
	         else{
	         	colorSeleccionado = bola.getColor();
	         	System.out.println("entroxx");
	         	botonWelcome.setBackground(colorSeleccionado);
	         	botonWelcome.setForeground(botonWelcome.getBackground());
	         	botonWelcome.revalidate();
	         	String nombreColor = "";
	         	if (colorSeleccionado.equals(Color.RED)) {
	         	    nombreColor = "Rojo";
	         	} else if (colorSeleccionado.equals(Color.GREEN)) {
	         	    nombreColor = "Verde";
	         	} else if (colorSeleccionado.equals(Color.BLUE)) {
	         	    nombreColor = "Azul";
	         	} else if (colorSeleccionado.equals(Color.MAGENTA)) {
	         	    nombreColor = "Rosa";
	         	} else if (colorSeleccionado.equals(Color.ORANGE)) {
	         	    nombreColor = "Naranja";
	         	} else if (colorSeleccionado.equals(Color.CYAN)) {
	         	    nombreColor = "Cyan";
	         	}
	         	botonWelcome.setText("Color seleccionado -> " + nombreColor);
	  			System.out.println(botonWelcome.getBackground());
	         }
	     	repaint();
        }
    }
	private void dibujarTablero(JPanel panelTablero) {
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                	JPanel bolaPanel = new JPanel();
                    bolaPanel.setPreferredSize(new Dimension(25, 25));
                    bolaPanel.setBackground(new Color(139, 69, 19).darker());
                    panelTablero.add(bolaPanel);
                    Bola bola = new Bola(new Color(139, 69, 19).darker(), false, i == 0, j , i);
                    bola.addMouseListener(new BolaMouseListener());
                    bola.setColorTablero();
                    bola.setOpaque(false);
                    bolaPanel.add(bola);
                }
            }
        }
 
	public static void main(String[] args) {
        CodeMaker codeMaker = new CodeMaker(NUMERO_COLUMNAS);
        codeMaker.setVisible(true);
    }

}
