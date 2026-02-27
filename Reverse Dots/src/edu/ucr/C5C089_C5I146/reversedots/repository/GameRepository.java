package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Game;
/**
 * Maneja guardar y cargar partidas.
 */
public interface GameRepository {
    /**
     * Guarda una partida en la ruta dada.
     * @param path ruta del archivo donde se guarda
     * @param game partida a guardar
     */
    void save(String path, Game game);
    /**
     * Carga una partida desde una ruta.
     * @param path ruta del archivo a cargar
     * @return la partida cargada o null si no existe
     */
    Game load(String path);
    /**
     * Indica si una partida existe en la ruta dada.
     * @param path ruta del archivo a verificar
     * @return true si existe, false si no
     */
}