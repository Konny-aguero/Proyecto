package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.UIUtils;

import javax.swing.*;
import java.awt.*;

public class NewGamePanel extends JPanel {

    private JTextField p1Field;
    private JTextField p2Field;
    private JTextField sizeField;
    private GameController controller;

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

        add(new JLabel("Jugador 1:"), gbc);
        gbc.gridy++;
        add(p1Field, gbc);

        gbc.gridy++;
        add(new JLabel("Jugador 2:"), gbc);
        gbc.gridy++;
        add(p2Field, gbc);

        gbc.gridy++;
        add(new JLabel("Tamaño del tablero (par ≥ 4):"), gbc);
        gbc.gridy++;
        add(sizeField, gbc);

        JButton start = UIUtils.createMenuButton("Iniciar");
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

            // → aquí se llamará al controlador real
//            window.boardView.buildBoard(size);
//            window.showPanel("BoardView");
            try {
                controller.startGame(p1, p2, size);

                window.boardView.buildBoard(size);
                window.boardView.refreshBoard(); // ← la crearemos ahora

                window.showPanel("BoardView");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    public String getP1() { return p1Field.getText(); }
    public String getP2() { return p2Field.getText(); }
}
