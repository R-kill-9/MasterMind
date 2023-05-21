package main.presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class pruebaVista {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    pruebaVista window = new pruebaVista();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public pruebaVista() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Crear botón rojo
        JButton btnRojo = new JButton("Rojo");
        btnRojo.setBounds(50, 50, 100, 30);
        btnRojo.setBackground(Color.RED);
        btnRojo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });
        frame.getContentPane().add(btnRojo);

        // Crear botón azul
        JButton btnAzul = new JButton("Azul");
        btnAzul.setBounds(160, 50, 100, 30);
        btnAzul.setBackground(Color.BLUE);
        btnAzul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });
        frame.getContentPane().add(btnAzul);

        // Crear botón amarillo
        JButton btnAmarillo = new JButton("Amarillo");
        btnAmarillo.setBounds(270, 50, 100, 30);
        btnAmarillo.setBackground(Color.YELLOW);
        btnAmarillo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });
        frame.getContentPane().add(btnAmarillo);

        // Crear botón verde
        JButton btnVerde = new JButton("Verde");
        btnVerde.setBounds(110, 100, 100, 30);
        btnVerde.setBackground(Color.GREEN);
        btnVerde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });
        frame.getContentPane().add(btnVerde);

        // Crear botón naranja
        JButton btnNaranja = new JButton("Naranja");
        btnNaranja.setBounds(220, 100, 100, 30);
        btnNaranja.setBackground(Color.ORANGE);
        btnNaranja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });
        frame.getContentPane().add(btnNaranja);
    }

}
