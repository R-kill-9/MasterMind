package main.presentation;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class TurnosMaquina extends JFrame {
    private static final long serialVersionUID = 1L;
    private static Color[][] tablero;
    private static final int NUMERO_FILAS = 10;
    private static int NUMERO_COLUMNAS;

    public TurnosMaquina(Color[][] combinaciones) {
        setTitle("Mastermind");
       
        setSize(300, 600); // Establecer tamaño del JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        NUMERO_COLUMNAS = combinaciones[0].length;

        JPanel panelTablero = new JPanel(new GridLayout(NUMERO_FILAS, NUMERO_COLUMNAS));
        int margen = 20;
        int panelTableroWidth = 400;
        int panelTableroHeight = 600;
        panelTablero.setPreferredSize(new Dimension(panelTableroWidth, panelTableroHeight));
        // panelTablero.setBackground(new Color(139, 69, 19));
        panelTablero.setBorder(BorderFactory.createLineBorder(Color.WHITE, margen));

        tablero = new Color[NUMERO_FILAS][NUMERO_COLUMNAS];
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                tablero[i][j] = Color.WHITE;
            }
        }
        System.out.println(NUMERO_FILAS);
        System.out.println(NUMERO_COLUMNAS);
        System.out.println(combinaciones[0].length);
        for (int i = NUMERO_FILAS - 1; i >= 0; i--) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                JPanel bolaPanel = new JPanel();
                bolaPanel.setPreferredSize(new Dimension(40, 40));
                panelTablero.add(bolaPanel);
                Color bolaColor = new Color(139, 69, 19).darker(); // color de tablero
                Bola bola = new Bola(bolaColor, false, i == 0, j, i);
                bola.setColor(bolaColor);
                bola.setColorTablero();
                bola.setOpaque(false);
                bolaPanel.setBackground(new Color(139, 69, 19).darker());
                bolaPanel.add(bola);

                bola.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
                final int fila = i;
                final int col = j;
                if (combinaciones.length > i && combinaciones[i].length > j) {
                	
                    ActionListener taskPerformer = new ActionListener() {
                    	
                        int i = fila; // Acceder a la variable i de la iteración exterior
                        int j = col; // Acceder a la variable j de la iteración exterior
                        public void actionPerformed(ActionEvent evt) {
                        	System.out.println(i);
                            bola.setColor(combinaciones[i][j]);
                            bola.repaint(); // Llamar a repaint() para actualizar la vista
                        }
                    };
                    Timer timer = new Timer(1000*(i +1 ), taskPerformer);
                    timer.setRepeats(false); // Para que el temporizador se detenga después de una sola ejecución
                    timer.start();
                }
            }
        }
        JLabel botonWelcome = new JLabel("Resolviendo por la máquina...");
        Font font = new Font(botonWelcome.getFont().getName(), Font.BOLD, 15);
        botonWelcome.setFont(font);
        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.finRonda("CM");
                setVisible(false);
            }
        });
        add(botonWelcome, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(botonContinuar, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        TurnosMaquina turnosM = new TurnosMaquina(tablero);
        turnosM.setVisible(true);
    }
}

