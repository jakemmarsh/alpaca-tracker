package alpaca;

import java.awt.Polygon;
import java.util.ArrayList;
import helpers.Pair;
import helpers.GPSTranslator;

/**
 * @author Sylvia Allain, Jonathan Cole, Clay Peterson
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
			analyzeIsolation(a, alpacas);
			analyzeBattery(a);
			analyzeHeartRate(a);
			analyzeSignalQuality(a);
			
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

		if (propertyPolygon.contains(weightedLatitude, weightedLongitude)){
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.OutOfBounds);
			alpaca.alertPriority = 0;
			state = "In bounds";
		}
		else {
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.OutOfBounds);
			alpaca.alertPriority = 3;
			state = "Out of bounds";
		}
		return state;
	}
	
	/**
	 * @param latitude, longitude
	 * @author Jonathan Cole
	 * Compares the alpaca specified against the list. If it's more than 10 feet away
	 * from any other alpaca, it is considered isolated.
	 * @return whether the alpaca is isolated
	 */
	public void analyzeIsolation(Alpaca alpaca, ArrayList<Alpaca> alpacaList) {
		//get position on earth in feet from (0, 0)
		Pair baseCoords = new Pair(alpaca.hardware.getLatitudeDecimalDegrees(), alpaca.hardware.getLongitudeDecimalDegrees());
		boolean firstLoop = true;
		double lowestDistance = 0;
		for(Alpaca a : alpacaList){
			//Only run if the compared alpaca and the alpaca from the list are different.
			if(a != alpaca){
				Pair alpCoords = new Pair(a.hardware.getLatitudeDecimalDegrees(), a.hardware.getLongitudeDecimalDegrees());
				double distance = GPSTranslator.getDistance(baseCoords.x, baseCoords.y, alpCoords.x, alpCoords.y, GPSTranslator.ProjectionType.Haversine);
				if(distance < lowestDistance || firstLoop){
					lowestDistance = distance;
					firstLoop = false;
				}
			}
		}
		
		//Create an alert if no alpacas are closer than maxAlpacaGroupDistance units away.
		if(lowestDistance > pacaWorld.returnMaxAlpacaGroupDistance()){
			//Alpaca is isolated
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.Isolated);
			alpaca.alertPriority = 2;
		}
		else{
			//Alpaca is grouped
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.Isolated);
			alpaca.alertPriority = 0;
		}
		
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
	 * @author Jonathan Cole
	 * Throws an alert if the specified alpaca's battery life is low.
	 * Threshold is defined PacaWorld as lowBatteryWarningThreshold.
	 */
	public String analyzeBattery(Alpaca alpaca){
		String state = "";
		if(alpaca.hardware.getBatteryLife() < pacaWorld.returnLowBatteryWarningThreshold()){
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.BatteryLow);
			alpaca.alertPriority = 1;
			state = "Alpaca battery is low";
		}
		else{
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.BatteryLow);
			alpaca.alertPriority = 0;
			state = "Alpaca battery is nominal";
		}
		return state;
	}
	
	/**
	 * @author Sylvia Allain, Jonathan Cole
	 * @param pitch, roll
	 * @return type of position
	 */
	public String analyzeOrientation(Alpaca alpaca){
		
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
	 * @author Sylvia Allain, Jonathan Cole
	 * @param signal quality
	 * @return string signal quality
	 */
	public String analyzeSignalQuality(Alpaca alpaca) {
		
		int signalQuality = alpaca.hardware.getSignalQuality();
		
		String state = "";
		int signalCeiling = 5;
		
		if (signalQuality < 0){
			state = "Error: Negative signal value";
		}
		else if (signalQuality > signalCeiling){
			state = "Error: High signal value";
		}
		else{
			state = Integer.toString(signalQuality);
		}
		
		//Signal is too low
		if(signalQuality < pacaWorld.returnLowSignalThreshold()){
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.LowSignal);
			alpaca.alertPriority = 1;
		}
		//Signal is fine
		else{
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.LowSignal);
			alpaca.alertPriority = 0;
		}
		
		return state;
	}
	
	/**
	 * @author Sylvia Allain
	 * @param temperature
	 * @return health condition
	 */
	public String analyzeTemperature(Alpaca alpaca) {
		//normal alpaca temperature is 100.5 to 102.5 F
		float alpacaHighTemp = 102.5f;
		float alpacaLowTemp = 100.5f;
		
		float temperature = alpaca.hardware.getTemperature();
		
		String state = "";
		if (temperature < pacaWorld.returnTemperatureFloor())
			state = "Error: Low temperature value";
		else if (temperature > pacaWorld.returnTemperatureCeiling())
			state = "Error: High temperature value";
		else if (temperature > alpacaHighTemp) {
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.TemperatureHigh);
			state = "Alpaca is sick";
		}
		else if (temperature < alpacaLowTemp) {
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.TemperatureLow);
			state = "Alpaca is sick";
		}
		else {
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.TemperatureHigh);
			pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.TemperatureLow);
			state = Float.toString(temperature);
		}
		
		
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
	 * TODO: add test
	 * @author Jonathan Cole
	 * @param alpaca
	 */
	public void analyzeHeartRate(Alpaca alpaca){
		float heartRate = alpaca.hardware.getHeartRate();
		//High heart rate
		boolean isHigh = (heartRate > pacaWorld.returnHeartRateCeiling());
		//Low heart rate
		boolean isLow = (heartRate < pacaWorld.returnHeartRateFloor());
		//Dead
		boolean dead = (heartRate == 0);
		
		if(dead){
			pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.Dead);
			alpaca.alertPriority = 3;
		}
		else{
			if(isLow){
				pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.HeartRateLow);
				alpaca.alertPriority = 2;
			}
			else{
				pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.HeartRateLow);
				alpaca.alertPriority = 0;
			}
			if(isHigh){
				pacaWorld.CreateAlert(alpaca, PacaAlert.EventType.HeartRateHigh);
				alpaca.alertPriority = 2;
			}
			else{
				pacaWorld.RemoveAlert(alpaca, PacaAlert.EventType.HeartRateHigh);
				alpaca.alertPriority = 0;
			}
		}
		
	}
	
}