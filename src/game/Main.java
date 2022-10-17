package game;

import java.io.FileNotFoundException;

/**
 *
 * @author Balla Viktória
 */
public class Main {
    /**
     * Read input file in database, and simulate the game.
     * @param args - default argument
     */
    public static void main(String[] args) {
        try {
            Database database = new Database();
            database.read("input.txt");
            Game game = new Game(database);
            if (database.preRolled) {
                //System.out.println("DICEROLLS SIZE " + database.diceRolls.size());
                //System.out.println("PLAYERS SIZE " + database.getPlayersSize());
                game.generateRounds(database.diceRolls.size()/database.getPlayersSize(), database.diceRolls);
            } else game.generateRounds(20);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
    }
}
