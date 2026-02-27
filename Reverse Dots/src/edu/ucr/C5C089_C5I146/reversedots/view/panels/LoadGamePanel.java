package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class LoadGamePanel extends JPanel {

    public LoadGamePanel(MainWindow window, GameController controller) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 15, 0);

        // Botón seleccionar archivo
        gbc.gridy++;
        JButton load = MenuStyles.createMenuButton("Seleccionar archivo...");
        load.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                java.io.File file = fileChooser.getSelectedFile();

                boolean loaded = controller.loadEGame(file);

                if (loaded) {

                    int size = controller.getBoard().getSize();

                    // Reconstruir tablero
                    window.boardView.buildBoard(size);
                    window.boardView.refreshBoard();

                    // Ir al tablero
                    window.showPanel("BoardView");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al cargar la partida.");
                }
            }
        });

        add(load, gbc);

        // Botón volver
        gbc.gridy++;
        JButton back = MenuStyles.createMenuButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));
        add(back, gbc);
    }
}