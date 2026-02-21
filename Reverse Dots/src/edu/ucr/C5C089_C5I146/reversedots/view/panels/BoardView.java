package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.model.MoveResult;
import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BoardView extends JPanel {

    private final JPanel boardPanel;
    private final JLabel lblTurn;
    private final JLabel lblP1Stats;
    private final JLabel lblP2Stats;
    private final JTextArea messages;
    private final GameController controller;
    private int boardSize;

    public BoardView(MainWindow window, GameController controller) {
        this.controller = controller;
        setOpaque(true);
        setBackground(new Color(156, 169, 214));
        setLayout(new BorderLayout());

        // Turno
        lblTurn = new JLabel("Turno: -", SwingConstants.CENTER);
        lblTurn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lblTurn.setForeground(Color.BLACK);

        add(lblTurn, BorderLayout.NORTH);

        // Panel tablero
        boardPanel = new JPanel();
        boardPanel.setOpaque(false);
        add(boardPanel, BorderLayout.CENTER);

        // Panel estadisticas
        JPanel side = new JPanel();
        side.setBackground(new Color(156, 169, 214));
        side.setPreferredSize(new Dimension(220, 0));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

        lblP1Stats = new JLabel("Jugador 1: 0", SwingConstants.CENTER);
        lblP1Stats.setForeground(Color.WHITE);
        lblP2Stats = new JLabel("Jugador 2: 0", SwingConstants.CENTER);
        lblP2Stats.setForeground(Color.WHITE);

        messages = new JTextArea();
        messages.setEditable(false);
        messages.setOpaque(true);
        messages.setBackground(new Color(100, 110, 150));
        messages.setForeground(Color.WHITE);
        messages.setLineWrap(true);
        messages.setWrapStyleWord(true);

        JButton btnSave = new JButton("Guardar partida");

        btnSave.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {

                java.io.File file = fileChooser.getSelectedFile();
                boolean saved = this.controller.saveEGame(file);

                if (saved) {
                    addMessage("Partida guardada correctamente.");
                } else {
                    addMessage("Error al guardar la partida.");
                }

            }
        });

        JScrollPane scroll = new JScrollPane(messages);
        scroll.getViewport().setBackground(new Color(100, 110, 150));

        side.add(Box.createVerticalStrut(20));
        side.add(lblP1Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(lblP2Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(scroll);


        side.add(Box.createVerticalStrut(20));
        side.add(btnSave);

        add(side, BorderLayout.EAST);
    }
    //creacion del tablero
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
            cell.setBackground(new Color(209, 215, 229));

            int row = i / size;
            int col = i % size;

            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

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
        refreshBoard();
    }

    public void setTurn(String text) {
        lblTurn.setText("Turno: " + text);
    }

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
                    cell.setBackground(new Color(209, 215, 229));
            }
        }

        setTurn(controller.getCurrentPlayer().getName());
        setStats(
                controller.getPlayer1Score(),
                controller.getPlayer2Score()
        );
        highlightValidMoves();
    }
    private void highlightValidMoves() {
        List<int[]> moves = controller.getGame().getValidMoves(controller.getCurrentPlayer().getColor());
        Component[] components = boardPanel.getComponents();
        int size = controller.getBoard().getSize();

        // 2. Primero restaurar colores por defecto
        for (int i = 0; i < size * size; i++) {
            JButton cell = (JButton) components[i];

            switch (controller.getBoard().getPiece(i / size, i % size)) {
                case BLACK:
                    cell.setBackground(Color.BLACK);
                    break;
                case WHITE:
                    cell.setBackground(Color.WHITE);
                    break;
                default:
                    cell.setBackground(new Color(209, 215, 229)); // celda vacía normal
            }
        }

        // 3. Marcar válidos
        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];

            JButton cell = (JButton) components[row * size + col];
            cell.setBackground(new Color(151, 164, 196));
        }
    }


}
