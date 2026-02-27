package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Player;

import java.io.*;

public class TextPlayerRepository implements PlayerRepository {

    private static final String DIRECTORY = "players";

    public TextPlayerRepository() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @Override
    public void save(Player player) {

        try {
            File file = new File(DIRECTORY + "/" + player.getName() + ".txt");

            if (file.exists()) {
                return;
            }

            writePlayerToFile(player, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player load(String playerName) {

        try {
            File file = new File(DIRECTORY + "/" + playerName + ".txt");

            if (!file.exists()) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String name = reader.readLine().split("=")[1];
            int wins = Integer.parseInt(reader.readLine().split("=")[1]);
            int losses = Integer.parseInt(reader.readLine().split("=")[1]);
            int draws = Integer.parseInt(reader.readLine().split("=")[1]);

            reader.close();

            Player player = new Player(name, null);
            player.setWins(wins);
            player.setLosses(losses);
            player.setDraws(draws);

            return player;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean exists(String playerName) {
        File file = new File(DIRECTORY + "/" + playerName + ".txt");
        return file.exists();
    }

    @Override
    public void update(Player player) {

        try {
            File file = new File(DIRECTORY + "/" + player.getName() + ".txt");
            writePlayerToFile(player, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePlayerToFile(Player player, File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("NAME=" + player.getName());
        writer.newLine();
        writer.write("WINS=" + player.getWins());
        writer.newLine();
        writer.write("LOSSES=" + player.getLosses());
        writer.newLine();
        writer.write("DRAWS=" + player.getDraws());

        writer.close();
    }
}