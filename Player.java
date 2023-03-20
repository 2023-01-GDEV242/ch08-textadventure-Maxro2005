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
}
