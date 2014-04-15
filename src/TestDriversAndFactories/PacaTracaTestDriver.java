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

	/**
	 * @author Sylvia Allain
	 * @param ID
	 * @return new alpaca hardware
	 */
	public PacaTraca createAlpacaHardware( String ID ) {
		PacaTraca hardware = factory.createPacaTraca(ID);
		m_sensors.put(ID, hardware);
		sensorNames.add(ID);
		return hardware;
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