package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class LoadGamePanel extends JPanel {

    public LoadGamePanel(MainWindow window) {

        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 15, 0);

        // Botón Cargar
        gbc.gridy++;
        JButton load = MenuStyles.createMenuButton("Seleccionar archivo...");
        load.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "Archivo seleccionado:\n" + fc.getSelectedFile().getAbsolutePath());
            }
        });
        add(load, gbc);

        // Botón Volver
        gbc.gridy++;
        JButton back = MenuStyles.createMenuButton("Volver");
        back.addActionListener(e -> window.showPanel("MainMenu"));
        add(back, gbc);
    }
}