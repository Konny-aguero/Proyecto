package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private final Color softYellow = new Color(250, 244, 186);
    private final Color softGray   = new Color(214, 214, 214);
    private final Color darkGray   = new Color(52, 52, 52);

    public MainMenuPanel(MainWindow window) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(160));

        JButton nuevoBtn = createMenuButton("Nuevo Juego", softYellow);
        nuevoBtn.setForeground(Color.BLACK);
        add(center(nuevoBtn));
        add(Box.createVerticalStrut(20));

        JButton loadGame = createMenuButton("Cargar Partida", softGray);
        loadGame.setForeground(Color.BLACK);
        loadGame.addActionListener(e -> window.showPanel("LoadGame"));
        add(center(loadGame));
        add(Box.createVerticalStrut(20));

        JButton players = createMenuButton("Jugadores", darkGray);
        players.setForeground(Color.WHITE);
        add(center(players));
        add(Box.createVerticalStrut(20));

        JButton exit = createMenuButton("Salir", softGray);
        exit.setForeground(Color.BLACK);
        exit.addActionListener(e -> System.exit(0));
        add(center(exit));
    }

    private JButton createMenuButton(String text, Color bgColor) {
        JButton button = new JButton(text) {

            private Color currentColor = bgColor; // color que se dibuja
            private Color hoverColor = bgColor.darker(); // color al pasar el mouse

            {
                // Listener para detectar cuando el mouse entra o sale
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        currentColor = hoverColor;
                        repaint(); // se vuelve a dibujar
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        currentColor = bgColor;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();

                // Sombra
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fillRoundRect(3, 4, w - 6, h - 6, 20, 20);

                // Botón (color actual según hover)
                g2.setColor(currentColor);
                g2.fillRoundRect(0, 0, w - 6, h - 6, 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(220, 40));
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        return button;


}

    private JPanel center(JComponent comp) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(comp);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }
}