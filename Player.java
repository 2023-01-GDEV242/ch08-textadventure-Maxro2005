import java.util.ArrayList;

/**
 * This class contains information about the player.
 *
 * @author Max Romano
 * @version 2023.03.20
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int carryCapacity;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        inventory = new ArrayList<Item>();
        carryCapacity = 50;
    }

    /**
     * Accessor method for inventory.
     * @return inventory.
     */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
    
    /**
     * Accessor method for carry capacity.
     * @return carry capacity.
     */
    public int getCarryCapacity()
    {
        return carryCapacity;
    }
    
    /**
     * Updates the current room of the player.
     * @param newRoom the current room.
     */
    public void updateRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
    /**
     * Changes the carry capacity of the player.
     * @param change the value of the change, can be positive or negative.
     */
    public void updateCarryCapacity(int change)
    {
        carryCapacity += change;
    }
    
    /**
     * Takes an item from the current room.
     * @param item the item to be taken.
     */
    public void takeItem(Item item)
    {
        inventory.add(item);
        int weightIncrease = item.getWeight() * -1;
        updateCarryCapacity(weightIncrease);
    }
    
    /**
     * Drops an item into the current room.
     * @param itemString the identifying string of the item to be dropped.
     */
    public void dropItem(String itemString)
    {
        Item item = null;
        for (Item items : inventory)
        {
            if (itemString.equals(items.getString()))
                item = items;
        }

        if (item == null) {
            System.out.println("You do not have that item.");
        }
        else {
            currentRoom.addItem(item);
            updateCarryCapacity(item.getWeight());
            inventory.remove(item);
            System.out.println("Item dropped.");
        }
    }
}
