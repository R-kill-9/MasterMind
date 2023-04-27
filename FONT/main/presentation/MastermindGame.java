package main.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MastermindGame extends JFrame {
	private Color colorSeleccionado;
	
	private static final long serialVersionUID = 1L;

    //private static final int TAMAÑO_BOLA = 50;
    private static final int NUMERO_FILAS = 10;
    private static int NUMERO_COLUMNAS;
    private Color[][] tablero;
    private Bola[][] solucion;
    private Integer filasRest = 9;
    		
    public MastermindGame(Integer numCols) {
    	NUMERO_COLUMNAS = numCols;
        setTitle("Mastermind");
        setSize(400, 560); // Establecer tamaño del JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panelTablero = new JPanel(new GridLayout(NUMERO_FILAS, NUMERO_COLUMNAS));
        solucion = new Bola[NUMERO_FILAS][NUMERO_COLUMNAS];
        int margen = 20; // Margen para los bordes
        int panelTableroWidth = 400;
        int panelTableroHeight = 560;
        panelTablero.setPreferredSize(new Dimension(panelTableroWidth, panelTableroHeight)); 
        panelTablero.setBackground(new Color(139, 69, 19));
        panelTablero.setBorder(BorderFactory.createLineBorder(Color.WHITE, margen));

        tablero = new Color[NUMERO_FILAS][NUMERO_COLUMNAS];
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                tablero[i][j] = Color.WHITE;
            }
        }

        dibujarTablero(panelTablero);

        JPanel panelBolas = new JPanel(new FlowLayout());
        Bola bola1 = new Bola(Color.GREEN, false, true, 0, 0);
        panelBolas.add(bola1);
        bola1.setOpaque(false);
        Bola bola2 = new Bola(Color.MAGENTA, false, true, 0, 0);
        panelBolas.add(bola2);
        bola2.setOpaque(false);
        Bola bola3 = new Bola(Color.RED, false, true, 0, 0);
        panelBolas.add(bola3);
        bola3.setOpaque(false);
        Bola bola4 = new Bola(Color.BLUE, false, true, 0, 0);
        panelBolas.add(bola4);
        bola4.setOpaque(false);
        Bola bola5 = new Bola(Color.ORANGE, false, true, 0, 0);
        panelBolas.add(bola5);
        bola5.setOpaque(false);
        Bola bola6 = new Bola(Color.YELLOW, false, true, -1, -1);
        panelBolas.add(bola6);
        bola6.setOpaque(false);
        JPanel panelSubBotones = new JPanel();
        JButton jSubmit = new JButton("Aceptar");
        Dimension d = new Dimension(10, 0);
        jSubmit.setPreferredSize(d);
        
        
        bola1.addMouseListener(new BolaMouseListener());
        bola2.addMouseListener(new BolaMouseListener());
        bola3.addMouseListener(new BolaMouseListener());
        bola4.addMouseListener(new BolaMouseListener());
        bola5.addMouseListener(new BolaMouseListener());
        bola6.addMouseListener(new BolaMouseListener());

        JButton ayuda = new JButton("  Ayuda  ");
        JButton botonSalir = new JButton("    Salir    ");
        JPanel panelSalir = new JPanel(new BorderLayout());
        panelSalir.add(botonSalir, BorderLayout.CENTER);

        // Crear panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
       
    
        jSubmit.setPreferredSize(d);
        panelSubBotones.add(jSubmit);
        JButton botonAceptar = new JButton(" Aceptar ");
        JButton botonReanudar = new JButton("Reanudar");

        botonSalir.addActionListener(e -> System.exit(0));
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println(tablero[filasRest]);
            	Color[] feedBack = CtrlPresentacion.submit(tablero[filasRest]);
            	int pos = 0;
            	System.out.println(feedBack);
            	if(feedBack != null) {
            		for (Color c : feedBack) {
            			System.out.println(solucion[filasRest][pos]);
                		solucion[filasRest][pos].setColor(c);
                		++pos;
                	}
                	if(feedBack.length > 0) --filasRest;
                	repaint();
            	}
            	
            }
        });
        
        Dimension botonSize = new Dimension(100, 30); 
        ayuda.setPreferredSize(botonSize);
        botonAceptar.setPreferredSize(botonSize);
        botonReanudar.setPreferredSize(botonSize);
        panelBotones.add(ayuda);
        panelBotones.add(botonAceptar);
        panelBotones.add(botonReanudar);

        // Crear panel para el sur
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelBotones, BorderLayout.EAST);
        panelSur.add(panelBolas, BorderLayout.CENTER);
        panelSur.add(panelSalir, BorderLayout.SOUTH);
        
        
      
        add(panelTablero, BorderLayout.NORTH);

        add(panelSur, BorderLayout.SOUTH);
        pack();
    }
    private void dibujarTablero(JPanel panelTablero) {
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                
                if (j == NUMERO_COLUMNAS - 1) {
                	 JPanel panelUltimaColumna = new JPanel(new GridLayout(2,2)); 
                     panelUltimaColumna.setOpaque(true);
                     panelUltimaColumna.setBackground(new Color(139, 69, 19).darker());
                     for (int k = 0; k < NUMERO_COLUMNAS-1; k++) {
                         Bola bolaSol = new Bola(new Color(139, 69, 19).darker(), true, true, j, i);
                         bolaSol.addMouseListener(new BolaMouseListener());
                         bolaSol.setColorTablero();
                         bolaSol.setOpaque(false);
                         panelUltimaColumna.add(bolaSol);
                         panelUltimaColumna.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19).darker(), 5));
                         solucion[i][k] = bolaSol;
                     }
                     panelTablero.add(panelUltimaColumna);
                } else {
                	JPanel bolaPanel = new JPanel();
                    bolaPanel.setPreferredSize(new Dimension(50, 50));
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
    }
    


    private class BolaMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
        	 super.mouseClicked(e);
             Bola bola = (Bola) e.getComponent();
	         if(bola.esColorTablero() && !bola.isDisabled()) {
	         		if(colorSeleccionado != null) {
	         			bola.setColor(colorSeleccionado);
	         			Integer numFila = bola.getFila();
	         			Integer numColumna = bola.getColumna();
	         			tablero[filasRest][numColumna] = colorSeleccionado;
	         		}
	         }
	         else {
	         	colorSeleccionado = bola.getColor();
	         }
	     	repaint();
        }
    }
    public static void main(String[] args) {
        MastermindGame mastermindGame = new MastermindGame(NUMERO_COLUMNAS);
        mastermindGame.setVisible(true);
    }
}
