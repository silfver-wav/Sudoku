package model;

import java.io.*;

/**
 * Represents IO for a Sudoku game
 */
public class SudokuIO {

    /**
     * Serialize logic of a sudoku game to file
     * @param filename the name of the file
     * @param logic the logic
     * @throws IOException If an error occurred during the serialization
     */
    public static void serializeToFile(String filename, SudokuLogic logic) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(logic);

        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Deserialize logic of a sudoku game from a file
     * @param filename the name of the file
     * @return logic of the sudoku game
     * @throws IOException If and error occurred during the deserialization
     * @throws ClassNotFoundException If classpath is undefined
     */
    public static SudokuLogic  deSerializeFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            SudokuLogic logic = (SudokuLogic) in.readObject();
            return logic;
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        } finally {
            try {
                if(in != null) in.close();
            } catch(Exception e) {
                throw e;
            }
        }
    }

}