package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class MenuBackgroundPanel extends JPanel {

    public MenuBackgroundPanel() {
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new GradientPaint(0, 0, new Color(84, 94, 121),
                getWidth(), getHeight(), new Color(120, 130, 150)));
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
