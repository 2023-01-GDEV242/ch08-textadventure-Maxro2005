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
                westBuilding, workforceBldg;
      
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
        
        // initialise room exits
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

        currentRoom = mainEntrance;  // start game outside
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
            currentRoom = nextRoom;
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
}
