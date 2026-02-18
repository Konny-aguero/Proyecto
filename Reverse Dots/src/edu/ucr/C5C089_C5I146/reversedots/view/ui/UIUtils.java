package edu.ucr.C5C089_C5I146.reversedots.view.ui;

import javax.swing.*;
import java.awt.*;

public class UIUtils {

    public static JButton createMenuButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(60, 60, 60));
        b.setForeground(Color.WHITE);
        b.setPreferredSize(new Dimension(220, 45));
        return b;
    }

    public static JPanel center(Component c) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.add(c);
        return p;
    }
}
