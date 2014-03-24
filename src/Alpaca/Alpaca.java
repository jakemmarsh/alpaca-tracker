package Alpaca;

/**
 * 
 * @author clayton peterson
 * This class contains the information pertaining to an 
 * individual alpaca. 
 */
public class Alpaca {

	// The unique id number to map the alpaca to its tracker
	private int ID;
	
	// The name of the alpaca
	private String name;
	
	// The tracker mounted on the alpaca
	private TestTracker tracker; 

	// --------------------------------------------------
	public Alpaca (String name, int ID)
	{
		this.ID   = ID;
		this.name = name;
		System.out.println ("Alpaca: " + name + ", with ID: " + ID);
	}
	
	// --------------------------------------------------
	/* Returns the name of the alpaca */
	public String getName ()
	{
		return name;
	}
	
	// --------------------------------------------------
	/* Returns the body temperature of the alpaca */
	public float getBodyTemperature ()
	{
		return tracker.bodyTemperature ();
	}
	
	// --------------------------------------------------
	/* Returns a boolean indicating whether the alpaca
	 * is laying down or not. */
	public boolean isLayingDown ()
	{
		return tracker.isLayingDown ();
	}
	
	// --------------------------------------------------
	/* Returns the alpacas location in terms of latitude,
	 * longitude, and altitude, in that order. */
	public float [] location ()
	{
		return tracker.location ();
	}
}
