package edu.ucr.C5C089_C5I146.reversedots.view.ui;

import javax.swing.*;
import java.awt.*;

public class MenuStyles extends JPanel {
    private Image backgroundImage;
    public MenuStyles() {
        setOpaque(true);
        backgroundImage = new ImageIcon(getClass().getResource("images/fondo.png")).getImage();
        setPreferredSize(new Dimension(300, 500));
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
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}