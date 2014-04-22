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
public class PacaWorld {
	
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
	private HashMap alert = new HashMap();
	
	public PacaWorld() {
		farmCoordinates = new ArrayList<float[]>();
		
		//DEFAULT BOUNDARIES
		defaultBoundaries(farmCoordinates);
		
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
	
	public void defaultBoundaries(ArrayList<float[]> farmCoordinates) {
		float[] coordinate1 = new float[2];
		coordinate1[0] = 44.010f;
		coordinate1[1] = 68.010f;
		
		float[] coordinate2 = new float[2];
		coordinate2[0] = 44.010f;
		coordinate2[1] = 68.200f;
		
		float[] coordinate3 = new float[2];
		coordinate3[0] = 44.100f;
		coordinate3[1] = 68.100f;
		
		float[] coordinate4 = new float[2];
		coordinate4[0] = 44.200f;
		coordinate4[1] = 68.200f;
		
		float[] coordinate5 = new float[2];
		coordinate5[0] = 44.200f;
		coordinate5[1] = 68.010f;
	}
	
	/**
	 * Creates an event and passes it to both the list of events and FireBase.
	 * @param alp
	 * @param type
	 */
	public void CreateAlert(Alpaca alp, PacaAlert.EventType type){
		Firebase alertRef = new Firebase("https://crackling-fire-2064.firebaseio.com/alerts").push();
		PacaAlert e = new PacaAlert(alp, type);
		
		// build hashmap of alert to store in Firebase
		alert.put("alpacaID", alp.getTrackerID());
		alert.put("read", e.readByUser);
		alert.put("message", e.ToString());
		alert.put("priority", e.priority.toString());

		alerts.add(e);
		
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
	
	public float returnLongitudeFloor() { return longitudeFloor; }
	
	public float returnLongitudeCeiling() { return longitudeCeiling; }
	
	public int returnNumSatellites() { return numSatellites; }
	
	public float returnAltitudeFloor() { return altitudeFloor; }
	
	public float returnAltitudeCeiling() { return altitudeCeiling; }
	
	public float returnTemperatureFloor() { return temperatureFloor; }
	
	public float returnTemperatureCeiling() { return temperatureCeiling; }
	
	public float returnMaxAlpacaGroupDistance() { return maxAlpacaGroupDistance; }
	
	public float returnLowBatteryWarningThreshold() { return lowBatteryWarningThreshold; }
	
	public ArrayList<float[]> returnFarmCoordinates() { return farmCoordinates; };
}