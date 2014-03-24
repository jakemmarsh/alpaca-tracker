package Alpaca;

import pacatraca.GPSDataImpl;
import pacatraca.IMUDataImpl;

public class Tracker {
	
	private Integer     ID;
	private GPSDataImpl gpsData;
	private IMUDataImpl imuData;
	
	public Tracker (int id)
	{
		this.ID = id;
		this.gpsData = new GPSDataImpl ();
		this.imuData = new IMUDataImpl ();
	}
	
	// ======================= GPS Queries ======================= 
	// -----------------------------------------------------------
	/** 
	 * This method returns the tracker's location as an array of
	 * three floating point numbers. Longitude, Latitude, Altitude.
	 * @return
	 */
	public float [] getLocation ()
	{
		float [] loc = new float [3];
		if (gpsData.isConnected())
		{
			loc [0] = gpsData.getLongitudeDecimalDegrees (); // X
			loc [1] = gpsData.getLatitudeDecimalDegrees  (); // Z
			loc [2] = gpsData.getAltitude ();  				 // Y
		}
		return loc;
	}
	
	// -----------------------------------------------------------
	/**
	 * This method returns the speed that the alpaca is moving at 
	 * @return
	 */
	public float getSpeed ()
	{
		if (gpsData.isConnected ())
			return gpsData.getSpeed ();
		else
			return 0.0f;
	}
	
	// ======================= IMU Queries ======================= 
	// -----------------------------------------------------------
	/**
	 * This returns the temperature of the alpaca
	 * @return
	 */
	public float getTemperature ()
	{
		if (gpsData.isConnected ())
			return imuData.Temperature ();
		else
			return 0.0f;
	}
}
