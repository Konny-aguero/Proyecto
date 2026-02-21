package edu.ucr.C5C089_C5I146.reversedots.model;

public enum MoveResult {

    SUCCESS,                // Movimiento realizado correctamente
    INVALID_POSITION,       // Fuera del tablero
    CELL_NOT_EMPTY,         // La casilla ya tiene ficha
    NOT_YOUR_TURN,          // Intentó jugar el jugador incorrecto
    INVALID_MOVE,           // No encierra piezas
    GAME_ALREADY_FINISHED   // La partida ya terminó
}


