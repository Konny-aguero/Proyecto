package edu.ucr.C5C089_C5I146.reversedots.view;

import edu.ucr.C5C089_C5I146.reversedots.view.panels.*;
import edu.ucr.C5C089_C5I146.reversedots.view.ui.MenuBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards;

    public MainMenuPanel mainMenuPanel;
    public NewGamePanel newGamePanel;
    public BoardView boardView;
    public PlayersPanel playersPanel;
    public LoadGamePanel loadGamePanel;

    private MenuBackgroundPanel background;

    private JMenuBar menuBar;
    private JMenuItem newGameItem;
    private JMenuItem loadGameItem;
    private JMenuItem saveGameItem;
    private JMenuItem playersItem;
    private JMenuItem exitItem;

    public MainWindow() {
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

        // barra de menú global
        createMenuBar();

        background = new MenuBackgroundPanel();
        background.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);

        mainMenuPanel = new MainMenuPanel(this);
        newGamePanel = new NewGamePanel(this);
        boardView = new BoardView(this);
        playersPanel = new PlayersPanel(this);
        loadGamePanel = new LoadGamePanel(this);

        cards.add(mainMenuPanel, "MainMenu");
        cards.add(newGamePanel, "NewGame");
        cards.add(boardView, "BoardView");
        cards.add(playersPanel, "Players");
        cards.add(loadGamePanel, "LoadGame");

        background.add(cards, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        setContentPane(background);
        setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Acciones");

        newGameItem = new JMenuItem("Nueva partida");
        loadGameItem = new JMenuItem("Cargar partida");
        saveGameItem = new JMenuItem("Guardar partida");
        playersItem = new JMenuItem("Jugadores registrados");
        exitItem = new JMenuItem("Salir");

        // Acciones por defecto: navegar a los panels correspondientes
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

        // Guardar deshabilitado por defecto; el controlador podrá habilitarlo y registrar listener
        saveGameItem.setEnabled(false);

        menu.add(newGameItem);
        menu.add(loadGameItem);
        menu.add(saveGameItem);
        menu.add(playersItem);
        menu.addSeparator();
        menu.add(exitItem);

        menuBar.add(menu);
    }

    public void showPanel(String name) {
        cardLayout.show(cards, name);
    }

    // Getters para que el controlador pueda registrar listeners
    public JMenuItem getNewGameMenuItem() { return newGameItem; }
    public JMenuItem getLoadGameMenuItem() { return loadGameItem; }
    public JMenuItem getSaveGameMenuItem() { return saveGameItem; }
    public JMenuItem getPlayersMenuItem() { return playersItem; }
    public JMenuItem getExitMenuItem() { return exitItem; }

    public void setSaveEnabled(boolean enabled) {
        if (saveGameItem != null) saveGameItem.setEnabled(enabled);
    }

    public void addMenuActionListener(String key, ActionListener l) {
        switch (key) {
            case "NEW": newGameItem.addActionListener(l); break;
            case "LOAD": loadGameItem.addActionListener(l); break;
            case "SAVE": saveGameItem.addActionListener(l); break;
            case "PLAYERS": playersItem.addActionListener(l); break;
            case "EXIT": exitItem.addActionListener(l); break;
            default: throw new IllegalArgumentException("Unknown menu key: " + key);
        }
    }

    // Muestra diálogo para guardar un archivo; devuelve la ruta seleccionada o null si se canceló.
    // Si el archivo existe, solicita confirmación de sobrescritura.
    public String showSaveDialog() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showSaveDialog(this);
        if (res != JFileChooser.APPROVE_OPTION) return null;
        File f = fc.getSelectedFile();
        if (f.exists()) {
            int over = JOptionPane.showConfirmDialog(this,
                    "El archivo existe. ¿Desea sobrescribirlo?",
                    "Confirmar sobrescritura", JOptionPane.YES_NO_OPTION);
            if (over != JOptionPane.YES_OPTION) return null;
        }
        return f.getAbsolutePath();
    }

    // Muestra diálogo para abrir un archivo; devuelve la ruta seleccionada o null si se canceló.
    public String showOpenDialog() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(this);
        if (res != JFileChooser.APPROVE_OPTION) return null;
        return fc.getSelectedFile().getAbsolutePath();
    }
}
