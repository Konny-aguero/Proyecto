package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class NewGamePanel extends JPanel {

    private final JTextField p1Field;
    private final JTextField p2Field;
    private final JTextField sizeField;
    private final GameController controller;

    public NewGamePanel(MainWindow window, GameController controller) {

        this.controller = controller;
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;

        p1Field = new JTextField(15);
        p2Field = new JTextField(15);
        sizeField = new JTextField(5);

        JLabel lblP1 = new JLabel("Jugador 1:");
        lblP1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add(lblP1, gbc);

        gbc.gridy++;
        add(p1Field, gbc);

        gbc.gridy++;
        JLabel lblP2 = new JLabel("Jugador 2:");
        lblP2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add(lblP2, gbc);

        gbc.gridy++;
        add(p2Field, gbc);

        gbc.gridy++;
        JLabel lblSize = new JLabel("Tamaño del tablero (par ≥ 4):");
        lblSize.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add(lblSize, gbc);

        gbc.gridy++;
        add(sizeField, gbc);

        JButton start = MenuStyles.createMenuButton("Iniciar");
        gbc.gridy++;
        add(start, gbc);

        start.addActionListener(e -> {

            String p1 = p1Field.getText().trim();
            String p2 = p2Field.getText().trim();

            int size;
            try {
                size = Integer.parseInt(sizeField.getText());
                if (size < 4 || size % 2 != 0) throw new Exception();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Tamaño inválido.");
                return;
            }

            try {
                controller.startGame(p1, p2, size);

                window.boardView.buildBoard(size);
                window.boardView.refreshBoard();

                window.showPanel("BoardView");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    public String getP1() { return p1Field.getText(); }
    public String getP2() { return p2Field.getText(); }
}
