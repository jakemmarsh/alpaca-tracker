package PacaTraca;

import java.util.Random;

/**
 * @author Bryan Wells
 *
 * implementation of the GPS Sensor Data class
 * Provides a concrete implementation for an GPS Sensor
 * to service requests for GPS Data
 */
public class PacaTracaImpl implements PacaTraca {
	
	private String m_sensorID;
	private float longitude = 0;
	private float latitude = 0;
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
	
	private boolean isRandom = true;
	
	Random rand = new Random();
	
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
	 * @author Sylvia Allain
	 * negative == South
	 * @see com.pacatraca.PacaTraca#getLatitudeDecimalDegrees()
	 */
	@Override
	public Float getLatitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		if (isRandom)
			latitude = getRandomLatitude();
		return latitude;
	}

	/**
	 * @author Sylvia Allain
	 * negative == West
	 * @see com.pacatraca.PacaTraca#getLongitudeDecimalDegrees()
	 */
	@Override
	public Float getLongitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		if (isRandom)
			longitude = getRandomLongitude();
		return longitude;
	}

	/**
	 * @author Sylvia Allain
	 * feet per second
	 * @see com.pacatraca.PacaTraca#getSpeed()
	 */
	@Override
	public Float getSpeed() {
		// TODO Auto-generated method stub
		if (isRandom)
			speed = getRandomSpeed();
		return speed;
	}

	/**
	 * @author Sylvia Allain
	 * decimal degrees
	 * @see com.pacatraca.PacaTraca#getCourse()
	 */
	@Override
	public Float getCourse() {
		// TODO Auto-generated method stub
		if (isRandom)
			course = getRandomCourse();
		return course;
	}

	/**
	 * @author Sylvia Allain
	 * @return feet from sea level
	 * @see com.pacatraca.PacaTraca#getAltitude()
	 */
	@Override
	public Float getAltitude() {
		// TODO Auto-generated method stub
		if (isRandom)
			altitude = getRandomAltitude();
		return altitude;
	}

	/**
	 * @author Sylvia Allain
	 * @return number of satellites GPS found
	 * @see com.pacatraca.PacaTraca#getNumSatellites()
	 */
	@Override
	public Integer getNumSatellites() {
		// number of satellites GPS found
		// TODO Auto-generated method stub
		if (isRandom)
			numSatellites = getRandomNumSatellites();
		return numSatellites;
	}

	/**
	 * @author Sylvia Allain
	 * true if GPS has a fix on at least one satellite
	 * @see com.pacatraca.PacaTraca#haveFix()
	 */
	@Override
	public Boolean haveFix() {
		// TODO Auto-generated method stub
		if (isRandom)
			fix = getRandomFix();
		return fix;
	}

	/**
	 * @author Sylvia Allain
	 * Returns a signal quality of 1-5. 5 is the best quality.
	 * @see com.pacatraca.PacaTraca#getSignalQuality()
	 */
	@Override
	public Integer getSignalQuality() {
		// TODO Auto-generated method stub
		if (isRandom)
			signalQuality = getRandomSignalQuality();
		return signalQuality;
	}
	
	@Override
	/**
	 * @author Sylvia Allain
	 * @return The temperature in Fahrenheit
	 */
	public Float getTemperature() {
		// TODO Auto-generated method stub
		if (isRandom)
			temperature = getRandomTemperature();
		return temperature;
	}

	@Override
	/**
	 * @author Sylvia Allain
	 * Returns the motion sensor's roll reading
	 * -rotate about y (-90 to 90 degrees)
	 */
	public Float getRoll() {
		// TODO Auto-generated method stub
		if (isRandom)
			roll = getRandomRoll();
		return roll;
	}

	@Override
	/**
	 * @author Sylvia Allain
	 * Returns the motion sensor's pitch reading
	 * -rotate about x degrees (0-180 degrees)
	 */
	public Float getPitch() {
		// TODO Auto-generated method stub
		if (isRandom)
			pitch = getRandomPitch();
		return pitch;
	}

	@Override
	/**
	 * @author Sylvia Allain
	 * Returns the motion sensor's heading reading
	 * -heading 0-360 degrees
	 */
	public Float getCompassHeading() {
		// TODO Auto-generated method stub
		if (isRandom)
			heading = getRandomHeading();
		return heading;
	}

	@Override
	/**
	 * @author Sylvia Allain
	 * I have no idea
	 * sensor pressure at sea level in hPa
	 */
	public void setSeaLevelPressure(Float pressure) {
		// TODO Auto-generated method stub
		
	}	
	
	/**
	 * @author Sylvia Allain
	 * @param ID
	 * @return a random longitude coordinate between -68 and -69, roughly in Orono
	 */
	private float getRandomLongitude() {
		int floor = 68;
		int ceiling = 69;
		
		return -(rand.nextFloat() * (ceiling - floor) + floor);
	}
	
	/**
	 * @author Sylvia Allain
	 * @param ID
	 * @return a random latitude coordinate between 44 and 45, roughly in Orono
	 */
	private float getRandomLatitude() {
		int floor = 44;
		int ceiling = 45;
		
		return rand.nextFloat() * (ceiling - floor) + floor;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random speed between 0 and 15
	 */
	private float getRandomSpeed() {
		return rand.nextFloat() * 15;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random course between 0 and 360
	 */
	private float getRandomCourse() {
		return rand.nextFloat() * 360;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random number of satellites between 0 and 5
	 */
	private int getRandomNumSatellites() {
		return rand.nextInt(6);
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random heading between 0 and 360
	 */
	private float getRandomHeading() {
		return rand.nextFloat() * 360;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random pitch between 0 and 180
	 */
	private float getRandomPitch() {
		return rand.nextFloat() * 180;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random roll between -90 and 90
	 */
	private float getRandomRoll() {
		return rand.nextFloat() * 180 - 90;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random altitude between 0 and 1000
	 */
	private float getRandomAltitude() {
		return rand.nextFloat() * 1000;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random signal quality between 0 and 5
	 */
	private int getRandomSignalQuality() {
		return rand.nextInt(6);
	}
	
	/**
	 * @author Sylvia Allain
	 * @return random temperature between 0 and 105
	 */
	private float getRandomTemperature() {
		return rand.nextFloat() * 105;
	}
	
	/**
	 * @author Sylvia Allain
	 * @return boolean fix
	 */
	private boolean getRandomFix() {
		return true;
	}
	
	
	/**
	 * @author Sylvia Allain
	 * Set data for testing
	 */
	
	public void setLongitude( float longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude( float latitude ) {
		this.latitude = latitude;
	}
	
	public void setSpeed( float speed ) {
		this.speed = speed;
	}
	
	public void setCourse( float course ) {
		this.course = course;
	}
	
	public void setNumSatellites( int numSatellites ) {
		this.numSatellites = numSatellites;
	}
	
	public void setHeading( float heading ) {
		this.heading = heading;
	}
	
	public void setPitch( float pitch ) {
		this.pitch = pitch;
	}
	
	public void setRoll( float roll ) {
		this.roll = roll;
	}
	
	public void setAltitude( float altitude ) {
		this.altitude = altitude;
	}
	
	public void setSignalQuality( int signalQuality ) {
		this.signalQuality = signalQuality;
	}
	
	public void setTemperature( float temperature ) {
		this.temperature = temperature;
	}
	
	public void setFix( boolean fix ) {
		this.fix = fix;
	}
	
	public void setIsRandom(boolean r) {
		this.isRandom = r;
	}
	
}
