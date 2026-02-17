package edu.ucr.C5C089_C5I146.reversedots.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(MainWindow window) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(160));

        // Colores del diseño
        Color softYellow = new Color(250, 244, 186);
        Color softGray   = new Color(214, 214, 214);
        Color darkGray   = new Color(52, 52, 52);

        // Crear botones usando la plantilla
        JButton newBtn   = createCustomButton("Nuevo Juego", softYellow, Color.BLACK);
        JButton loadBtn  = createCustomButton("Cargar Partida", softGray, Color.BLACK);
        JButton playBtn  = createCustomButton("Jugadores", darkGray, Color.WHITE);
        JButton exitBtn  = createCustomButton("Salir", softGray, Color.BLACK);

        // Acciones
        newBtn.addActionListener(e -> window.showPanel("NewGame"));
        exitBtn.addActionListener(e -> System.exit(0));

        // Añadir botones con su espacio
        add(center(newBtn));   add(Box.createVerticalStrut(20));
        add(center(loadBtn));  add(Box.createVerticalStrut(20));
        add(center(playBtn));  add(Box.createVerticalStrut(20));
        add(center(exitBtn));
    }

    // --- CREADOR DE BOTONES ---

    private JButton createCustomButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                drawRoundedStyle(g, this, getBackground());
                super.paintComponent(g);
            }
        };

        btn.setBackground(bg);
        btn.setForeground(fg);

        // Limpiar estilos estándar de Swing
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);

        // Configuración común (Tamaño, Fuente, Mouse)
        btn.setPreferredSize(new Dimension(220, 40));
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        // Efecto Hover
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bg.darker());
                btn.repaint();
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bg);
                btn.repaint();
            }
        });

        return btn;
    }

    // --- LÓGICA DE DIBUJO ---

    private void drawRoundedStyle(Graphics g, JComponent c, Color bgColor) {
        UIUtils.drawRoundedStyle(g, c, bgColor);
    }
    private JPanel center(JComponent comp) {
        return UIUtils.center(comp);
    }
}