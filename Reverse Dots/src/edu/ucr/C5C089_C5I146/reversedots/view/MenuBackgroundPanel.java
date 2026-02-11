package edu.ucr.C5C089_C5I146.reversedots.view;
import javax.swing.*;
import java.awt.*;

public class MenuBackgroundPanel extends JPanel {
    private String title;

    public MenuBackgroundPanel(String title) {
        this.title = title;
        setLayout(new BorderLayout());
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        //fondo azul
        g2.setColor(new Color(150, 159, 186));
        g2.fillRect(0, 0, w, h);

        //rectangulo claro
        g2.setColor(new Color(255, 255, 255, 60));
        int anchoCuadro = 400;
        int altoCuadro = 180;
        int x1 = (w - anchoCuadro) / 2;
        int y1 = (h - altoCuadro) / 2 + 35;
        g2.fillRoundRect(x1, y1, anchoCuadro, altoCuadro, 70, 80);

        //marco blanco
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.WHITE);
        int margin = 40;

        // Rectángulo principal
        g2.drawRect(margin, margin, w - margin * 2, h - margin * 2);

        // Líneas internas
        g2.drawLine(margin, margin + 70, w - margin, margin + 70); // superior
        g2.drawLine(60, 40, 60, h - 40); // izquierda
        g2.drawLine(w - 60, 40, w - 60, h - 40); // derecha
        g2.drawLine(margin, h - margin - 20, w - margin, h - margin - 20); // inferior

        // título
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(title);
        int x = w / 2 - textWidth / 2;
        int y = margin + 50;
        g2.drawString(title, x, y);

// círculos
        g2.setColor(Color.WHITE);
        int dotSize = 16;

// Circulos izquierdos (mantenemos igual)
        g2.fillOval(x - 50, y - 27, dotSize, dotSize);
        g2.fillOval(x - 30, y - 15, dotSize, dotSize);
        g2.fillOval(x - 30, y - 39, dotSize, dotSize);

// Círculos derechos (movemos relativo al ancho del texto)
        g2.fillOval(x + textWidth + 15, y - 39, dotSize, dotSize);
        g2.fillOval(x + textWidth + 15, y - 15, dotSize, dotSize);
        g2.fillOval(x + textWidth + 35, y - 27, dotSize, dotSize);


        g2.dispose();
    }
    public void setTitle(String title) {
        this.title = title;
        repaint(); // actualizar el panel con el nuevo título
    }

}
