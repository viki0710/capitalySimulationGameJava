package game;

import java.util.ArrayList;

/**
 *
 * @author Balla Viktória
 */
public class Player {
    private String name;
    private String strategy;
    private int money;
    private ArrayList<Field> estates;

    private boolean secondBuy;
    
    /**
     *
     * @param name - player name
     * @param strategy - strategy
     * @param money - current balance
     */
    public Player(String name, String strategy, int money) {
        this.name = name;
        this.strategy = strategy;
        this.money = money;
        this.secondBuy = false;
        //estates = new ArrayList<>();
    }
    
    /*public void addEstate(Field e) {
        estates.add(e);
    }*/

    /**
     * get name of player
     * @return name - Player name
     */
    
    public String getName() {
        return name;
    }

    /**
     * Set name of player
     * @param name - Player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get type of strategy the player uses
     * @return Strategy of player
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * Set strategy of player
     * @param strategy - player strategy
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    /**
     * Get current money of player
     * @return Balance of the player
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set current money of player
     * @param money - balance of the player
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * Check if the player is on the second time on an estate field
     * This is for when the player can buy house.
     * @return Is it a second buy
     */
    public boolean isSecondBuy() {
        return secondBuy;
    }

    /**
     * Set if the player is on the second time on an estate field
     * @param secondBuy - is player the second time on an estate field
     */
    public void setSecondBuy(boolean secondBuy) {
        this.secondBuy = secondBuy;
    }
    
    /**
     * toString override
     * @return Description of player (name, strategy, balance)
     */
    @Override
    public String toString() {
        return "Player{" + "name:" + name + ", strategy:" + strategy + ", balance: " + money + '}';
        //return "Player{" + "name:" + name + ", strategy:" + strategy + ", estates owned:" + estates + '}';
    }
}
