import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Each room can also contain items and/or NPCs.
 * 
 * @author Max Romano
 * @version 2023.03.20
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> itemMap;
    private ArrayList<Item> items;
    private NPC npc;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        itemMap = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Accessor method for NPC.
     * @return NPC.
     */
    public NPC getNPC()
    {
        return npc;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Adds an item to a room.
     * @param item the item to be added.
     */
    public void addItem(Item item)
    {
        itemMap.put(item.getString(), item);
        items.add(item);
    }
    
    /**
     * Adds an NPC to a room.
     * @param npc the NPC to be added.
     */
    public void addNPC(NPC npc)
    {
        this.npc = npc;
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String longDescription =
        "You are " + description + ".\n" + getExitString();
        for (Item item : items)
            longDescription = longDescription + "\n"
                                + item.getLongDescription();
        if (npc != null)
            longDescription = longDescription + "\nNPC: "
                                + npc.getName();
        return longDescription;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Return the item that the player is trying to take. If there is no
     * item with that itemString, return null.
     * @param itemString the item's string.
     * @return the item taken.
     */
    public Item getItem(String itemString)
    {
        return itemMap.get(itemString);
    }
    
    public void removeItem(Item item)
    {
        itemMap.remove(item.getString());
        items.remove(item);
    }
}

