package TestDriversAndFactories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import PacaTraca.PacaTraca;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Sylvia Allain
 * Class which creates dummy alpacas, stores them in a HashMap,
 * updates their data, and runs tests on methods of the program
 */

public class PacaTracaTestDriver {

	private HashMap< String, PacaTraca > m_sensors = new HashMap< String, PacaTraca >( );
	private PacaTracaHardwareFactory factory = new PacaTracaHardwareFactory();
	public List<String> sensorNames = new ArrayList<String>();
	Random rand = new Random();

	//Random Alpaca Data
	public PacaTraca createAlpacaHardware( String ID ) {
		PacaTraca hardware = factory.createPacaTraca(ID);
		m_sensors.put(ID, hardware);
		sensorNames.add(ID);
		return hardware;
	}
	
	public void randomUpdateDummyTestSensors() {
		for(String s : sensorNames){
			float longitude = getRandomLongitude(s);
			float latitude = getRandomLatitude(s);
			float speed = getRandomSpeed(s);
			float course = getRandomCourse(s);
			int numSatellites = getRandomNumSatellites(s);
			float heading = getRandomHeading(s);
			float pitch = getRandomPitch(s);
			float roll = getRandomRoll(s);
			float altitude = getRandomAltitude(s);
			int signalQuality = getRandomSignalQuality(s);
			float temperature = getRandomTemperature(s);
			boolean fix = getRandomFix(s);
			
			PacaTraca hardware = m_sensors.get(s);

			hardware.setLongitude(longitude);
			hardware.setLatitude(latitude);
			hardware.setSpeed(speed);
			hardware.setCourse(course);
			hardware.setNumSatellites(numSatellites);
			hardware.setHeading(heading);
			hardware.setPitch(pitch);
			hardware.setRoll(roll);
			hardware.setAltitude(altitude);
			hardware.setSignalQuality(signalQuality);
			hardware.setTemperature(temperature);
			hardware.setFix(fix);
		}
	}
	
	/**
	 * @param ID
	 * @return a random longitude coordinate between -68 and -69, roughly in Orono
	 */
	private float getRandomLongitude(String ID) {
		int floor = 68;
		int ceiling = 69;
		
		return rand.nextFloat() * (ceiling - floor) + floor;
	}
	
	/**
	 * @param ID
	 * @return a random latitude coordinate between 44 and 45, roughly in Orono
	 */
	private float getRandomLatitude(String ID) {
		int floor = 44;
		int ceiling = 45;
		
		return rand.nextFloat() * (ceiling - floor) + floor;
	}
	
	private float getRandomSpeed(String ID) {
		return rand.nextFloat() * 15;
	}
	
	private float getRandomCourse(String ID) {
		return rand.nextFloat() * 360;
	}
	
	private int getRandomNumSatellites(String ID) {
		return rand.nextInt(5);
	}
	
	private float getRandomHeading(String ID) {
		return rand.nextFloat() * 360;
	}
	
	private float getRandomPitch(String ID) {
		return rand.nextFloat() * 180;
	}
	
	private float getRandomRoll(String ID) {
		return rand.nextFloat() * 180 - 90;
	}
	
	private float getRandomAltitude(String ID) {
		return rand.nextFloat() * 1000;
	}
	
	private int getRandomSignalQuality(String ID) {
		return rand.nextInt(5);
	}
	
	private float getRandomTemperature(String ID) {
		return rand.nextFloat() * 105;
	}
	
	private boolean getRandomFix(String ID) {
		return true;
	}
	
	/**
	 * @author Bryan Wells
	 * using the hash map holding our sensors, get each sensor (any kind will do)
	 * and ask the sensor to give us its values as a string using the toString method
	 * print these string sensor values out and return
	 */
	public void verifySensors( ) {
		int index = 0;
		for(String s : sensorNames){
			System.out.println("Sensor " + Integer.toString(index));
			System.out.println(m_sensors.get(s).toString());
			index++;
		}
	}
}