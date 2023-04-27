package main.presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Bola extends JPanel {
    private static final long serialVersionUID = 1L;
    private Color color;
    private Color colorSeleccionado = null;
    private boolean disabled;
    private final int TAMAÑO_BOLA;
    public boolean seleccionada;
    public boolean bolaTablero;
    private Integer fila;
    private Integer columna;
    
    public Bola(Color colorBola, boolean sol, boolean disabled, Integer numColumna, Integer numFila) {
        seleccionada = false;
        TAMAÑO_BOLA = sol == true ? 15 : 30;
        setPreferredSize(new Dimension(TAMAÑO_BOLA, TAMAÑO_BOLA));

        Color marron = new Color(97, 48, 13);
        bolaTablero = marron.equals(colorBola) ? true : false;
        color = colorBola;
        setPreferredSize(new Dimension(TAMAÑO_BOLA, TAMAÑO_BOLA));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color marron = new Color(139, 69, 19);
                if (color.equals(marron)) {
                    if (colorSeleccionado != null) {
                        color = colorSeleccionado;
                    }
                } else {
                    colorSeleccionado = color;
                }
                Cursor customCursor = new Cursor(Cursor.HAND_CURSOR);
                setCursor(customCursor);

            }
        });
        this.fila = numFila;
        this.columna = numColumna;
        this.disabled = disabled;
    }
    
    public Integer getFila() {
    	return this.fila;
    }
    public Integer getColumna() {
    	return this.columna;
    }
    public void changeDisabled(boolean disabled){
    	this.disabled = disabled;
    }
    
    public Color getColor() {
        return this.color;
    }
    public boolean isDisabled() {
    	return this.disabled;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (seleccionada) {
            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
            int mouseY = MouseInfo.getPointerInfo().getLocation().y;
            setLocation(mouseX - getWidth() / 2, mouseY - getHeight() / 2);
        }
        g.setColor(color);
        g.fillOval(0, 0,  TAMAÑO_BOLA,  TAMAÑO_BOLA); // Utilizar getWidth() y getHeight() para dibujar la elipse
    }
    public boolean esColorTablero() {
        return bolaTablero;
    }

    public void setColorTablero() {
        color = new Color(139, 69, 19);
        repaint();
    }

    public void setColor(Color colorSeleccionado2) {
        color = colorSeleccionado2;
    }
}
