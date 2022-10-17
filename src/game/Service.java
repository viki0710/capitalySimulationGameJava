package game;

/**
 *
 * @author Balla Viktória
 */
public class Service extends Field {

    /**
     * Price of service
     */
    public int price;
    
    /**
     *Constructor of Service (sets price)
     * @param price - price of field
     */
    public Service(int price) {
        this.price = price;
    }

    /**
     * Gets the type of field
     * @return "Service"
     */
    public String getType() {
        return "Service";
    }
    
    /**
     * Gets price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price
     * @param price - price of field
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * toString override
     * @return Description of service with price
     */
    @Override
    public String toString() {
        return "Service{" + "price=" + price + '}';
    }
    
}
