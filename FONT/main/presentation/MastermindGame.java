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
    private JPanel panelPausa;
    private JPanel panelTablero;
    private JPanel panelBolas;
    private Bola bola1, bola2, bola3, bola4, bola5, bola6;
    private JButton botonSalir;
    private JPanel panelSalir;
    private JButton botonAyuda;
    private Dimension d; //dimesnion boton
    private JButton submitB;
    private JButton botonPausar;
    private JPanel panelBotones;
    private Dimension botonSize;
    private JButton botonReiniciar;
    private JButton botonReanudarPausa;
    private JButton botonSalirPausa;
    private JPanel panelSur;
    private Color[][] combHechasAnt;
    private Color[][] feedBack;
    		
    public MastermindGame(Integer numCols, Boolean ayuda, Color[][] colores, Color[][] feedBackProps) {
    	combHechasAnt = colores;
    	feedBack = feedBackProps;
    	Integer filasComb = 0;
    	if(colores != null) filasComb = colores.length;
    	filasRest = filasRest - filasComb;
    	initComponents(numCols, ayuda);
    }
    
    private void initComponents(Integer numCols, Boolean ayuda) {
    	NUMERO_COLUMNAS = numCols == null ? 5 : numCols;
    	setAyuda = ayuda == null ? true : ayuda;
    
    	configWindow();
        
        pausePanel();

        boardPanel();
        
        GridBagConstraints gbc = new GridBagConstraints();

        ballsPanel(gbc);
        
        //DIMESIONES
        botonSize = new Dimension(100, 30); 
        d = new Dimension(10, 0);
        
        
        JPanel panelSubBotones = new JPanel();
        JButton jPaused = new JButton("Pausar");
        
        jPaused.setPreferredSize(d);
        
        ballsListeners();

        // Crear panel para los botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
        configPause();
        //salir
        configExit();
        
        //ayuda
        configHelp();
        
        //submit
        configSubmit();

        jPaused.setPreferredSize(d);
        panelSubBotones.add(jPaused);
        
        //pausar
        configPause();
        panelSubBotones.add(botonPausar);
        //reiniciar
        configRestart();
        
        //gestión pausa
        pauseGestion();
        
        // Crear panel para el sur
        configSouthPanel();
        
        //Boton superior
        configMessage();
        
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
                         if (feedBack != null && (NUMERO_FILAS - i) <= feedBack.length) {
                         	int combHechasAntFila = NUMERO_FILAS - i - 1;
                             int combHechasAntColumna = k;
                             if (combHechasAntColumna < feedBack[combHechasAntFila].length) {
                                 bolaSol.setColor(feedBack[combHechasAntFila][combHechasAntColumna]);
                                 bolaSol.changeDisabled(true);
                                 bolaSol.repaint();
                             }
                         }
                     }
                     panelTablero.add(panelUltimaColumna);
                } else {
                	JPanel bolaPanel = new JPanel();
                    bolaPanel.setPreferredSize(new Dimension(25, 25));
                    bolaPanel.setBackground(new Color(139, 69, 19).darker());
                    panelTablero.add(bolaPanel);
                    Color colorBola;
                    if (combHechasAnt != null && combHechasAnt.length > i && combHechasAnt[i].length > j) {
                    	colorBola = combHechasAnt[i][j];
                    }
                    else colorBola = new Color(139, 69, 19).darker();
                    Bola bola = new Bola(colorBola, false, i == NUMERO_FILAS - 1 - filasRest, j , i);
                    bola.addMouseListener(new BolaMouseListener());
                    bola.setColorTablero();
                    bola.setOpaque(false);
                    bolaPanel.add(bola);
                    if (combHechasAnt != null && (NUMERO_FILAS - i) <= combHechasAnt.length) {
                    	int combHechasAntFila = NUMERO_FILAS - i - 1;
                        int combHechasAntColumna = j;
                        if (combHechasAntColumna < combHechasAnt[combHechasAntFila].length) {
                            bola.setColor(combHechasAnt[combHechasAntFila][combHechasAntColumna]);
                            bola.changeDisabled(true);
                            bola.repaint();
                        }
                    }
                }
            }
        }
    } 
    private class BolaMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
        	 super.mouseClicked(e);
             Bola bola = (Bola) e.getComponent();
             Integer numFila = bola.getFila();
             System.out.println(filasRest + "numfila" + numFila);
	         if(bola.esColorTablero() && !bola.isDisabled() && numFila == filasRest || numFila == filasRest) {
	         		if(colorSeleccionado != null) {
	         			bola.setColor(colorSeleccionado);
	         			Integer numColumna = bola.getColumna();
	         			System.out.println("COLUMNA"+ numColumna);
	         			tablero[filasRest][numColumna] = colorSeleccionado;
	         		}
	         }
	         else{
	         	colorSeleccionado = bola.getColor();
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
    
    
    //Configura la ventana
    private void configWindow() {
    	setTitle("Mastermind");
        setSize(300, 600); // Establecer tamaño del JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    
    
    //Configura el panel de pausa
    private void pausePanel() {
    	panelPausa = new JPanel(new BorderLayout());
        panelPausa.setBackground(new Color(255, 255, 255, 128));
        panelPausa.setVisible(false);
        add(panelPausa, BorderLayout.CENTER); 
        panelPausa.setBounds(0, 0, 460, 800); 
        add(panelPausa, 0);
    }
    
    
    //Configura el panel del tablero y llama la función encargada de dibujarlo
    private void boardPanel() {
    	panelTablero = new JPanel(new GridLayout(NUMERO_FILAS, NUMERO_COLUMNAS));
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
    }
    
    
    //Configura el panel de bolas y le añade las bolas
    private void ballsPanel(GridBagConstraints gbc)
    {
    	panelBolas = new JPanel(new FlowLayout());
        bola1 = new Bola(Color.GREEN, false, true, 0, 0);
        panelBolas.add(bola1,gbc);
        bola1.setOpaque(false);
        bola2 = new Bola(Color.MAGENTA, false, true, 0, 0);
        panelBolas.add(bola2);
        bola2.setOpaque(false);
        bola3 = new Bola(Color.RED, false, true, 0, 0);
        panelBolas.add(bola3);
        bola3.setOpaque(false);
        bola4 = new Bola(Color.BLUE, false, true, 0, 0);
        panelBolas.add(bola4);
        bola4.setOpaque(false);
        bola5 = new Bola(Color.ORANGE, false, true, 0, 0);
        panelBolas.add(bola5);
        bola5.setOpaque(false);
        bola6 = new Bola(Color.cyan, false, true, -1, -1);
        panelBolas.add(bola6);
        bola6.setOpaque(false);
    }
    
    
    //añade los listeners a las bolas
    private void ballsListeners() {
    	bola1.addMouseListener(new BolaMouseListener());
        bola2.addMouseListener(new BolaMouseListener());
        bola3.addMouseListener(new BolaMouseListener());
        bola4.addMouseListener(new BolaMouseListener());
        bola5.addMouseListener(new BolaMouseListener());
        bola6.addMouseListener(new BolaMouseListener());
    }
    
    //Configura el botón de salida
    private void configExit() {
    	botonSalir = new JButton("    Menú    ");
        panelSalir = new JPanel(new BorderLayout());
        panelSalir.add(botonSalir, BorderLayout.CENTER);
        //LISTENER
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
                panelBotones = new JPanel();
                panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton botonGuardar = new JButton("Guardar");
                botonGuardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	//GUARDA LA PARTIDA EN LA PERSISTENCIA
                    	CtrlPresentacion.guardarPartida();
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
    }
    
    
    //Configura el botón de ayuda
    private void configHelp() {
    	botonAyuda = new JButton("  Ayuda  ");
        botonAyuda.setPreferredSize(d);
        botonAyuda.setPreferredSize(botonSize);
        panelBotones.add(botonAyuda);
        //LISTENER
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
                panelBotones = new JPanel();
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
    }
    
    //Configura el botón de submit
    private void configSubmit() {
    	submitB = new JButton("Aceptar");
    	panelBolas.add(submitB);
    	//LISTENER
    	submitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Color[] feedBack = CtrlPresentacion.submit(tablero[filasRest]);
            	int pos = 0;
            	Boolean finished = true;
            	if(feedBack != null) {
            		for (Color c : feedBack) {
                		solucion[filasRest][pos].setColor(c);
                		++pos;
                		if(!c.equals(Color.black)) finished = false;
                	}
                	repaint();
                	if(feedBack.length > 0) --filasRest;
                	if(finished || filasRest < 0) {
                		JFrame frame = new JFrame("Mi Ventana");
                        JDialog dialogo = new JDialog(frame, "Has ganado!", true);
                        JPanel panel = new JPanel();
                        panel.setSize(400,400);
                        String text = filasRest < 0 && !finished ? "<html><div style='text-align: center;'>¡Oups!<br>Te has quedado sin intentos :(</div></html>" : "<html><div style='text-align: center;'>¡HAS GANADO!<br>Felicidades👑</div></html>";
                        JLabel mensaje = new JLabel(text);
                        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
                        panel.add(mensaje);
                        panel.add(Box.createVerticalStrut(10));
                        panelBotones = new JPanel();
                        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
                        JButton botonContinuar = new JButton("Continuar");
                        botonContinuar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                CtrlPresentacion.finRonda("CB");
                                dialogo.dispose();
                                setVisible(false);
                            }
                        });
                        panelBotones.add(botonContinuar);
                        panel.add(panelBotones);
                        dialogo.setContentPane(panel);
                        dialogo.pack();
                        dialogo.setLocationRelativeTo(frame);
                        dialogo.setVisible(true);
                	}
                	repaint();
            	}
            }
        });
    }
    
    //Configura lo relativo al botón de pausa
    private void configPause() {
    	botonPausar = new JButton("  Pausar  ");
        botonPausar.setPreferredSize(botonSize);
        panelBotones.add(botonPausar);
    	//LISTENER
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
    }
    
    
    //Configura el botón de reiniciar
    private void configRestart() {
    	botonReiniciar = new JButton("Reiniciar");
    	botonReiniciar.setPreferredSize(botonSize);
        panelBotones.add(botonReiniciar);
    	//LISTENER
    	botonReiniciar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CtrlPresentacion.reiniciarPartida();
        		setVisible(false);
        		dispose();
        }});
    }
    
    
    //Gestiona la configuración de la ventana cuando se hace pause
    private void pauseGestion() {
    	panelPausa.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        botonReanudarPausa = new JButton("Reanudar");
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
        
        botonSalirPausa = new JButton("Salir");
        botonSalirPausa.setPreferredSize(new Dimension(100, 50));
        botonSalirPausa.setOpaque(true);
        botonSalirPausa.setBorder(BorderFactory.createLineBorder(Color.RED));
        botonSalirPausa.setBackground(Color.RED);
        botonSalirPausa.setForeground(Color.WHITE);
        botonSalirPausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.guardarPartida();
                CtrlPresentacion.carregarVistaMenu();
                setVisible(false);
            }
        });
        panelPausa.add(botonSalirPausa);
    }
    
    
    //COnfigura el panel inferior de la pantalla
    private void configSouthPanel() {
    	panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelBotones, BorderLayout.EAST);
        panelSur.add(panelBolas, BorderLayout.CENTER);
        panelSur.add(panelSalir, BorderLayout.SOUTH);
    }

    //Configura el mensaje de la parte superior de la pantalla
    private void configMessage() {
    	botonWelcome = new JLabel("Selecciona un color para empezar");
        botonWelcome.setBackground(Color.gray);
        botonWelcome.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Font font = new Font(botonWelcome.getFont().getName(), Font.BOLD, 15);
        botonWelcome.setFont(font);
    }
    
}
