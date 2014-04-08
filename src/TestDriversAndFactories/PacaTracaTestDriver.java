package TestDriversAndFactories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import PacaTraca.PacaTraca;

/**
 * @author Bryan Wells
 * Very simple class to instantiate a few sensors, store them in a map
 * and then get them from the map and print out their values
 * 
 */
public class PacaTracaTestDriver {

	private HashMap< String, PacaTracaTestHardware > m_sensors = new HashMap< String, PacaTracaTestHardware >( );
	private PacaTracaTestHardwareFactory factory = new PacaTracaTestHardwareFactory();
	public List<String> sensorNames = new ArrayList<String>();
	Random rand = new Random();

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
		//this.createFaultyTestSensors(factory);
		this.createDummyTestSensors(factory, 5);
		this.randomUpdateDummyTestSensors();
		this.verifySensors(  );
		this.randomUpdateDummyTestSensors();
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
	
	public void createDummyTestSensors( PacaTracaTestHardwareFactory factory, int numAlpacas ) {
		
		for (int i = 0; i < numAlpacas; i++) {
			AddRandomAlpaca(factory, "Alpaca " + i);
		}
		
	}
	
	public void randomUpdateDummyTestSensors() {
		for(String s : sensorNames){
			float longitude = GetRandomLongitude(s);
			float latitude = GetRandomLatitude(s);
			float speed = GetRandomSpeed(s);
			float course = GetRandomCourse(s);
			int numSatellites = GetRandomNumSatellites(s);
			float heading = GetRandomHeading(s);
			float pitch = GetRandomPitch(s);
			float roll = GetRandomRoll(s);
			float altitude = GetRandomAltitude(s);
			int signalQuality = GetRandomSignalQuality(s);
			float temperature = GetRandomTemperature(s);
			boolean fix = GetRandomFix(s);
			
			updateData(s, longitude, latitude, speed, course, numSatellites, heading, pitch, roll, altitude, signalQuality, temperature, fix);
		}
	}
	
	/**
	 * @param ID
	 * @return a random longitude coordinate between -68 and -69, roughly in Orono
	 */
	private float GetRandomLongitude(String ID) {
		int floor = 68;
		int ceiling = 69;
		
		return rand.nextFloat() * (ceiling - floor) + floor;
	}
	
	/**
	 * @param ID
	 * @return a random latitude coordinate between 44 and 45, roughly in Orono
	 */
	private float GetRandomLatitude(String ID) {
		int floor = 44;
		int ceiling = 45;
		
		return rand.nextFloat() * (ceiling - floor) + floor;
	}
	
	private float GetRandomSpeed(String ID) {
		return rand.nextInt(15) + rand.nextFloat();
	}
	
	private float GetRandomCourse(String ID) {
		return rand.nextInt(359) + rand.nextFloat();
	}
	
	private int GetRandomNumSatellites(String ID) {
		return rand.nextInt(5);
	}
	
	private float GetRandomHeading(String ID) {
		return rand.nextInt(359) + rand.nextFloat();
	}
	
	private float GetRandomPitch(String ID) {
		return rand.nextInt(179) + rand.nextFloat();
	}
	
	private float GetRandomRoll(String ID) {
		return rand.nextInt(179) + rand.nextFloat() - 90;
	}
	
	private float GetRandomAltitude(String ID) {
		return rand.nextInt(1000) + rand.nextFloat();
	}
	
	private int GetRandomSignalQuality(String ID) {
		return rand.nextInt(5);
	}
	
	private float GetRandomTemperature(String ID) {
		return rand.nextInt(105) + rand.nextFloat();
	}
	
	private boolean GetRandomFix(String ID) {
		return true;
	}
	
	/**
	 * Creates new PacaTracaTestHardware for test cases and stores it in the factory.
	 * The ID is stored into sensorNames.
	 */
	public void createFaultyTestSensors( PacaTracaTestHardwareFactory factory ){
		TestCase1(factory, "Alpaca 1");
		TestCase2(factory, "Alpaca 2");
		TestCase3(factory, "Alpaca 3");
		TestCase4(factory, "Alpaca 4");
		TestCase5(factory, "Alpaca 5");
		TestCase6(factory, "Alpaca 6");
		TestCase7(factory, "Alpaca 7");
		TestCase8(factory, "Alpaca 8");
		TestCase9(factory, "Alpaca 9");
		TestCase10(factory, "Alpaca 10");
		TestCase11(factory, "Alpaca 11");
		TestCase12(factory, "Alpaca 12");
		TestCase13(factory, "Alpaca 13");
		TestCase14(factory, "Alpaca 14");
		TestCase15(factory, "Alpaca 15");
		TestCase16(factory, "Alpaca 16");
		TestCase17(factory, "Alpaca 17");
		TestCase18(factory, "Alpaca 18");
		TestCase19(factory, "Alpaca 19");
		TestCase20(factory, "Alpaca 20");
		TestCase21(factory, "Alpaca 21");
		TestCase22(factory, "Alpaca 22");
	}
	
	/**
	 * Test cases variables
	 * Normal Cases:
	 * -180 <= longitude <= 180
	 * -90 <= latitude <= 90
	 *  0 <= speed <= 20 mph
	 *  0 <= course <= 360
	 *  0 <= numSatellites
	 *  0 <= heading <= 360
	 *  0 <= pitch <= 180
	 *  -90 <= roll <= 90
	 *  0 <= altitude <= 8900
	 *  0 <= signalQuality <= 5
	 *  -140 F <= temperature <= 4000 F
	 */
	
	//Random Alpaca Data
	public void AddRandomAlpaca( PacaTracaTestHardwareFactory factory, String ID ) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
	}
	
	//Normal Case
	private void TestCase1(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Longitude
	private void TestCase2(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 200.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Longitude
	private void TestCase3(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, -200.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Latitude
	private void TestCase4(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 100.0f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Latitude
	private void TestCase5(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, -100.0f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Speed
	private void TestCase6(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 50.0f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Speed
	private void TestCase7(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, -5.0f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Course
	private void TestCase8(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 400.0f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Course
	private void TestCase9(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, -100.0f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low NumSatelites
	private void TestCase10(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, -5, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Heading
	private void TestCase11(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 400.0f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Heading
	private void TestCase12(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, -100.0f, 93.1f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Pitch
	private void TestCase13(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 200.0f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Pitch
	private void TestCase14(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, -20.0f, -22.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Roll
	private void TestCase15(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, 200.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//Low Roll
	private void TestCase16(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -200.0f, 1023.3f, 2, 100.3f, true);
	}
	
	//High Altitude
	private void TestCase17(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 10000.0f, 2, 100.3f, true);
	}
	
	//Low Altitude
	private void TestCase18(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, -200.0f, 2, 100.3f, true);
	}
	
	//High Signal Quality
	private void TestCase19(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 7, 100.3f, true);
	}
	
	//Low Signal Quality
	private void TestCase20(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, -3, 100.3f, true);
	}
	
	//High Temperature
	private void TestCase21(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, 5000.0f, true);
	}
	
	//Low Temperature
	private void TestCase22(PacaTracaTestHardwareFactory factory, String ID) {
		m_sensors.put(ID, factory.createPacaTraca(ID));
		sensorNames.add(ID);
		updateData(ID, 108.0f, 80.4f, 10.3f, 123.3f, 3, 32.8f, 93.1f, -22.0f, 1023.3f, 2, -300.0f, true);
	}
	
	private void updateData( String ID, float longitude, float latitude, float speed, float course, int numSatellites, float heading, float pitch, float roll, float altitude, int signalQuality, float temperature, boolean fix ) {
		PacaTracaTestHardware hardware = m_sensors.get(ID);
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