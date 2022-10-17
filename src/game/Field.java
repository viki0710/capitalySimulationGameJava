package game;

/**
 *
 * @author Balla Viktória
 */
public abstract class Field {

    /**
     * 
     */
    public int price;
    private boolean owned;
    private boolean house;
    private Player owner;
    
    /**
     * Constructor
     */
    public Field() {
    }
    
    /**
     * Gets type of field
     * @return Type of field (none, this is the default)
     */
    public String getType(){
        return "none";
    }
    
    /**
     * Gets price of field
     * @return Price of field
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Sets price of field
     * @param price - price of current field
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    /**
     * Checks if field is owned
     * @return if field is owned
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * Sets if field is owned
     * @param owned - is it owned
     */
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    /**
     * Checks if there is a house
     * @return is there a house
     */
    public boolean isHouse() {
        return house;
    }

    /**
     * Sets if there is a house
     * @param house - is there a house
     */
    public void setHouse(boolean house) {
        this.house = house;
    }
    
    /**
     * Gets owner of field
     * @return owner of field
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets owner of field
     * @param owner - owner of field
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
