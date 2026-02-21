package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {

    public PlayersPanel(MainWindow window) {

        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridy = 0;

        // jugadores
        gbc.gridy++;

        JTextArea list = new JTextArea("Aquí se mostrarán los jugadores...");
        list.setEditable(false);
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        list.setBackground(new Color(40, 40, 40));
        list.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(350, 250));

        add(scrollPane, gbc);

        // volver
        gbc.gridy++;

        JButton back = MenuStyles.createMenuButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));

        add(back, gbc);
    }
}