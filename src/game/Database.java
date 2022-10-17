package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Balla Viktória
 */
public class Database {
    
    private final ArrayList<Field>  fields;

    /**
     *
     */
    public final ArrayList<Player> players;

    /**
     *
     */
    public final ArrayList<Integer> diceRolls;

    /**
     *
     */
    public boolean preRolled;
    
    /**
     *
     */
    public Database() {
        fields  = new ArrayList<>();
        players = new ArrayList<>();
        diceRolls = new ArrayList<>();
        preRolled = false;
    }
    
    /**
     * This method opens the input file and reads and stores the contained fields and players.
     * If there are also dice rolls, it will store those too.
     * @param filename name of the file the inputs are contained in
     * @throws FileNotFoundException - file not found
     * @throws InvalidInputException - invalid input
     */
    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numFields = sc.nextInt(); //reads number of fields
        for (int i = 0; i < numFields; i++) {
            //reads each line and adds a type of field to fields arraylist
            Field field;
            switch (sc.next()) {
                case "E":
                    field = new Estate();
                    break;
                case "S":
                    field = new Service(Integer.parseInt(sc.next()));
                    break;
                case "L":
                    field = new Lucky(Integer.parseInt(sc.next()));
                    break;
                default:
                    throw new InvalidInputException();
            }
            //System.out.println(field);
            fields.add(field);
        }
        
        int numPlayers = sc.nextInt(); //reads number of players
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player(sc.next(), sc.next(), 10000);
            //System.out.println(player);
            players.add(player);
        }
        
        int diceRollsNum = sc.nextInt();
        if (diceRollsNum != 0) {
            preRolled = true;
            System.out.println("dice was prerolled");
            for (int i = 0; i < diceRollsNum; i++) {
                int dr = sc.nextInt();
                diceRolls.add(dr);
            }
        } else System.out.println("dice was not prerolled");
        
    }
    
    /**
     * Get number of fields
     * @return size of fields array
     */
    public int getFieldsSize() {
        return fields.size();
    }
    
    /**
     * Get number of players
     * @return size of players array
     */
    public int getPlayersSize() {
        return players.size();
    }
    
    /**
     * Get the N-th field
     * @param n - number we need to check
     * @return N-th element of fields array
     */
    public Field getNthField(int n) {
        return fields.get(n);
    }
    
    /**
     * Get the N-th player
     * @param n - number to check
     * @return N-th element of players array
     */
    public Player getNthPlayer(int n) {
        return players.get(n);
    }

    /**
     * Checks if dice is pre-rolled
     * @return Dice is pre-rolled
     */
    public boolean isPreRolled() {
        return preRolled;
    }

    /**
     * Sets if dice is pre-rolled
     * @param isPreRolled - is the dice pre-rolled
     */
    public void setPreRolled(boolean isPreRolled) {
        this.preRolled = isPreRolled;
    }
    
    /**
     * Lists the estates owned by a player
     * @param p Player whose estates we need to get
     */
    public void getHousesOfPlayer(Player p) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getType().equals("Estate")) {
                if (fields.get(i).getOwner().getName().equals(p.getName())) {
                    System.out.println(p.getName() + " owns " + fields.get(i));
                }
            }  
        }
    }
    
    /**
     * Clears all the array lists used by the game
     */
    public void clear() {
        fields.clear();
        players.clear();
        diceRolls.clear();
    }

}
