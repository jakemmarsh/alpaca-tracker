package PacaTraca;

/**
 * @author Bryan Wells
 *
 * interface to GPS data for a given Alpaca.  Any GPS Sensor must
 * implement these methods
 */
public interface PacaTraca {
	public Float getLatitudeDecimalDegrees( ); // negative == South
	public Float getLongitudeDecimalDegrees( ); // negative == West	
	public Float getHeartRate( );
	public Float getSpeed( ); // feet per second
	public Float getCourse( ); // decimal degrees
	public Float getAltitude( ); // feet
	public Integer getNumSatellites( ); // number of satellites GPS found
	public Boolean haveFix( ); // true if GPS has a fix on at least one satellite
	public Integer getSignalQuality( ); // 1-5; 5 is best quality
	public Float getTemperature( ); //fahrenheit
	public Float getRoll( ); // rotate about y (-90 to 90 degrees)
	public Float getPitch( ); // rotate about x degrees (0-180 degrees)
	public Float getCompassHeading( ); // heading 0-360 degrees
	public void setSeaLevelPressure( Float pressure ); // sensor pressure at sea level in hPa
	public String getSensorID( ); // return the ID of this sensor/collar
	public void setSensorID( String ID ); // set the value of this sensor's ID

	
	/**
	 * Set data for testing
	 */
	public void setLongitude( float longitude);
	public void setLatitude( float latitude );
	public void setSpeed( float speed );
	public void setCourse( float course );
	public void setNumSatellites( int numSatellites );
	public void setHeading( float heading );
	public void setPitch( float pitch );
	public void setRoll( float roll );
	public void setAltitude( float altitude );
	public void setSignalQuality( int signalQuality );
	public void setTemperature( float temperature );
	public void setFix( boolean fix );
	public void setIsRandom( boolean r );
}

