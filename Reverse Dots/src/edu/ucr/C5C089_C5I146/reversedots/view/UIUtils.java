package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class UIUtils {

    // El método de dibujo extraído
    public static void drawRoundedStyle(Graphics g, JComponent c, Color bgColor) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Hace que los bordes no se vean "pixelados"
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = c.getWidth();
        int h = c.getHeight();

        // 1. Dibujar Sombra (desplazada 3px a la derecha y 4px abajo)
        g2.setColor(new Color(0, 0, 0, 80));
        g2.fillRoundRect(3, 4, w - 6, h - 6, 20, 20);

        // 2. Dibujar Fondo Principal
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, w - 6, h - 6, 20, 20);

        g2.dispose();
    }

    // El método de centrado extraído
    public static JPanel center(JComponent comp) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(comp);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }
}