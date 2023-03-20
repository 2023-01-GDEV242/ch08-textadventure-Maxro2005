import java.util.ArrayList;

/**
 *  This class is the main class of my text adventure game. For now,
 *  players can simply walk around and explore the RVCC campus.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Max Romano
 * @version 2023.03.13
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room mainEntrance, artsCenter, batemanStudentCenter,
                childrensCampus, collegeCenter, courtyard, eastBuilding,
                eventCenter, hunterdonHall, library, physicalEducation,
                planetarium, scienceCenter, somersetHall, theatre,
                westBuilding, workforceBldg, trapdoor;
      
        // create the rooms
        mainEntrance = new Room("outside the main entrance to the "
                                    + "college");
        artsCenter = new Room("in the center for arts students");
        batemanStudentCenter = new Room("in the Bateman Student Center");
        childrensCampus = new Room("in the Children's Campus");
        collegeCenter = new Room("in the College Center");
        courtyard = new Room("in the courtyard");
        eastBuilding = new Room("in the East Building");
        eventCenter = new Room("in the Event Center");
        hunterdonHall = new Room("in Hunterdon Hall");
        library = new Room("in the Evelyn S. Field Library");
        physicalEducation = new Room("in the Physical Education complex");
        planetarium = new Room("in the Planetarium");
        scienceCenter = new Room("in the Whitman Science Center");
        somersetHall = new Room("in Somerset Hall");
        theatre = new Room("in the Theatre");
        westBuilding = new Room("in the West Building");
        workforceBldg = new Room("in the Workforce Training Center");
        trapdoor = new Room("trapped. Game over");
        
        // initialize room exits
        mainEntrance.setExit("north", somersetHall);
        mainEntrance.setExit("east", eventCenter);
        mainEntrance.setExit("west", theatre);

        artsCenter.setExit("north", childrensCampus);
        artsCenter.setExit("south", batemanStudentCenter);
        
        batemanStudentCenter.setExit("north", artsCenter);
        batemanStudentCenter.setExit("south", physicalEducation);
        
        childrensCampus.setExit("south", artsCenter);
        
        collegeCenter.setExit("east", hunterdonHall);
        collegeCenter.setExit("south", courtyard);
        collegeCenter.setExit("west", physicalEducation);
        
        courtyard.setExit("north", collegeCenter);
        courtyard.setExit("east", eastBuilding);
        courtyard.setExit("south", library);
        
        eastBuilding.setExit("north", hunterdonHall);
        eastBuilding.setExit("south", planetarium);
        eastBuilding.setExit("west", courtyard);
        
        eventCenter.setExit("north", somersetHall);
        eventCenter.setExit("west", mainEntrance);
        
        hunterdonHall.setExit("south", eastBuilding);
        hunterdonHall.setExit("west", collegeCenter);
        
        library.setExit("north", courtyard);
        library.setExit("east", somersetHall);
        library.setExit("south", theatre);
        library.setExit("west", westBuilding);
        
        physicalEducation.setExit("north", batemanStudentCenter);
        physicalEducation.setExit("east", collegeCenter);
        physicalEducation.setExit("south", westBuilding);
        physicalEducation.setExit("west", scienceCenter);
        
        planetarium.setExit("north", eastBuilding);
        planetarium.setExit("west", somersetHall);
        
        scienceCenter.setExit("east", physicalEducation);
        scienceCenter.setExit("south", westBuilding);
        scienceCenter.setExit("west", workforceBldg);
        
        somersetHall.setExit("north", courtyard);
        somersetHall.setExit("east", planetarium);
        somersetHall.setExit("south", mainEntrance);
        somersetHall.setExit("west", library);
        
        theatre.setExit("north", library);
        theatre.setExit("east", mainEntrance);
        theatre.setExit("west", westBuilding);
        
        westBuilding.setExit("north", scienceCenter);
        westBuilding.setExit("east", physicalEducation);
        westBuilding.setExit("west", workforceBldg);
        
        workforceBldg.setExit("east", westBuilding);
        workforceBldg.setExit("west", trapdoor);

        currentRoom = mainEntrance;  // start game outside
        
        // create the items
        Item paintbrush, cheatSheet, dandelion, goodieBag, mask, book,
        dumbbell, safetyGoggles, essay, computer, makeup;
        
        paintbrush = new Item("a paintbrush", 3, "paintbrush");
        cheatSheet = new Item("a notes sheet for some math class", 3,
                                "cheatSheet");
        dandelion = new Item("a dandelion", 1, "dandelion");
        goodieBag = new Item("a goodie bag from an on-campus event", 10,
                                "goodieBag");
        mask = new Item("a nursing mask", 3, "mask");
        book = new Item("a book titled 'A Tale of Two Cities'", 20, "book");
        dumbbell = new Item("a 25-pound dumbbell", 25, "dumbbell");
        safetyGoggles = new Item("a pair of safety goggles from a lab", 5,
                                    "safetyGoggles");
        essay = new Item("an essay from an English class", 5, "essay");
        computer = new Item("a computer", 40, "computer");
        makeup = new Item("a makeup set", 10, "makeup");
        
        // initialize room items
        artsCenter.addItem(paintbrush);
        batemanStudentCenter.addItem(cheatSheet);
        courtyard.addItem(dandelion);
        eventCenter.addItem(goodieBag);
        hunterdonHall.addItem(mask);
        library.addItem(book);
        physicalEducation.addItem(dumbbell);
        scienceCenter.addItem(safetyGoggles);
        somersetHall.addItem(essay);
        somersetHall.addItem(book);
        westBuilding.addItem(computer);
        westBuilding.addItem(cheatSheet);
        workforceBldg.addItem(makeup);
        
        // create and initialize the player
        player = new Player();
        player.updateRoom(mainEntrance);
        
        // create and add NPCs
        NPC greg, linda;
        
        greg = new NPC("Greg", "Welcome to RVCC!");
        linda = new NPC("Linda", "They use a lot of computers in the WEST"
                        + " Building.");
                        
        mainEntrance.addNPC(greg);
        collegeCenter.addNPC(linda);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the RVCC Campus!");
        System.out.println("RVCC is your local community college.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                eat();
                break;
                
            case BACK:
                back(command);
                break;
                
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
                
            case TALK:
                talk();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            player.updateRoom(currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
    
    /**
     * Prints out the description of the room and the exits again (like
     * looking around the room).
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Prints out a message saying that the player has eaten and is no
     * longer hungry.
     */
    private void eat()
    {
        System.out.println("You have eaten and can now carry more items.");
        player.updateCarryCapacity(25);
    }
    
    /**
     * Takes the player into the previous room they were in.
     */
    private void back(Command command)
    {
        if(command.hasSecondWord())
        {
            System.out.println("Back what?");
        }
        else
        {
            if (previousRoom == null)
            {
                System.out.println("You have just started!");
            }
            else if (previousRoom == currentRoom)
            {
                System.out.println("You can only go back once.");
            }
            else
            {
                currentRoom = previousRoom;
                player.updateRoom(currentRoom);
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    /**
     * Takes an item from the room.
     */
    private void take(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        
        String itemString = command.getSecondWord();

        // Try to take the item.
        Item item = currentRoom.getItem(itemString);

        if (item == null) {
            System.out.println("That is not an available item.");
        }
        else {
            if (item.getWeight() > player.getCarryCapacity())
                System.out.println("Item is too heavy. Drop an item or eat"
                                    + " to be able to take it.");
            else
            {
                player.takeItem(item);
                currentRoom.removeItem(item);
                System.out.println("Item taken.");
            }
        }
    }
    
    /**
     * Drops an item into the room.
     */
    private void drop(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Drop what?");
            return;
        }
        
        String itemString = command.getSecondWord();
        player.dropItem(itemString);
    }
    
    /**
     * Talks to an NPC.
     */
    private void talk()
    {
        if (currentRoom.getNPC() == null)
        {
            System.out.println("There is no one to talk to.");
            return;
        }
        
        currentRoom.getNPC().sayVoiceline();
    }
}
