package PacaTraca;

/**
 * @author Bryan Wells
 *
 * implementation of the GPS Sensor Data class
 * Provides a concrete implementation for an GPS Sensor
 * to service requests for GPS Data
 */
public class PacaTracaImpl implements PacaTraca {
	
	private String m_sensorID;
	private float longitude;
	private float latitude;
	private float speed;
	private float course;
	private int numSatellites;
	private float heading;
	private float pitch;
	private float roll;
	private float altitude;
	private int signalQuality;
	private float temperature;
	private boolean fix;
	
	/*
	 * protected default constructor
	 */
	public PacaTracaImpl( ) {
	}
	
	/**
	 * constructor taking an AlpacaID
	 */
	public PacaTracaImpl( String sensorID ) {
		this.setSensorID( sensorID ); // set the value of this sensor's ID
		//System.out.println("PacaTracaHardware constructor was called");
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#toString()
	 */
	@Override
	public String toString( ) {
		//return this.m_sensorID;
		return getFullStatus();
	}
	
	public String getFullStatus(){
		String outStr = "";
		outStr += "ID: " + getSensorID().toString() + "\n";
		outStr += "Signal: " + getSignalQuality().toString() + "\n";
		outStr += "Latitude: " + getLatitudeDecimalDegrees().toString() + "\n";
		outStr += "Longitude: " + getLongitudeDecimalDegrees().toString() + "\n";
		outStr += "Altitude: " + getAltitude().toString()  + "\n";
		outStr += "Course: " + getCourse().toString() + "\n";
		outStr += "Temperature (F): " + getTemperature().toString() + "\n";
		outStr += "Speed: " + getSpeed().toString() + "\n";
		outStr += "Has satellite fix: " + haveFix().toString() + "\n";
		outStr += "Heading: " + getCompassHeading().toString() + "\n";
		outStr += "Pitch: " + getPitch().toString() + "\n";
		outStr += "Roll: " + getRoll() + "\n";
		outStr += "Has satellite fix: " + haveFix().toString() + "\n";
		outStr += "Detected satellites: " + getNumSatellites() + "\n";
		
		return outStr;
	}

	/**
	 * Returns the sensor ID for this hardware
	 * @see com.pacatraca.getSensorID()
	 */
	@Override
	public String getSensorID( ) {
		// return the ID of this sensor/collar
		// TODO Auto-generated method stub
		return m_sensorID;
	}
	
	/**
	 * @see com.pacatraca.getSensorID()
	 */
	@Override
	public void setSensorID( String id ) {
		// TODO Auto-generated method stub
		this.m_sensorID = id;
	}
	
	/**
	 * negative == South
	 * @see com.pacatraca.PacaTraca#getLatitudeDecimalDegrees()
	 */
	@Override
	public Float getLatitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		return latitude;
	}

	/**
	 * negative == West
	 * @see com.pacatraca.PacaTraca#getLongitudeDecimalDegrees()
	 */
	@Override
	public Float getLongitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		return longitude;
	}

	/**
	 * feet per second
	 * @see com.pacatraca.PacaTraca#getSpeed()
	 */
	@Override
	public Float getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	/**
	 * decimal degrees
	 * @see com.pacatraca.PacaTraca#getCourse()
	 */
	@Override
	public Float getCourse() {
		// TODO Auto-generated method stub
		return course;
	}

	/**
	 * @return feet from sea level
	 * @see com.pacatraca.PacaTraca#getAltitude()
	 */
	@Override
	public Float getAltitude() {
		// TODO Auto-generated method stub
		return altitude;
	}

	/**
	 * @return number of satellites GPS found
	 * @see com.pacatraca.PacaTraca#getNumSatellites()
	 */
	@Override
	public Integer getNumSatellites() {
		// number of satellites GPS found
		// TODO Auto-generated method stub
		return numSatellites;
	}

	/**
	 * true if GPS has a fix on at least one satellite
	 * @see com.pacatraca.PacaTraca#haveFix()
	 */
	@Override
	public Boolean haveFix() {
		// TODO Auto-generated method stub
		return fix;
	}

	/**
	 * Returns a signal quality of 1-5. 5 is the best quality.
	 * @see com.pacatraca.PacaTraca#getSignalQuality()
	 */
	@Override
	public Integer getSignalQuality() {
		// TODO Auto-generated method stub
		return signalQuality;
	}
	
	@Override
	/**
	 * @return The temperature in Fahrenheit
	 */
	public Float getTemperature() {
		// TODO Auto-generated method stub
		return temperature;
	}

	@Override
	/**
	 * Returns the motion sensor's roll reading
	 * -rotate about y (-90 to 90 degrees)
	 */
	public Float getRoll() {
		// TODO Auto-generated method stub
		return roll;
	}

	@Override
	/**
	 * Returns the motion sensor's pitch reading
	 * -rotate about x degrees (0-180 degrees)
	 */
	public Float getPitch() {
		// TODO Auto-generated method stub
		return pitch;
	}

	@Override
	/**
	 * Returns the motion sensor's heading reading
	 * -heading 0-360 degrees
	 */
	public Float getCompassHeading() {
		// TODO Auto-generated method stub
		return heading;
	}

	@Override
	/**
	 * I have no idea
	 * sensor pressure at sea level in hPa
	 */
	public void setSeaLevelPressure(Float pressure) {
		// TODO Auto-generated method stub
		
	}	
}
