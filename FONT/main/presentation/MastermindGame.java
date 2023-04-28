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
    private JLabel botonWelcome;
    private Integer filasRest = 9;
    private static Boolean setAyuda;
    		
    public MastermindGame(Integer numCols, Boolean ayuda) {
    	NUMERO_COLUMNAS = numCols == null ? 5 : numCols;
    	setAyuda = ayuda == null ? true : ayuda;
        setTitle("Mastermind");
        setSize(300, 600); // Establecer tamaño del JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panelPausa = new JPanel(new BorderLayout());
        panelPausa.setBackground(new Color(255, 255, 255, 128));
        panelPausa.setVisible(false);
        add(panelPausa, BorderLayout.CENTER); 
        panelPausa.setBounds(0, 0, 460, 800); 
        add(panelPausa, 0);


        JPanel panelTablero = new JPanel(new GridLayout(NUMERO_FILAS, NUMERO_COLUMNAS));
        solucion = new Bola[NUMERO_FILAS][NUMERO_COLUMNAS];
        int margen = 20; 
        int panelTableroWidth = 200;
        int panelTableroHeight = 600;
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
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelBolas = new JPanel(new FlowLayout());
        Bola bola1 = new Bola(Color.GREEN, false, true, 0, 0);
        
        panelBolas.add(bola1,gbc);
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
        JButton jPaused = new JButton("Pausar");
        Dimension d = new Dimension(10, 0);
        jPaused.setPreferredSize(d);
        
        
        bola1.addMouseListener(new BolaMouseListener());
        bola2.addMouseListener(new BolaMouseListener());
        bola3.addMouseListener(new BolaMouseListener());
        bola4.addMouseListener(new BolaMouseListener());
        bola5.addMouseListener(new BolaMouseListener());
        bola6.addMouseListener(new BolaMouseListener());

        JButton botonAyuda = new JButton("  Ayuda  ");
        botonAyuda.setPreferredSize(d);
        JButton botonSalir = new JButton("    Salir    ");
        JPanel panelSalir = new JPanel(new BorderLayout());
        panelSalir.add(botonSalir, BorderLayout.CENTER);

        // Crear panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
        JButton submitB = new JButton("Aceptar");
   
        panelBolas.add(submitB);
        
        jPaused.setPreferredSize(d);
        panelSubBotones.add(jPaused);
        JButton botonPausar = new JButton("  Pausar  ");
        JButton botonReiniciar = new JButton("Reiniciar");

        botonSalir.addActionListener(e -> System.exit(0));
        botonReiniciar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	CtrlPresentacion.reiniciarPartida();
        	setVisible(false);
        	dispose();
        	
        }});
        botonAyuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame("Mi Ventana");
                JDialog dialogo = new JDialog(frame, "Confirmación", true);
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                JLabel mensaje = new JLabel("<html><div style='text-align: center;'>¿Quieres solicitar ayuda?<br>Tu puntuación se reducirá a la mitad</div></html>");
                mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(mensaje);
                panel.add(Box.createVerticalStrut(10));
                JPanel panelBotones = new JPanel();
                panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton botonSi = new JButton("Sí");
                botonSi.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CtrlPresentacion.setAyudaPartida();
                        dialogo.dispose();
                    }
                });
                JButton botonNo = new JButton("No");
                botonNo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialogo.dispose();
                    }
                });
                panelBotones.add(botonSi);
                panelBotones.add(botonNo);
                panel.add(panelBotones);
                dialogo.setContentPane(panel);
                dialogo.pack();
                dialogo.setLocationRelativeTo(frame);
                dialogo.setVisible(true);
            }
        });
        submitB.addActionListener(new ActionListener() {
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
        botonPausar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelPausa.setVisible(true); // mostrar el panel de pausa
                panelTablero.setEnabled(false); // desactivar el tablero
                panelBolas.setEnabled(false); // desactivar las bolas
                panelBotones.setEnabled(false); // desactivar los botones
                botonSalir.setEnabled(false);
                botonAyuda.setEnabled(false);
                botonPausar.setEnabled(false);
                botonReiniciar.setEnabled(false);
                submitB.setEnabled(false);

            }
        });

        panelSubBotones.add(botonPausar);
        panelPausa.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        JButton botonReanudarPausa = new JButton("Reanudar");
        botonReanudarPausa.setPreferredSize(new Dimension(100, 50));
        botonReanudarPausa.setOpaque(true);
        botonReanudarPausa.setBorder(BorderFactory.createLineBorder(Color.GREEN)); 
        botonReanudarPausa.setBackground(Color.GREEN); 
        botonReanudarPausa.setForeground(Color.white); // texto negro
        botonReanudarPausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelPausa.setVisible(false);
                panelTablero.setEnabled(true);
                panelBolas.setEnabled(true);
                panelBotones.setEnabled(true);
                botonSalir.setEnabled(true);
                botonAyuda.setEnabled(true);
                botonPausar.setEnabled(true);
                botonReiniciar.setEnabled(true);
                submitB.setEnabled(true);

            }
        });
        panelPausa.add(botonReanudarPausa);
        
        JButton botonSalirPausa = new JButton("Salir");
        botonSalirPausa.setPreferredSize(new Dimension(100, 50));
        botonSalirPausa.setOpaque(true);
        botonSalirPausa.setBorder(BorderFactory.createLineBorder(Color.RED));
        botonSalirPausa.setBackground(Color.RED);
        botonSalirPausa.setForeground(Color.WHITE);
        botonSalirPausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelPausa.add(botonSalirPausa);


        
        Dimension botonSize = new Dimension(100, 30); 
        botonAyuda.setPreferredSize(botonSize);
        botonPausar.setPreferredSize(botonSize);
        botonReiniciar.setPreferredSize(botonSize);
        panelBotones.add(botonAyuda);
        panelBotones.add(botonPausar);
        panelBotones.add(botonReiniciar);

        // Crear panel para el sur
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelBotones, BorderLayout.EAST);
        panelSur.add(panelBolas, BorderLayout.CENTER);
        panelSur.add(panelSalir, BorderLayout.SOUTH);
        
        botonWelcome = new JLabel("Welcome to Mastermind!");
        System.out.println(botonWelcome.getBackground());
        
        botonWelcome.setLayout(new BoxLayout(botonWelcome, BoxLayout.X_AXIS));
        Font font = new Font(botonWelcome.getFont().getName(), Font.BOLD, 15);
        botonWelcome.setFont(font);
        
        add(botonWelcome, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);

        add(panelSur, BorderLayout.SOUTH);
        pack();
        
    }
    private void dibujarTablero(JPanel panelTablero) {
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                
                if (j == NUMERO_COLUMNAS - 1) {
                	Integer numF = setAyuda ? 2 : 1;
                	Integer numC = NUMERO_COLUMNAS - 1;
                	JPanel panelUltimaColumna = new JPanel(new GridLayout(numF, numC)); 
                	panelUltimaColumna.setLayout(new BoxLayout(panelUltimaColumna, BoxLayout.X_AXIS));

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
	         	botonWelcome.setBackground(colorSeleccionado);
	         	botonWelcome.revalidate();
	  			System.out.println(botonWelcome.getBackground());
	         }
	     	repaint();
        }
    }
    public static void main(String[] args) {
        MastermindGame mastermindGame = new MastermindGame(NUMERO_COLUMNAS, setAyuda);
        mastermindGame.setVisible(true);
    }
}
