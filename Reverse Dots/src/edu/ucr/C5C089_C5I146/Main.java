package edu.ucr.C5C089_C5I146;
import edu.ucr.C5C089_C5I146.reversedots.view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        showMainMenu();
//    }
//
//    public static void showMainMenu() {
//        String[] options = {
//                "Iniciar Nueva Partida",
//                "Cargar Partida",
//                "Ver Jugadores",
//                "Salir"
//        };
//
//        int choice = JOptionPane.showOptionDialog(null,
//                "Seleccione una opción", "Menú de Inicio - Reverse Dots",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
//                null, options, options[0]);
//
//        switch (choice) {
//            case 0:
//                startNewGame();
//                break;
//            case 1:
//                // Aquí irá la lógica de cargar archivo
//                break;
//            case 2:
//                // Aquí irá la lógica de estadísticas de jugadores
//                break;
//            case 3:
//                confirmExit();
//                break;
//        }
//    }
//
//    private static void startNewGame() {
//        // Requisito: Solicitar tamaño (Par >= 4)
//        String input = JOptionPane.showInputDialog("Ingrese el tamaño del tablero (N x N):");
//        int size = Integer.parseInt(input);
//
//        if (size >= 4 && size % 2 == 0) {
//            // Requisito: Los nombres de jugadores (simplificado por ahora)
//            String p1Name = JOptionPane.showInputDialog("Nombre Jugador 1:");
//            String p2Name = JOptionPane.showInputDialog("Nombre Jugador 2:");
//
//            // Aquí llamaríamos a tu MainView pasándole los datos
//            MainView gameWindow = new MainView(size);
//            gameWindow.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Tamaño inválido. Debe ser par y >= 4.");
//            showMainMenu();
//        }
//    }
//
//    public static void confirmExit() {
//        int resp = JOptionPane.showConfirmDialog(null,
//                "¿Está seguro que desea salir?", "Confirmación",
//                JOptionPane.YES_NO_OPTION);
//        if (resp == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        } else {
//            showMainMenu();
//        }
    }
}