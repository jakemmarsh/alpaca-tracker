package alpaca;

import java.awt.Polygon;

import java.util.ArrayList;
import helpers.Pair;

/**
 * @author Sylvia Allain, Jonathan Cole
 * 
 * Analyzes Alpaca objects on an ongoing basis or on-demand.
 */
public class PacaAnalyzer {

	private PacaWorld pacaWorld;
	
	public PacaAnalyzer(PacaWorld pacaWorld)
	{
		this.pacaWorld = pacaWorld;
	}
	
	/**
	 * Calls all analysis methods across each member of a list of alpacas.
	 * @param alpacas
	 */
	public void analyze (ArrayList<Alpaca> alpacas){
		for(Alpaca a : alpacas){
			analyzeLocationBounds(a);
			analyzeLocationIsolation(a, alpacas);
		}
	}
	
	/**
	 * @author Sylvia Allain
	 * @param latitude, longitude
	 * @return whether the alpaca is out of bounds
	 */
	public String analyzeLocationBounds(Alpaca alpaca) {
		
		float latitude = alpaca.hardware.getLatitudeDecimalDegrees();
		float longitude = alpaca.hardware.getLongitudeDecimalDegrees();
		
		String state = "";
		int roundWeight = 10000;
		
		int weightedLatitude = (int)(latitude * roundWeight);
		int weightedLongitude = (int)(longitude * roundWeight);
		
		ArrayList<float[]> farmCoordinates = pacaWorld.returnFarmCoordinates();

	
		int[] xPoints = new int[farmCoordinates.size()];
		int[] yPoints = new int[farmCoordinates.size()];
		
		int i = 0;
		int xCoor;
		int yCoor;
		for (float[] tuple : farmCoordinates) {
			xCoor = (int) (tuple[0] * roundWeight);
			yCoor = (int) (tuple[1] * roundWeight);
			
			xPoints[i] = xCoor;
			yPoints[i] = yCoor;
			
			i ++;
		}
		
		Polygon propertyPolygon = new Polygon(xPoints, yPoints, farmCoordinates.size());

		if (propertyPolygon.contains(weightedLatitude, weightedLongitude))
			state = "In bounds";
		else {
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.OutOfBounds);
			state = "Out of bounds";
		}
		
		return state;
	}
	
	/**
<<<<<<< HEAD
	 * @param latitude, longitude
=======
>>>>>>> bd8263a5ad2866064883b508385652f596979ad9
	 * @author Jonathan Cole
	 * Compares the alpaca specified against the list. If it's more than 10 feet away
	 * from any other alpaca, it is considered isolated.
	 * @return whether the alpaca is isolated
	 */
	public String analyzeLocationIsolation(Alpaca alpaca, ArrayList<Alpaca> alpacaList) {
		//get position on earth in feet from (0, 0)
		Pair baseFeet = degreesToFeet(alpaca.hardware.getLatitudeDecimalDegrees(), alpaca.hardware.getLongitudeDecimalDegrees());
		String state = "";
		boolean firstLoop = true;
		double lowestDistance = 0;
		for(Alpaca a : alpacaList){
			//Only run if the compared alpaca and the alpaca from the list are different.
			if(a != alpaca){
				Pair alpFeet = degreesToFeet(a.hardware.getLatitudeDecimalDegrees(), a.hardware.getLongitudeDecimalDegrees());
				//Get distance between points (baseLatitude, baseLongitude) and (alpLatitude, alpLongitude)
				double t1 = Math.pow(alpFeet.x - baseFeet.x, 2);
				double t2 = Math.pow(alpFeet.y - baseFeet.y, 2);
				double distance = Math.sqrt(t1 + t2);
				
				if(distance < lowestDistance || firstLoop){
					lowestDistance = distance;
					firstLoop = false;
				}
			}
			
		}
		
		//Create an alert if no alpacas are closer than maxAlpacaGroupDistance units away.
		if(lowestDistance > pacaWorld.returnMaxAlpacaGroupDistance()){
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.Isolated);
			state = "Alpaca is isolated";
		}
		else{
			state = "Alpaca is grouped";
		}
		
		return state;
	}
	
	/**
	 * @author Sylvia Allain
	 * @param speed
	 * @return the type of movement
	 */
	public String analyzeSpeed(Alpaca alpaca){
		
		float speed = alpaca.hardware.getSpeed();
		
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
	 * @author Sylvia Allain
	 * @param course
	 * @return direction the alpaca is travelling
	 */
	public String analyzeCourse(Alpaca alpaca) {
		
		float course = alpaca.hardware.getCourse();
		
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
	 * @author Sylvia Allain
	 * @param number of satellites
	 * @return string number of satellites
	 */
	public String analyzeNumSatellites(Alpaca alpaca) {
		
		int numSatellites = alpaca.hardware.getNumSatellites();
		
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
	 * @author Sylvia Allain
	 * @param heading
	 * @return string heading
	 */
	public String analyzeHeading(Alpaca alpaca){
		
		float heading = alpaca.hardware.getCompassHeading();
		
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
	 * @author Sylvia Allain
	 * @param pitch, roll
	 * @return type of position
	 */
	public String analyzePosition(Alpaca alpaca){
		
		float pitch = alpaca.hardware.getPitch();
		float roll = alpaca.hardware.getRoll();
		
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
	 * @author Sylvia Allain
	 * @param altitude
	 * @return string altitude
	 */
	public String analyzeAltitude(Alpaca alpaca) {
		
		float altitude = alpaca.hardware.getAltitude();
		
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
	 * @author Sylvia Allain
	 * @param signal quality
	 * @return string signal quality
	 */
	public String analyzeSignalQuality(Alpaca alpaca) {
		
		int signalQuality = alpaca.hardware.getSignalQuality();
		
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
	 * @author Sylvia Allain
	 * @param temperature
	 * @return health condition
	 */
	public String analyzeTemperature(Alpaca alpaca) {
		
		float temperature = alpaca.hardware.getTemperature();
		
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
	 * @author Sylvia Allain
	 * @param fix
	 * @return string fix
	 */
	public String analyzeFix(Alpaca alpaca) {
		
		boolean fix = alpaca.hardware.haveFix();
		
		String state = Boolean.toString(fix);
		
		return state;
	}
	
	/**
	 * @author Jonathan Cole
	 * Converts decimal degrees to feet, assuming a spherical projection of coordinates.
	 * TODO: Refactor into a more relevant class
	 * @param x
	 * @param y
	 * @return
	 */
	public Pair degreesToFeet(float x, float y){
		//60 nautical miles in a degree of latitude, 6076 feet in a nautical mile
		float xFeet = x * 60 * 6076;
		//A degree longitude is 60 nm at the equator and 0 nm at the poles.
		float yFeet = ((float)Math.cos((double)y) * 60) * 6076;
		Pair p = new Pair(xFeet, yFeet);
		return p;
	}
}