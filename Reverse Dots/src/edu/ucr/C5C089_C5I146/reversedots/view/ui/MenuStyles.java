package edu.ucr.C5C089_C5I146.reversedots.view.ui;

import javax.swing.*;
import java.awt.*;

public class MenuStyles extends JPanel {
    public MenuStyles() {
        setOpaque(true);
    }
    public static JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(220, 45));
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(new GradientPaint(
                0, 0, new Color(84, 94, 121),
                getWidth(), getHeight(), new Color(120, 130, 150)
        ));

        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}