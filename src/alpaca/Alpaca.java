package alpaca;
import PacaTraca.PacaTraca;
import TestDriversAndFactories.PacaTracaHardwareFactory;

/**
 * This class represents an alpaca. Alpaca's have a tracker ID and 
 * a referenced hardware object. 
 * @author claytonpeterson
 *
 */
public class Alpaca {
	
	public PacaTraca hardware;
	private String   trackerID;
	private String   name;
	private String   dbRef;
	
	public Alpaca ()
	{
		PacaTracaHardwareFactory pthf = new PacaTracaHardwareFactory ();
		hardware = pthf.createPacaTraca (trackerID);
	}
	
	// =================================================================================
    // Setters 
	/**
	 * Sets a reference to the alpaca's database
	 * location
	 * @param location
	 * @param ID
	 * @author Clayton Peterson
	 */
	public void setDBRef (String location, int ID)
	{
		this.dbRef = location + "/" + ID;
	}
	
	/**
	 * Sets the name of the alpaca
	 * @param name
	 * @author Clayton Peterson 
	 */
	public void setName (String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the tracker ID of the alpaca
	 * @param id
	 * @author Clayton Peterson
	 */
	public void setTrackerID (String id)
	{
		this.trackerID = String.valueOf (id);
	}
	
	// =================================================================================
	// Getters
	/**
	 * @return A reference to the alpacas data 
	 * @author Clayton Peterson 
	 */
	public String getDatabaseRef ()
	{
		return this.dbRef;
	}
	
	/**
	 * @return The alpaca's name
	 * @author Clayton Peterson
	 */
	public String getName ()
	{
		return name;
	}
	
	/**
	 * @return The alpaca's tracker ID
	 * @author Clayton Peterson
	 */
	public String getTrackerID ()
	{
		return this.trackerID;
	}
}
