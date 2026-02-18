package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {

    public PlayersPanel(MainWindow window) {
        setOpaque(false);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Jugadores registrados", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 22));

        add(label, BorderLayout.NORTH);

        JTextArea list = new JTextArea("Aquí se mostrarán los jugadores...");
        list.setEditable(false);

        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton back = new JButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));
        add(back, BorderLayout.SOUTH);
    }
}
