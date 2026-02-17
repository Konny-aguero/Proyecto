package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewGamePanel extends JPanel {
    private JTextField p1;
    private JTextField p2;
    private JTextField size;

    public NewGamePanel(MainWindow window) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(125));

        Color darkGray = new Color(52, 52, 52);
        Color softGray = new Color(214, 214, 214);
        Color softYellow = new Color(250, 244, 186);

        p1 = createCustomField("Jugador 1...", darkGray, Color.WHITE);
        p2 = createCustomField("Jugador 2...", softGray, Color.BLACK);
        size = createCustomField("Tamaño (Par)...", darkGray, Color.WHITE);

        JButton btnReturn = createCustomButton("Volver", softGray);
        btnReturn.addActionListener(e -> window.showPanel("MainMenu"));
        JButton startBtn = createCustomButton("Empezar", softYellow);
        startBtn.addActionListener(e -> window.showPanel("BoardView"));

        // Agregar al panel
        add(center(p1));   add(Box.createVerticalStrut(20));
        add(center(p2));   add(Box.createVerticalStrut(20));
        add(center(size)); add(Box.createVerticalStrut(20));
        add(center(btnReturn));add(Box.createVerticalStrut(20));
        add(center(startBtn));
    }

    // --- CREADORES DE COMPONENTES ---

    private JTextField createCustomField(String text, Color bg, Color fg) {
        JTextField field = new JTextField(text) {
            @Override
            protected void paintComponent(Graphics g) {
                drawRoundedStyle(g, this, getBackground());
                super.paintComponent(g);
            }
        };
        field.setForeground(fg);
        field.setBackground(bg); // Usamos el background para guardar el color
        field.setHorizontalAlignment(JTextField.CENTER);
        setupCommonSettings(field, bg);

        // Borrar texto al hacer clic
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) { field.setText(""); }
        });

        return field;
    }

    private JButton createCustomButton(String text, Color bg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                drawRoundedStyle(g, this, getBackground());
                super.paintComponent(g);
            }
        };
        btn.setBackground(bg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        setupCommonSettings(btn, bg);

        return btn;
    }

    // 1. Configuración común (Tamaño, Fuente, Mouse)
    private void setupCommonSettings(JComponent c, Color originalBg) {
        c.setPreferredSize(new Dimension(220, 40));
        c.setMaximumSize(new Dimension(220, 40));
        c.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        c.setOpaque(false);
        c.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        c.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                c.setBackground(originalBg.darker());
                c.repaint();
            }
            public void mouseExited(MouseEvent e) {
                c.setBackground(originalBg);
                c.repaint();
            }
        });
    }
    //Llamado a UIUtils
    private void drawRoundedStyle(Graphics g, JComponent c, Color bgColor) {
        UIUtils.drawRoundedStyle(g, c, bgColor);
    }
    private JPanel center(JComponent comp) {
        return UIUtils.center(comp);
    }
    public String getP1() { return p1.getText(); }
    public String getP2() { return p2.getText(); }
    public String getBoardSize() { return size.getText(); }

}