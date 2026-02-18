package edu.ucr.C5C089_C5I146.reversedots.view.panels;

import edu.ucr.C5C089_C5I146.reversedots.view.MainWindow;
import edu.ucr.C5C089_C5I146.reversedots.model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.BiConsumer;

public class BoardView extends JPanel {

    private JPanel boardPanel;
    private JLabel lblTurn;
    private JLabel lblP1Stats;
    private JLabel lblP2Stats;
    private JTextArea messages;

    private int boardSize;
    private JButton[][] cells;
    private BiConsumer<Integer, Integer> cellClickHandler;

    public enum MessageType { INFO, WARN, ERROR }

    public BoardView(MainWindow window) {

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

        side.add(Box.createVerticalStrut(20));
        side.add(lblP1Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(lblP2Stats);
        side.add(Box.createVerticalStrut(20));
        side.add(new JLabel("Mensajes:", SwingConstants.CENTER));
        side.add(new JScrollPane(messages));

        add(side, BorderLayout.EAST);
    }

    // ============================================
    //        CREACIÓN DEL TABLERO
    // ============================================
    public void buildBoard(int size) {
        this.boardSize = size;

        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(size, size, 3, 3));

        cells = new JButton[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                JButton cell = new JButton();
                cell.setOpaque(true);
                cell.setBackground(new Color(255, 255, 255, 160));
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

                final int r = row;
                final int c = col;

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addMessage("Click en celda (" + r + "," + c + ")");
                        if (cellClickHandler != null) {
                            cellClickHandler.accept(r, c);
                        }
                    }
                });

                cells[row][col] = cell;
                boardPanel.add(cell);
            }
        }

        revalidate();
        repaint();
    }

    // Permite que el controlador capture clicks en celdas
    public void setCellClickHandler(BiConsumer<Integer, Integer> handler) {
        this.cellClickHandler = handler;
    }

    // Actualiza todo el tablero desde una matriz de Piece
    public void updateBoard(Piece[][] board) {
        if (board == null) return;
        int size = board.length;
        if (cells == null || cells.length != size) {
            buildBoard(size);
        }
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                setPiece(r, c, board[r][c]);
            }
        }
    }

    // Pinta una pieza en una celda (o la limpia si EMPTY)
    public void setPiece(int row, int col, Piece piece) {
        if (cells == null) return;
        if (row < 0 || row >= cells.length || col < 0 || col >= cells.length) return;

        JButton cell = cells[row][col];
        cell.setIcon(null);
        if (piece == Piece.BLACK) {
            cell.setIcon(new CircleIcon(Color.BLACK, Math.min(cell.getWidth(), cell.getHeight()) - 8));
            cell.setBackground(new Color(200, 200, 200));
        } else if (piece == Piece.WHITE) {
            cell.setIcon(new CircleIcon(Color.WHITE, Math.min(cell.getWidth(), cell.getHeight()) - 8));
            cell.setBackground(new Color(200, 200, 200));
        } else {
            cell.setBackground(new Color(255, 255, 255, 160));
        }
        cell.repaint();
    }

    // Establecer información de jugadores (nombre, color y conteo opcional)
    public void setPlayerInfo(String p1Name, Color p1Color, int p1Count,
                              String p2Name, Color p2Color, int p2Count) {
        lblP1Stats.setText(String.format("%s (%s): %d", p1Name, colorName(p1Color), p1Count));
        lblP2Stats.setText(String.format("%s (%s): %d", p2Name, colorName(p2Color), p2Count));
    }

    private String colorName(Color c) {
        if (c == Color.BLACK) return "Negro";
        if (c == Color.WHITE) return "Blanco";
        return "";
    }

    // Resalta movimientos válidos
    public void highlightValidMoves(List<int[]> moves) {
        clearHighlights();
        if (moves == null || cells == null) return;
        for (int[] m : moves) {
            int r = m[0], c = m[1];
            if (r >= 0 && r < cells.length && c >= 0 && c < cells.length) {
                cells[r][c].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }
        }
    }

    public void clearHighlights() {
        if (cells == null) return;
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells.length; c++) {
                cells[r][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }
        }
    }

    public void setTurn(String text) {
        lblTurn.setText("Turno: " + text);
    }

    public void setTurn(String text, Color color) {
        lblTurn.setText("Turno: " + text);
        lblTurn.setForeground(color);
    }

    public void setStats(int p1, int p2) {
        lblP1Stats.setText("Jugador 1: " + p1);
        lblP2Stats.setText("Jugador 2: " + p2);
    }

    public void addMessage(String msg) {
        messages.append(msg + "\n");
    }

    public void showMessage(String msg, MessageType type) {
        if (type == MessageType.ERROR) {
            messages.append("[ERROR] " + msg + "\n");
        } else if (type == MessageType.WARN) {
            messages.append("[WARN] " + msg + "\n");
        } else {
            messages.append("[INFO] " + msg + "\n");
        }
    }

    // Icono simple para representar una ficha circular
    private static class CircleIcon implements Icon {
        private final Color color;
        private final int diameter;

        public CircleIcon(Color color, int diameter) {
            this.color = color;
            this.diameter = Math.max(4, diameter);
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int cx = (c.getWidth() - diameter) / 2;
            int cy = (c.getHeight() - diameter) / 2;
            g2.setColor(color);
            g2.fillOval(cx, cy, diameter, diameter);
            g2.setColor(Color.DARK_GRAY);
            g2.drawOval(cx, cy, diameter, diameter);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return diameter;
        }

        @Override
        public int getIconHeight() {
            return diameter;
        }
    }
}
