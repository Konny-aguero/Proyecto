package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private JPanel gridPanel; // Donde irán las fichas
    private JLabel lblTurno;  // Etiqueta para mostrar el turno

    public BoardView(MainWindow window) {
        setOpaque(false);
        setLayout(new BorderLayout(20, 20)); // Espaciado entre bordes
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // --- PARTE SUPERIOR: Turno ---
        // Aunque no tengas el controlador, dejamos el espacio visual
        lblTurno = new JLabel("Turno: Esperando...", SwingConstants.CENTER);
        lblTurno.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        lblTurno.setForeground(Color.WHITE);

        // Un panel contenedor para el texto del turno con fondo semitransparente
        JPanel turnContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 100)); // Fondo negro suave
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        turnContainer.setOpaque(false);
        turnContainer.add(lblTurno);
        add(turnContainer, BorderLayout.NORTH);

        // --- PARTE CENTRAL: El Tablero ---
        gridPanel = new JPanel();
        gridPanel.setOpaque(false);
        // El GridLayout lo definiremos luego dinámicamente según el tamaño
        add(gridPanel, BorderLayout.CENTER);

        // --- PARTE INFERIOR: Botón Salir ---
        JButton btnExit = createExitButton(window);
        add(UIUtils.center(btnExit), BorderLayout.SOUTH);

    }

    // Método para que el controlador genere el tablero después
    public void setupBoard(int size) {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(size, size, 5, 5)); // Filas, Columnas, Espacio

        for (int i = 0; i < size * size; i++) {
            JButton cell = new JButton();
            cell.setBackground(new Color(255, 255, 255, 50)); // Celdas semitransparentes
            cell.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            gridPanel.add(cell);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JButton createExitButton(MainWindow window) {
        JButton btn = new JButton("Salir") {
            @Override
            protected void paintComponent(Graphics g) {
                UIUtils.drawRoundedStyle(g, this, new Color(52, 52, 52));
                super.paintComponent(g);
            }
        };
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 40));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> window.showPanel("MainMenu"));
        return btn;
    }
}
