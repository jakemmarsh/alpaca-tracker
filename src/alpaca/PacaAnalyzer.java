package alpaca;

import java.awt.Polygon;

import java.util.ArrayList;

/**
 * @author Sylvia Allain
 * 
 * Analyzes Alpaca objects on an ongoing basis or on-demand.
 */
public class PacaAnalyzer {

	private PacaWorld pacaWorld;
	
	public PacaAnalyzer(PacaWorld pacaWorld)
	{
		this.pacaWorld = pacaWorld;
	}
	
	public void analyze (ArrayList alpacas)
	{
	}
	
	/**
	 * @param latitude, longitude
	 * @return whether the alpaca is out of bounds
	 */
	public String analyzeLocationBounds(float latitude, float longitude) {
		String state = "";
		
		return state;
	}

	private static int[] xPoints = {10,10,100,200,200};
	private static int[] yPoints = {10,200,100,200,10};
	private static Polygon propertyBoundaries = new Polygon(xPoints, yPoints, 5);
	
	public static void main (String [] args) {
		System.out.println(propertyBoundaries.contains(100,120));
	}
	
	/**
	 * @param latitude, longitude
	 * @return whether the alpaca is isolated
	 */
	public String analyzeLocationIsolation(float latitude, float longitude) {
		
		String state = "";
		
		return state;
	}
	
	/**
	 * @param speed
	 * @return the type of movement
	 */
	public String analyzeSpeed(float speed){
		
		float alpacaSpeedCeiling = 20f;
		float alpacaRunningSpeedMin = 10f;
		float alpacaWalkingSpeedMin = 2f;
		
		String state = "";
		
		if (speed > alpacaSpeedCeiling)
			state = "Error: High speed value";
		else if (speed > alpacaRunningSpeedMin)
			state = "Alpaca running";
		else if (speed > alpacaWalkingSpeedMin)
			state = "Alpaca walking";
		else if (speed > 0)
			state = "Alpaca standing";
		else
			state = "Error: Negative speed value";
			
		return state;
	}
	
	/**
	 * @param course
	 * @return direction the alpaca is travelling
	 */
	public String analyzeCourse(float course) {
		String state = "";
		
		if (course < 0)
			state = "Error: Negative course value";
		else if (course > 360)
			state = "Error: High course value";
		else
			state = Float.toString(course);
		
		return state;
	}
	
	/**
	 * @param number of satellites
	 * @return string number of satellites
	 */
	public String analyzeNumSatellites(int numSatellites) {
		String state = "";
		
		if (numSatellites < 0)
			state = "Error: Negative satellite number";
		else if (numSatellites > pacaWorld.returnNumSatellites())
			state = "Error: High satellite number";
		else
			state = Integer.toString(numSatellites);
		
		return state;
	}
	
	/**
	 * @param heading
	 * @return string heading
	 */
	public String analyzeHeading(float heading){
		String state = "";
		
		if (heading < 0)
			state = "Error: Negative heading value";
		else if (heading > 360)
			state = "Error: High heading value";
		else
			state = Float.toString(heading);
		
		return state;
	}
	
	/**
	 * @param pitch, roll
	 * @return type of position
	 */
	public String analyzePosition(float pitch, float roll){
		String state = "";
		
		if (pitch < 0 || pitch > 180 || roll < -90 || roll > 90)
			state = "Error: Invalid pitch and roll values";
		else if (pitch < 40 || pitch > 140 || roll < -50 || roll > 50)
			state = "Alpaca is laying down";
		else
			state = "Alpaca is standing";
		
		return state;
	}

	/**
	 * @param altitude
	 * @return string altitude
	 */
	public String analyzeAltitude(float altitude) {
		String state = "";
		
		if (altitude < pacaWorld.returnAltitudeFloor())
			state = "Error: Negative altitude value";
		else if (altitude > pacaWorld.returnAltitudeCeiling())
			state = "Error: High altitude value";
		else
			state = Float.toString(altitude);
		
		return state;
	}
	
	/**
	 * @param signal quality
	 * @return string signal quality
	 */
	public String analyzeSignalQuality(int signalQuality) {
		String state = "";
		int signalCeiling = 5;
		
		if (signalQuality < 0)
			state = "Error: Negative signal value";
		else if (signalQuality > 5)
			state = "Error: High signal value";
		else
			state = Integer.toString(signalQuality);
		
		return state;
	}
	
	/**
	 * @param temperature
	 * @return health condition
	 */
	public String analyzeTemperature(float temperature) {
		String state = "";
		if (temperature < pacaWorld.returnTemperatureFloor())
			state = "Error: Low temperature value";
		else if (temperature > pacaWorld.returnTemperatureCeiling())
			state = "Error: High temperature value";
		else
			state = Float.toString(temperature);
		
		return state;
	}
	
	/**
	 * @param fix
	 * @return string fix
	 */
	public String analyzeFix(boolean fix) {
		String state = Boolean.toString(fix);
		
		return state;
	}
}