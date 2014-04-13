package alpaca;
import PacaTraca.PacaTraca;
import TestDriversAndFactories.PacaTracaHardwareFactory;

public class Alpaca {
	
	public PacaTraca hardware;
	private String   trackerID;
	public  String   name;
	public  String   dbRef;
	private String   longitude;
	private String   latitude;
	
	public Alpaca ()
	{
		PacaTracaHardwareFactory pthf = new PacaTracaHardwareFactory ();
		hardware = pthf.createPacaTraca (trackerID);
	}

	public void setName (String name)
	{
		this.name = name;
	}
	
	public void setDBRef (String location, int ID)
	{
		this.dbRef = location + "/" + ID;
	}
	
	public void setTrackerID (String id)
	{
		this.trackerID = String.valueOf (id);
	}
	
	public void setLongitude (String longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude (String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getTrackerID ()
	{
		return this.trackerID;
	}
	
	public String getLongitude ()
	{
		return this.longitude;
	}
	
	public String getLatitude ()
	{
		return this.latitude;
	}
	
	public String getName ()
	{
		return name;
	}
}
