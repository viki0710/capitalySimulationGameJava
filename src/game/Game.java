package game;

import java.util.ArrayList;

/**
 *
 * @author Balla Viktória
 */
public class Game {
    private Dice dice = new Dice();
    private Database database = new Database();
    
    private int fieldsNum; //number of fields
    private ArrayList<Integer> playerPositions; //contains positions of players

    /**
     * Constructor, loads player positions to 1
     * @param db - Database
     */
    public Game(Database db) {
        database = db;        
        fieldsNum = db.getFieldsSize();
        
        playerPositions = new ArrayList<>();
        for (int i = 0; i < db.getPlayersSize(); i++) { //loads player positions to 1
            playerPositions.add(1);
        }
    }

    /**
     * Get number of fields
     * @return number of fields
     */
    public int getFieldsNum() {
        return fieldsNum;
    }

    /**
     * set number of fields
     * @param fieldsNum - Number of fields
     */
    public void setFieldsNum(int fieldsNum) {
        this.fieldsNum = fieldsNum;
    }
    
    /**
     * get positions of players
     * @return Positions of players in an array
     */
    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    /**
     * set positions of players
     * @param playerPositions - Positions of players in an array
     */
    public void setPlayerPositions(ArrayList<Integer> playerPositions) {
        this.playerPositions = playerPositions;
    }
    
    /**
     * get position of the N-th player
     * @param n - number we need to check
     * @return N-th player position
     */
    public int getNthPlayerPosition(int n) {
        return playerPositions.get(n);
    }

    
    /**
     * Make the player go the rolled amount of steps.
     * If the game map loops around, loop accordingly.
     * @param n The n-th player that changes position
     * @param num The number of steps
     */
    public void setNthPlayerPosition(int n, int num) {
        int currentPosition = getNthPlayerPosition(n);
        //System.out.println("stepsnum:" + num);
        if (num > fieldsNum){
            this.playerPositions.set(n, Math.abs(num - fieldsNum));
        } else this.playerPositions.set(n, num);
    }
    
    /**
     * Check if estate is owned, or contains house.
     * If not, simulate players actions according to strategy.
     * If it is already owned by someone else, reduce balance of player.
     * @param e The field the player is on
     * @param i The number of the player
     */
    public void estateOwnedCheck(Field e, int i) {
        String nameOfPlayer = database.getNthPlayer(i).getName();
        //System.out.println("name of current player: " + nameOfPlayer);
        if (e.getOwner().getName().equals(nameOfPlayer)) {
            System.out.println("Player is on own estate");
            if (!e.isHouse()){
                if (database.getNthPlayer(i).getStrategy().equals("g")) {
                    System.out.println("There is no house on this estate yet. You can build one");
                    e.setHouse(true);
                    database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-4000);
                }
                if (database.getNthPlayer(i).getStrategy().equals("c")) {
                    if (database.getNthPlayer(i).getMoney()-4000 > (database.getNthPlayer(i).getMoney() / 2)) {
                        System.out.println("There is no house on this estate yet. You can build one");
                        e.setHouse(true);
                        database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-4000);
                    } else System.out.println("You did not build house");
                }
                if (database.getNthPlayer(i).getStrategy().equals("t")) {
                    if (!database.getNthPlayer(i).isSecondBuy()) {
                        System.out.println("There is no house on this estate yet. You can build one");
                        e.setHouse(true);
                        database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-4000);
                        database.getNthPlayer(i).setSecondBuy(true);
                        System.out.println("1st buy");
                    } else {
                        database.getNthPlayer(i).setSecondBuy(false);
                        System.out.println("2nd buy");
                    }
                }
            }
        } else if (e.isHouse()) {
            database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-2000);
            System.out.println(database.getNthPlayer(i).getName() + " - 2000");
        } else if (e.isOwned()) {
            database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-500);
            System.out.println(database.getNthPlayer(i).getName() + " - 500");
        }
        
        
        //handles buying house
        if (e.getOwner().getName().equals("None")){
            System.out.println("You can buy this estate");
            if (database.getNthPlayer(i).getStrategy().equals("g")) { //greedy
                e.setOwned(true);
                e.setOwner(database.getNthPlayer(i));
                database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-1000);
            }
            if (database.getNthPlayer(i).getStrategy().equals("c")) { //careful
                if (database.getNthPlayer(i).getMoney()-1000 > (database.getNthPlayer(i).getMoney() / 2)) {
                    e.setOwned(true);
                    e.setOwner(database.getNthPlayer(i));
                    database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-1000);
                } else System.out.println("You did not buy the estate");
                
            }
            if (database.getNthPlayer(i).getStrategy().equals("t")) { //tactical
                if (!database.getNthPlayer(i).isSecondBuy()) {
                    e.setOwned(true);
                    e.setOwner(database.getNthPlayer(i));
                    database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney()-1000);
                    database.getNthPlayer(i).setSecondBuy(true);
                    System.out.println("1st buy");
                } else {
                    database.getNthPlayer(i).setSecondBuy(false);
                    System.out.println("2nd buy");
                }
            }
        }
    }
    
    /**
     * Gets the type of field the player has stepped on and changes the balance
     * @param i the field we need to check
     */
    public void getFieldType(int i){
        
        Field f = database.getNthField(getNthPlayerPosition(i)-1);
        System.out.println("The type of the current field is: " + f);
        
        if (f.getType().equals("Service")){
            database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney() - f.getPrice());
        }
        if (f.getType().equals("Lucky")){
            database.getNthPlayer(i).setMoney(database.getNthPlayer(i).getMoney() + f.getPrice());
        }
        if (f.getType().equals("Estate")){
            estateOwnedCheck(f, i);
        }
    }
    
    /**
     * Checks if a player has lost all money and in case that happens
     * frees up all the estates owned by them, and removes player
     * @param i The player we need to check for dropping out
     * @return If player is dropped out
     */
    public boolean dropOutPlayer(int i){
        if (database.getNthPlayer(i).getMoney() <= 0) {
            System.out.println(database.getNthPlayer(i).getName() + " has lost all money!");
            for (int j = 0; j < database.getFieldsSize(); j++) {
                if (database.getNthField(j).getType().equals("Estate")){
                    //System.out.println("OWNED: " + database.getNthField(j).isOwned() + ", HOUSE: " + database.getNthField(j).isHouse() + " OWNER: " + database.getNthField(j).getOwner());
                    if (database.getNthField(j).getOwner().getName().equals(database.getNthPlayer(i).getName())) {
                        //System.out.println("oops you lose this");
                        database.getNthField(j).setOwned(false);
                        database.getNthField(j).setHouse(false);
                        database.getNthField(j).setOwner(new Player("None", "none", 0));
                        //System.out.println("(NOT) OWNED: " + database.getNthField(j).isOwned() + ", HOUSE: " + database.getNthField(j).isHouse() + " OWNER: " + database.getNthField(j).getOwner());
                    }
                }
            }
            database.players.remove(i);
            return true;
        }
        return false;
    }
    
    /**
     * Displays the remaining players and their statistics
     */
    public void report() {
        System.out.println("###################################################################");
        System.out.println("Remaining players:");
        if (database.getPlayersSize() < 1) System.out.println("None");
        for (int i = 0; i < database.getPlayersSize(); i++) {
            System.out.println(database.players.get(i).toString());
            database.getHousesOfPlayer(database.players.get(i));
        }
    }
    
    /**
     * Generates rounds based on random dice rolls
     * @param rounds - number of rounds
     */
    public void generateRounds(int rounds) {
        int numberOfRounds = 1;
        for (int i = 0; i < database.getPlayersSize(); i++) {
            System.out.println("---------------------------------------------");
            System.out.println("Current round: " + numberOfRounds);
            
            int numRolled = dice.rollNewNumber();
            System.out.println("The current player (" + database.getNthPlayer(i).getName() + ", " + i + ") rolled " + numRolled);
            
            setNthPlayerPosition(i, getNthPlayerPosition(i) + numRolled);
            System.out.println("The current position of the player is " + getNthPlayerPosition(i));
            getFieldType(i);
            if (dropOutPlayer(i)) i--;
            if (i == database.getPlayersSize()-1) {
                numberOfRounds++;
                i = -1;
            }
            if (numberOfRounds == rounds+1) break;
        }
        report();
    }
    
    /**
     * Generates rounds based on a set value of dice rolls
     * @param rounds - number of rounds
     * @param pr List of dice rolls
     */
    public void generateRounds(int rounds, ArrayList<Integer> pr) {
        int numberOfRounds = 1;
        //System.out.println("ROUNDS: " + rounds);
        for (int i = 0, j = 0; i < database.getPlayersSize() && j < database.diceRolls.size(); i++, j++) {
            {
                System.out.println("---------------------------------------------");
                System.out.println("Current round: " + numberOfRounds);
                
                int numRolled = database.diceRolls.get(j);
                System.out.println("The current player (" + database.getNthPlayer(i).getName() + ", " + i + ") rolled " + numRolled);
            
                setNthPlayerPosition(i, getNthPlayerPosition(i) + numRolled);
                System.out.println("The current position of the player is " + getNthPlayerPosition(i));
                getFieldType(i);
                if (dropOutPlayer(i)) i--;
                if (i == database.getPlayersSize()-1) {
                    numberOfRounds++;
                    i = -1;
                }
                if (numberOfRounds-1 > rounds) break;
            }
        }
        report();
    }
}
