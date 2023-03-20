
/**
 * This class contains the items that are in the rooms in the RVCC campus.
 *
 * @author Max Romano
 * @version 2023.03.20
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
    }

    /**
     * Accessor method for description.
     * @return the description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Accessor method for the weight.
     * @return the weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Returns the string describing the item.
     * @return the string describing the item.
     */
    public String getLongDescription()
    {
        return "There is " + description + ".";
    }
}
