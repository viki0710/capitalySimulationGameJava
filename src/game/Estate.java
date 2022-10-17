package game;

/**
 *
 * @author Balla Viktória
 */
public class Estate extends Field {
    private boolean owned;
    private boolean house;
    private Player owner;
    
    /**
     * Constructor
     */
    public Estate() {
        owned = false;
        house = false;
        owner = new Player("None", "none", 0);
    }

    /**
     * Check if field is owned
     * @return if this field is owned
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * Set if field is owned
     * @param owned - is this field owned
     */
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    /**
     * Check if field contains a house
     * @return if there is a house
     */
    public boolean isHouse() {
        return house;
    }

    /**
     * Set if there is a house
     * @param house - is there house
     */
    public void setHouse(boolean house) {
        this.house = house;
    }
    
    /**
     * Gets type of field
     * @return Type of field(estate)
     */
    public String getType() {
        return "Estate";
    }

    /**
     * Gets owner of field
     * @return Owner of field
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

    /**
     * toString override
     * @return Description of estate
     */
    @Override
    public String toString() {
        return "Estate{" + "owned=" + owned + ", house=" + house + ", owner=" + owner + '}';
    }
    

    
}
