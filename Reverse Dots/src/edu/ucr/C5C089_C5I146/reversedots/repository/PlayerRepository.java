package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Player;
/**
 * Maneja guardar y leer datos de jugadores.
 */
public interface PlayerRepository {
    /**
     * Guarda un jugador si no existe.
     * @param player jugador a guardar
     */
    void save(Player player);
    /**
     * Carga un jugador desde archivo o memoria.
     * @param playerName nombre del jugador
     * @return el jugador cargado o null si no existe
     */
    Player load(String playerName);
    /**
     * Indica si un jugador ya existe.
     * @param playerName nombre del jugador
     * @return true si existe, false si no
     */
    boolean exists(String playerName);
    /**
     * Actualiza los datos del jugador.
     * @param player jugador a actualizar
     */
    void update(Player player);
}