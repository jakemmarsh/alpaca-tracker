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
	public  String   name;
	public  String   dbRef;
	private String   longitude;
	private String   latitude;
	private Float    heartRate;
	
	public Alpaca ()
	{
		PacaTracaHardwareFactory pthf = new PacaTracaHardwareFactory ();
		hardware = pthf.createPacaTraca (trackerID);
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
	 * Sets the tracker ID of the alpaca
	 * @param id
	 * @author Clayton Peterson
	 */
	public void setTrackerID (String id)
	{
		this.trackerID = String.valueOf (id);
	}
	
	/**
	 * Sets the longitude of the alpaca
	 * @param longitude
	 * @author Clayton Peterson
	 */
	public void setLongitude (String longitude)
	{
		this.longitude = longitude;
	}
	
	/**
	 * Sets the latitude of the alpaca
	 * @param latitude
	 * @author Clayton Peterson
	 */
	public void setLatitude (String latitude)
	{
		this.latitude = latitude;
	}
	
	/**
	 * Sets the heartRate of the alpaca
	 * @param heartRate
	 * @author Jake Marsh
	 */
	public void setHeartRate (Float heartRate)
	{
		this.heartRate = heartRate;
	}
	
	/**
	 * @return The alpaca's tracker ID
	 * @author Clayton Peterson
	 */
	public String getTrackerID ()
	{
		return this.trackerID;
	}
	
	/**
	 * @return The alpaca's longitude
	 * @author Clayton Peterson
	 */
	public String getLongitude ()
	{
		return this.longitude;
	}
	
	/**
	 * @return The alpaca's latitude
	 * @author Clayton Peterson
	 */
	public String getLatitude ()
	{
		return this.latitude;
	}
	
	/**
	 * @return The alpaca's heartRate
	 * @author Jake Marsh
	 */
	public Float getHeartRate ()
	{
		return this.heartRate;
	}
	
	/**
	 * @return The alpaca's name
	 * @author Clayton Peterson
	 */
	public String getName ()
	{
		return name;
	}
}
