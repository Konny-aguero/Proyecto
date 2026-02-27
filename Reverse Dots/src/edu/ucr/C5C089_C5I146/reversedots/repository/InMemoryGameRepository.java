package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Game;

import java.util.HashMap;
import java.util.Map;
/**
 * Guarda partidas solo en memoria durante la ejecución
 */
public class InMemoryGameRepository implements GameRepository {

    private final Map<String, Game> storage = new HashMap<>();

    @Override
    public void save(String id, Game game) {
        storage.put(id, game);
    }

    @Override
    public Game load(String id) {
        return storage.get(id);
    }

    @Override
    public boolean exists(String id) {
        return storage.containsKey(id);
    }
}