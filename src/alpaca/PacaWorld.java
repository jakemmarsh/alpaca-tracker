package alpaca;
import java.util.*;

/**
 * @author Sylvia Allain
 * 
 * Holds information about the environment the alpacas reside in.
 * High/Low tolerances for temperature, fence boundaries, etc.
 * 
 * Variables will be pulled from FireBase, and set by the 
 * User Preference interface.
 */
public class PacaWorld {
	
	private float longitudeFloor = -180f;
	private float longitudeCeiling = 180f;
	private int numSatellites = 100;
	private float altitudeFloor = 0;
	private float altitudeCeiling = 8900;
	private float temperatureFloor = -140f;
	private float temperatureCeiling = 4000f;

	public PacaWorld() {
		
	}
	
	public void setLongitudeFloor(float floor) { this.longitudeFloor = floor; }
	
	public void setLongitudeCeiling(float ceiling) { this.longitudeCeiling = ceiling; }
	
	public void setNumSatellites(int numSatellites) { this.numSatellites = numSatellites; }
	
	public void setAltitudeFloor(float floor) { this.altitudeFloor = floor; }
	
	public void setAltitudeCeiling(float ceiling) { this.altitudeCeiling = ceiling; }
	
	public void setTemperatureFloor(float floor) { this.temperatureFloor = floor; }
	
	public void setTemperatureCeiling(float ceiling) { this.temperatureCeiling = ceiling; }
	
	public float returnLongitudeFloor() { return longitudeFloor; }
	
	public float returnLongitudeCeiling() { return longitudeCeiling; }
	
	public int returnNumSatellites() { return numSatellites; }
	
	public float returnAltitudeFloor() { return altitudeFloor; }
	
	public float returnAltitudeCeiling() { return altitudeCeiling; }
	
	public float returnTemperatureFloor() { return temperatureFloor; }
	
	public float returnTemperatureCeiling() { return temperatureCeiling; }
	
}