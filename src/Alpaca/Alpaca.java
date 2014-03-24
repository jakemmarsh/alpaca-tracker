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
	public Alpaca (String name)
	{
		this.name = name;
	}
	
	// --------------------------------------------------
	public String getName ()
	{
		return name;
	}
	
	// --------------------------------------------------
	public float getBodyTemperature ()
	{
		return tracker.bodyTemperature ();
	}
	
	// --------------------------------------------------
	public boolean isLayingDown ()
	{
		return tracker.isLayingDown ();
	}
	
	// --------------------------------------------------
	public float [] location ()
	{
		return tracker.location ();
	}

}
