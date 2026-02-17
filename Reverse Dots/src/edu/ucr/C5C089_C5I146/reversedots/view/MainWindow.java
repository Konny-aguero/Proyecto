package edu.ucr.C5C089_C5I146.reversedots.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards;
    private MenuBackgroundPanel background;
    private NewGamePanel newGamePanel;


    public MainWindow() {
        setTitle("Reverse Dots");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 510);
        setLocationRelativeTo(null);

        background = new MenuBackgroundPanel("REVERSE DOTS");
        background.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);

        // --- INSTANCIAR PANELES ---
        MainMenuPanel mainMenu = new MainMenuPanel(this);
        newGamePanel = new NewGamePanel(this);
        BoardView boardView = new BoardView(this); // El panel del juego

        // --- AGREGAR AL CARDLAYOUT ---
        cards.add(mainMenu, "MainMenu");
        cards.add(newGamePanel, "NewGame");
        cards.add(boardView, "BoardView"); // Agregamos la vista del tablero

        background.add(cards, BorderLayout.CENTER);
        setContentPane(background);
        setVisible(true);
    }

    public void showPanel(String name) {
        // Actualizamos el título del fondo según la pantalla
        if (name.equals("MainMenu")) {
            background.setTitle("REVERSE DOTS");
        } else if (name.equals("NewGame")) {
            background.setTitle("Nueva Partida");
        } else if (name.equals("BoardView")) {
            background.setTitle(newGamePanel.getP1() + " vs " + newGamePanel.getP2());
        }

        cardLayout.show(cards, name);
    }
}