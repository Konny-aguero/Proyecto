package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.Game;

import java.io.*;

public class FileGameRepository {

    public void save(File file, Game game) throws IOException {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(game);
        }
    }

    public Game load(File file) throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {

            return (Game) ois.readObject();
        }
    }
}