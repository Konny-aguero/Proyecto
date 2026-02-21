package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class LoadGamePanel extends JPanel {

    public LoadGamePanel(MainWindow window) {

        setOpaque(false);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Cargar Partida", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JButton load = MenuStyles.createMenuButton("Seleccionar archivo...");
        load.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "Archivo seleccionado:\n" + fc.getSelectedFile().getAbsolutePath());
            }
        });

        add(load, BorderLayout.CENTER);

        JButton back = MenuStyles.createMenuButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));
        add(back, BorderLayout.SOUTH);
    }
}
