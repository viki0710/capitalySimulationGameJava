package game;

/**
 *
 * @author Balla Viktória
 */
public class Lucky extends Field {
   
    /**
     * Constructor
     * @param price - how much money player can win
     */
    public Lucky(int price) {
        this.price = price;
    }

    /**
     * Get type of field
     * @return type of field (lucky)
     */
    public String getType() {
        return "Lucky";
    }
        
    /**
     * Get price of field
     * @return price of field player can win
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Set price of field
     * @param bonus - price user can win
     */
    public void setPrice(int bonus) {
        this.price = bonus;
    }

    /**
     * toString override
     * @return Description of field
     */
    @Override
    public String toString() {
        return "Lucky{" + "price=" + price + '}';
    }
    
}
