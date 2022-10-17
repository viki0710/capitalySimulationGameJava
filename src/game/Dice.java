package game;

/**
 *
 * @author Balla Viktória
 */
public class Dice {
    private int currentNumber;
    
    /**
     * Constructor, rolls a number between 1 and 6
     */
    public Dice() {
        currentNumber = (int)(Math.random()*(6-1+1)+1);  
    }

    /**
     * Gets currently rolled number
     * @return current number rolled
     */
    public int getCurrentNumber() {
        return currentNumber;
    }

    /**
     * Sets current number
     * @param currentNumber - current number rolled
     */
    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }
    
    /**
     * Generates random number between 1 and 6
     * @return Returns random number between 1 and 6 
     */
    public int rollNewNumber () {
        int num = (int)(Math.random()*(6-1+1)+1);
        setCurrentNumber(num);
        return num;
    }
    
}
