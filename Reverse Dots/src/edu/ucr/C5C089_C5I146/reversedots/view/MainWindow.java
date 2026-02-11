package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout; // para cambiar entre paneles
    private JPanel cards;           // panel que contiene todas las “pantallas”
    private MenuBackgroundPanel background; // fondo dinámico

    public MainWindow() {
        setTitle("Reverse Dots");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 510);
        setLocationRelativeTo(null);

        // Panel de fondo con título inicial
        background = new MenuBackgroundPanel("REVERSE DOTS");
        background.setLayout(new BorderLayout());

        // Panel con CardLayout dentro del fondo
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false); // para que se vea el fondo

        // Crear paneles
        MainMenuPanel mainMenu = new MainMenuPanel(this);
        LoadGamePanel loadGame = new LoadGamePanel(this);

        // Agregar paneles al CardLayout
        cards.add(mainMenu, "MainMenu");
        cards.add(loadGame, "LoadGame");

        // Agregar cards al fondo
        background.add(cards, BorderLayout.CENTER);

        // Poner el fondo como content pane
        setContentPane(background);
        setVisible(true);
    }

    // Método para cambiar de pantalla y actualizar título dinámico
    public void showPanel(String name) {
        if(name.equals("MainMenu")) {
            background.setTitle("REVERSE DOTS");
        } else if(name.equals("LoadGame")) {
            background.setTitle("CARGAR PARTIDA");
        }
        cardLayout.show(cards, name);
    }
}
