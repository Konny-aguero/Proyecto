package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {

    private final GameController controller;
    private final JTextArea list;

    public PlayersPanel(MainWindow window, GameController controller) {

        this.controller = controller;

        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridy = 0;

        list = new JTextArea();
        list.setEditable(false);
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        list.setBackground(new Color(40, 40, 40));
        list.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        add(scrollPane, gbc);

        gbc.gridy++;

        JButton refresh = MenuStyles.createMenuButton("Actualizar");
        refresh.addActionListener(e -> {
            list.setText(controller.getRegisteredPlayersInfo());
        });
        add(refresh, gbc);

        gbc.gridy++;

        JButton back = MenuStyles.createMenuButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));
        add(back, gbc);

        // Cargar automáticamente al entrar
        list.setText(controller.getRegisteredPlayersInfo());
    }
}