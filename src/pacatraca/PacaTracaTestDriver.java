package pacatraca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Bryan Wells
 * Very simple class to instantiate a few sensors, store them in a map
 * and then get them from the map and print out their values
 * 
 */
public class PacaTracaTestDriver {

	private HashMap< String, PacaTraca > m_sensors = new HashMap< String, PacaTraca >( );
	private PacaTracaFactory factory = new PacaTracaHardwareFactory();
	public List<String> sensorNames = new ArrayList<String>();

	/**
	 * the main module
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PacaTracaTestDriver testCase = new PacaTracaTestDriver( );
		testCase.runTests();
	}
	
	/**
	 * @author Bryan Wells
	 * Create a factory (a hardware factory in this case)
	 * create some test sensors using the factory
	 * verify the created sensors
	 */
	public void runTests( ) {
		this.createTestSensor(factory, "Alpaca 1");
		this.createTestSensor(factory, "Alpaca 2");
		this.verifySensors(  );
	}
		
//	/**
//	 * @author Bryan Wells
//	 * ask the factory to create our sensors (any kind will be fine)
//	 * and then put these sensors into a hash map for safe keeping
//	 */
//	public void createTestSensors( PacaTracaFactory factory ) {
//		m_sensors.put( m_sensor1ID, factory.createPacaTraca( m_sensor1ID ) );
//		m_sensors.put( m_sensor2ID, factory.createPacaTraca( m_sensor2ID ) );
//	}
	
	/**
	 * Creates a new PacaTracaHardware and stores it in the factory.
	 * The ID is stored into sensorNames.
	 */
	public void createTestSensor( PacaTracaFactory factory, String ID){
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
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
		//System.out.println("Sensor 1: " + m_sensors.get( m_sensor1ID ).toString( ) );
		//System.out.println("Sensor 2: " + m_sensors.get( m_sensor2ID ).toString( ) );
	}
}
