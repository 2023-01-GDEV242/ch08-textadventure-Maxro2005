
/**
 * This class contains information about some NPCs that are around campus.
 *
 * @author Max Romano
 * @version 2023.03.20
 */
public class NPC
{
    // instance variables - replace the example below with your own
    private String name;
    private String voiceline;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(String name, String voiceline)
    {
        // initialise instance variables
        this.name = name;
        this.voiceline = voiceline;
    }

    /**
     * Accessor method for name.
     * @return name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method for voiceline.
     * @return voiceline.
     */
    public void sayVoiceline()
    {
        System.out.println(name + ": " + voiceline);
    }
}
