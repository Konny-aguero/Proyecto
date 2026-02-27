package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Player;

public interface PlayerRepository {

    void save(Player player);

    Player load(String playerName);

    boolean exists(String playerName);

    void update(Player player);
}