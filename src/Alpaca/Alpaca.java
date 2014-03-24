package Alpaca;

/**
 * 
 * @author clayton peterson
 * This class contains information pertaining to an 
 * individual alpaca, retrieved from the alpaca's tracker 
 * device. 
 */
public class Alpaca {

	// The unique id number to map the alpaca to its tracker
	private int ID;
	
	// The name of the alpaca
	private String name;
	
	// The tracker mounted on the alpaca
	private Tracker tracker; 

	// -----------------------------------------------------------
	public Alpaca (String name, int ID)
	{
		this.ID   = ID;
		this.name = name;
		this.tracker = new Tracker (ID);
		System.out.println ("Alpaca: " + name + ", with ID: " + ID);
	}
	
	// -----------------------------------------------------------
	/* Returns the alpaca's ID which corresponds with it's tracker
	 * ID */
	public int getID ()
	{
		return ID;
	}
	
	// -----------------------------------------------------------
	/* Returns the name of the alpaca */
	public String getName ()
	{
		return name;
	}
	
	// -----------------------------------------------------------
	/* Returns the body temperature of the alpaca */
	public float getBodyTemperature ()
	{
		return tracker.getTemperature ();
	}
	
	// -----------------------------------------------------------
	/* Returns the alpaca's location in terms of longitude,
	 * latitude, and altitude, in that order. */
	public float[] getLocation ()
	{
		return tracker.getLocation ();
	}
	
	// -----------------------------------------------------------
	/* Returns the alpaca's movement speed */
	public float getSpeed ()
	{
		return tracker.getSpeed ();
	}
}
