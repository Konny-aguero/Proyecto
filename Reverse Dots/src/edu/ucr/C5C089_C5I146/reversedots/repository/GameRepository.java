package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Game;

public interface GameRepository {

    void save(String path, Game game);

    Game load(String path);

    boolean exists(String path);
}