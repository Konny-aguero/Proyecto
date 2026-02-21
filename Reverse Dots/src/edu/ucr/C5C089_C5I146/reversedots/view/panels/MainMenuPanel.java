package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(MainWindow window) {

        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 15, 0);

        JButton newGame = MenuStyles.createMenuButton("Nueva partida");
        JButton loadGame = MenuStyles.createMenuButton("Cargar partida");
        JButton players = MenuStyles.createMenuButton("Jugadores registrados");
        JButton exit = MenuStyles.createMenuButton("Salir");

        newGame.addActionListener(e -> window.showPanel("NewGame"));
        loadGame.addActionListener(e -> window.showPanel("LoadGame"));
        players.addActionListener(e -> window.showPanel("Players"));

        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "¿Desea salir del juego?", "Confirmación",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(newGame, gbc);
        gbc.gridy++;
        add(loadGame, gbc);
        gbc.gridy++;
        add(players, gbc);
        gbc.gridy++;
        add(exit, gbc);
    }
}
