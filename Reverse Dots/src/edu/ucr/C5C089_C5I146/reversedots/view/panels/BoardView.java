package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.model.MoveResult;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardView extends JPanel {

    private JPanel boardPanel;
    private JLabel lblTurn;
    private JLabel lblP1Stats;
    private JLabel lblP2Stats;
    private JTextArea messages;
    private GameController controller;


    private int boardSize;

    public BoardView(MainWindow window, GameController controller){
        this.controller = controller;
        setOpaque(false);
        setLayout(new BorderLayout());

        // === PANEL SUPERIOR: TURNO ===
        lblTurn = new JLabel("Turno: -", SwingConstants.CENTER);
        lblTurn.setFont(new Font("Arial", Font.BOLD, 20));
        lblTurn.setForeground(Color.WHITE);

        add(lblTurn, BorderLayout.NORTH);

        // === PANEL CENTRAL: TABLERO ===
        boardPanel = new JPanel();
        boardPanel.setOpaque(false);
        add(boardPanel, BorderLayout.CENTER);

        // === PANEL DERECHO: ESTADÍSTICAS ===
        JPanel side = new JPanel();
        side.setBackground(new Color(0, 0, 0, 90));
        side.setPreferredSize(new Dimension(220, 0));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

        lblP1Stats = new JLabel("Jugador 1: 0", SwingConstants.CENTER);
        lblP1Stats.setForeground(Color.WHITE);
        lblP2Stats = new JLabel("Jugador 2: 0", SwingConstants.CENTER);
        lblP2Stats.setForeground(Color.WHITE);

        messages = new JTextArea();
        messages.setEditable(false);
        messages.setOpaque(false);
        messages.setForeground(Color.WHITE);
        messages.setLineWrap(true);
        messages.setWrapStyleWord(true);


        JButton btnSave = new JButton("Guardar partida");

        btnSave.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {

                java.io.File file = fileChooser.getSelectedFile();

                // Aquí más adelante llamaremos al controller
                //addMessage("Guardar en: " + file.getAbsolutePath());
                boolean saved = controller.saveEGame(file);

                if (saved) {
                    addMessage("Partida guardada correctamente.");
                } else {
                    addMessage("Error al guardar la partida.");
                }

            }
        });


        side.add(Box.createVerticalStrut(20));
        side.add(lblP1Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(lblP2Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(new JLabel("Mensajes:", SwingConstants.CENTER));
        side.add(new JScrollPane(messages));


        side.add(Box.createVerticalStrut(20));
        side.add(btnSave);

        add(side, BorderLayout.EAST);
    }

    // ============================================
    //        CREACIÓN DEL TABLERO
    // ============================================
    public void buildBoard(int size) {
        this.boardSize = size;

        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(size, size, 3, 3));

        for (int i = 0; i < size * size; i++) {

            JButton cell = new JButton();
            cell.setFocusPainted(false);
            cell.setBorderPainted(false);
            cell.setContentAreaFilled(false);
            cell.setOpaque(true);
            cell.setBackground(new Color(255, 255, 255, 160));

            int row = i / size;
            int col = i % size;

            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   // addMessage("Click en celda (" + row + "," + col + ")");
                    // Aquí se conectará al controlador real
//                    if (controller.makeMove(row, col)) {
//
//                        refreshBoard();
//
//                        if (controller.isGameOver()) {
//                            addMessage("Juego terminado. Ganador: " +
//                                    controller.getWinner().getName());
//                        }
//
//                    } else {
//                        addMessage("Movimiento inválido.");
//                    }
                    MoveResult result = controller.makeMove(row, col);

                    if (result == MoveResult.SUCCESS) {

                        refreshBoard();

                        if (controller.isGameOver()) {
                            addMessage("Juego terminado. Ganador: " +
                                    controller.getWinner().getName());
                        }

                    } else {
                        addMessage("Movimiento inválido: " + result);
                    }
                }
            });

            boardPanel.add(cell);
        }

        revalidate();
        repaint();
    }

    public void setTurn(String text) {
        lblTurn.setText("Turno: " + text);
    }

//    public void setStats(int p1, int p2) {
//        lblP1Stats.setText("Jugador 1: " + p1);
//        lblP2Stats.setText("Jugador 2: " + p2);
//    }
public void setStats(int p1, int p2) {
    lblP1Stats.setText(controller.getPlayer1Name() + ": " + p1);
    lblP2Stats.setText(controller.getPlayer2Name() + ": " + p2);
}

    public void addMessage(String msg) {
        messages.append(msg + "\n");
    }

    public void refreshBoard() {

        if (controller.getBoard() == null) return;

        int size = controller.getBoard().getSize();

        Component[] components = boardPanel.getComponents();

        for (int i = 0; i < size * size; i++) {

            int row = i / size;
            int col = i % size;

            JButton cell = (JButton) components[i];

            switch (controller.getBoard().getPiece(row, col)) {
                case BLACK:
                    cell.setBackground(Color.BLACK);
                    break;
                case WHITE:
                    cell.setBackground(Color.WHITE);
                    break;
                default:
                    cell.setBackground(new Color(255,255,255,160));
            }
        }

        setTurn(controller.getCurrentPlayer().getName());
        setStats(
                controller.getPlayer1Score(),
                controller.getPlayer2Score()
        );
    }


}
