package edu.ucr.C5C089_C5I146.reversedots.view;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.view.panels.*;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuStyles;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel panel;
    private GameController controller;
    public MainMenuPanel mainMenuPanel;
    public NewGamePanel newGamePanel;
    public BoardView boardView;
    public PlayersPanel playersPanel;
    public LoadGamePanel loadGamePanel;

    private MenuStyles background;

    private JMenuBar menuBar;
    private JMenuItem newGameItem;
    private JMenuItem loadGameItem;
    private JMenuItem playersItem;
    private JMenuItem exitItem;

    public MainWindow() {
        controller = new GameController();
        setTitle("Reverse Dots");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "¿Desea salir de la aplicación?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // barra de menú
        createMenuBar();

        background = new MenuStyles();
        background.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        panel.setOpaque(false);

        mainMenuPanel = new MainMenuPanel(this);
        newGamePanel = new NewGamePanel(this, controller);
        boardView = new BoardView(this, controller);
        playersPanel = new PlayersPanel(this);
        loadGamePanel = new LoadGamePanel(this);

        panel.add(mainMenuPanel, "MainMenu");
        panel.add(newGamePanel, "NewGame");
        panel.add(boardView, "BoardView");
        panel.add(playersPanel, "Players");
        panel.add(loadGamePanel, "LoadGame");

        background.add(panel, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        setContentPane(background);
        setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Acciones");

        newGameItem = new JMenuItem("Nueva partida");
        loadGameItem = new JMenuItem("Cargar partida");
        playersItem = new JMenuItem("Jugadores registrados");
        exitItem = new JMenuItem("Salir");

        // navegar a los panels correspondientes
        newGameItem.addActionListener(e -> showPanel("NewGame"));
        loadGameItem.addActionListener(e -> showPanel("LoadGame"));
        playersItem.addActionListener(e -> showPanel("Players"));
        exitItem.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "¿Desea salir del juego?", "Confirmación",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menu.add(newGameItem);
        menu.add(loadGameItem);
        menu.add(playersItem);
        menu.addSeparator();
        menu.add(exitItem);

        menuBar.add(menu);
    }

    public void showPanel(String name) {
        cardLayout.show(panel, name);
    }
}