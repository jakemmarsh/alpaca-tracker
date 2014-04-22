package alpaca;
import java.awt.Polygon;
import java.util.*;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * @author Sylvia Allain, Jonathan Cole
 * 
 * Holds information about the environment the alpacas reside in.
 * High/Low tolerances for temperature, fence boundaries, etc.
 * 
 * Variables will be pulled from FireBase, and set by the 
 * User Preference interface.
 */
public class PacaWorld{
	
	public List<PacaAlert> alerts = new ArrayList<PacaAlert>();
	public ArrayList<float[]> farmCoordinates;
	
	private float longitudeFloor = -180f;
	private float longitudeCeiling = 180f;
	private int numSatellites = 100;
	private float altitudeFloor = 0;
	private float altitudeCeiling = 8900;
	private float temperatureFloor = -140f;
	private float temperatureCeiling = 4000f;
	//The maximum distance an alpaca can be from another before being considered isolated
	private float maxAlpacaGroupDistance = 30f;
	//The threshold for low battery alerts. A number from 0 - 100.
	private float lowBatteryWarningThreshold = 20.0f;
	private float heartRateFloor = 70;
	private float heartRateCeiling = 120;
	//The amount of signal at or below which an alert is thrown. A number from 1 - 5.
	private int lowSignalThreshold = 1;
	private HashMap alert = new HashMap();
	
	public PacaWorld() {
		farmCoordinates = new ArrayList<float[]>();
		
		String farmUrl = "https://crackling-fire-2064.firebaseio.com/farm/boundaries";
		Firebase farmRef = new Firebase(farmUrl);
		farmRef.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
		    	ArrayList coordinates = (ArrayList) snapshot.getValue();
		    	for(int i = 0; i < coordinates.size(); i++) {
		    		HashMap coordinatePair = (HashMap) coordinates.get(i);
		    		
		    		float [] tuple = new float [2];
		    		tuple [0] = Float.parseFloat (coordinatePair.get("lat").toString());
		    		tuple [1] = Float.parseFloat (coordinatePair.get("lng").toString());
		    		farmCoordinates.add(tuple);
		    	}
		    }

			@Override
			public void onCancelled(FirebaseError arg0) 
			{}
		});
	}
	
	public void defaultBoundaries() {
		ArrayList<float[]> farmCoordinates = new ArrayList<float[]>();
		
		float[] coordinate1 = new float[2];
		coordinate1[0] = 44.010f;
		coordinate1[1] = 68.010f;
		farmCoordinates.add(coordinate1);
		
		float[] coordinate2 = new float[2];
		coordinate2[0] = 44.010f;
		coordinate2[1] = 68.200f;
		farmCoordinates.add(coordinate2);
		
		float[] coordinate3 = new float[2];
		coordinate3[0] = 44.100f;
		coordinate3[1] = 68.100f;
		farmCoordinates.add(coordinate3);
		
		float[] coordinate4 = new float[2];
		coordinate4[0] = 44.200f;
		coordinate4[1] = 68.200f;
		farmCoordinates.add(coordinate4);
		
		float[] coordinate5 = new float[2];
		coordinate5[0] = 44.200f;
		coordinate5[1] = 68.010f;
		farmCoordinates.add(coordinate5);
		
		this.farmCoordinates = farmCoordinates;
	}
	
	/**
	 * Creates an event and passes it to both the list of events and FireBase.
	 * @param alp
	 * @param type
	 */
	public void CreateAlert(Alpaca alp, PacaAlert.EventType type){
		PacaAlert e = new PacaAlert(alp, type);
		//Only add the alert if the alpaca doesn't already have one raised.
		if(!alp.alerts.contains(e)){
			Firebase alertRef = new Firebase("https://crackling-fire-2064.firebaseio.com/alerts").push();
			
			// build hashmap of alert to store in Firebase
			alert.put("alpacaID", alp.getTrackerID());
			alert.put("read", e.readByUser);
			alert.put("message", e.ToString());
			alert.put("priority", e.priority.toString());
	
			alerts.add(e);
			alp.alerts.add(e);
			
			// save alert in Firebase
			alertRef.setValue(alert, new Firebase.CompletionListener() {		
				@Override
				public void onComplete(FirebaseError error, Firebase arg1) {
					if (error != null) {
			            System.err.println("[CreateAlert] Data could not be saved: " + error.getMessage());
			        } else {
			            System.out.println("[CreateAlert] Data saved successfully.");
			        }
				}
			});
		}
	}
	
	/**
	 * @author Jonathan Cole
	 * Removes an event from the specified alpaca's internal list.
	 * Call this when an analysis method is successful to tell the system
	 * that an alert no longer applies. This is not reflected in the frontend.
	 * @param alp
	 * @param type
	 */
	public void RemoveAlert(Alpaca alp, PacaAlert.EventType type){
		PacaAlert e = new PacaAlert(alp, type);
		alp.alerts.remove(e);
	}
	
	/**
	 * @author Sylvia Allain, Jonathan Cole
	 * getters / setters
	 */
	
	public void setLongitudeFloor(float floor) { this.longitudeFloor = floor; }
	
	public void setLongitudeCeiling(float ceiling) { this.longitudeCeiling = ceiling; }
	
	public void setNumSatellites(int numSatellites) { this.numSatellites = numSatellites; }
	
	public void setAltitudeFloor(float floor) { this.altitudeFloor = floor; }
	
	public void setAltitudeCeiling(float ceiling) { this.altitudeCeiling = ceiling; }
	
	public void setTemperatureFloor(float floor) { this.temperatureFloor = floor; }
	
	public void setTemperatureCeiling(float ceiling) { this.temperatureCeiling = ceiling; }
	
	public void setMaxAlpacaGroupDistance(float distance) { this.maxAlpacaGroupDistance = distance; }
	
	public void setLowBatteryWarningThreshold(float threshold) { this.lowBatteryWarningThreshold = threshold; }
	
	public void setLowSignalThreshold(int threshold) { this.lowSignalThreshold = threshold; }
	
	public void setHeartRateFloor(float floor) { this.heartRateFloor = floor; }
	
	public void setHeartRateCeiling(float ceiling) { this.heartRateCeiling = ceiling; }
	
	public float returnLongitudeFloor() { return longitudeFloor; }
	
	public float returnLongitudeCeiling() { return longitudeCeiling; }
	
	public int returnNumSatellites() { return numSatellites; }
	
	public float returnAltitudeFloor() { return altitudeFloor; }
	
	public float returnAltitudeCeiling() { return altitudeCeiling; }
	
	public float returnTemperatureFloor() { return temperatureFloor; }
	
	public float returnTemperatureCeiling() { return temperatureCeiling; }
	
	public float returnMaxAlpacaGroupDistance() { return maxAlpacaGroupDistance; }
	
	public float returnLowBatteryWarningThreshold() { return lowBatteryWarningThreshold; }
	
	public float returnHeartRateFloor(){ return heartRateFloor; }
	
	public float returnHeartRateCeiling(){ return heartRateCeiling; }
	
	public int returnLowSignalThreshold(){ return lowSignalThreshold; }
	
	public ArrayList<float[]> returnFarmCoordinates() { return farmCoordinates; };
}