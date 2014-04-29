package TestDriversAndFactories;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import static org.junit.Assert.*;
import alpaca.Alpaca;
import alpaca.PacaAnalyzer;
import alpaca.PacaWorld;

/**
 * @author Sylvia Allain
 * 
 * Tests all methods of the PacaAnalyzer
 */

public class PacaAnalyzerTest {
	
	PacaWorld pacaWorld;
	PacaAnalyzer analyzer;
	PacaTracaHardwareFactory factory;
	ArrayList<Alpaca> alpacaList;
	Alpaca fred;
	Alpaca bob;
	
	/**
	 * @author Sylvia Allain
	 * @throws Exception
	 * initializes test variables
	 */
	@Before
	public void setUp() throws Exception {
		alpacaList = new ArrayList<Alpaca>();
		pacaWorld = new PacaWorld();
		analyzer = new PacaAnalyzer(pacaWorld);
		factory = new PacaTracaHardwareFactory();
		fred = new Alpaca();
		fred.hardware.setIsRandom(false);
		bob = new Alpaca();
		bob.hardware.setIsRandom(false);
		
		alpacaList.add(fred);
		alpacaList.add(bob);
	}
	
	/**
	 * Test cases variables
	 * Normal Cases:
	 * -180 <= longitude <= 180
	 * -90 <= latitude <= 90
	 *  0 <= speed <= 20 mph
	 *  0 <= course <= 360
	 *  0 <= numSatellites
	 *  0 <= pitch <= 180
	 *  -90 <= roll <= 90
	 *  0 <= altitude <= 8900
	 *  0 <= signalQuality <= 5
	 *  -140 F <= temperature <= 4000 F
	 *
	 */
	
	/**
	 * @author Sylvia Allain
	 * @return alpaca
	 * Set Normal Case
	 */
	@Ignore
	private Alpaca NormalCaseAlpaca(Alpaca alpaca) {

		float longitude = 44.02f;
		float latitude = 68.02f;
		float speed = 10.3f;
		float course = 123.3f;
		int numSatellites = 3;
		float pitch = 93.1f;
		float roll = -22.0f;
		float altitude = 1023.3f;
		int signalQuality = 2;
		float temperature = 100.9f;
		int batteryLife = 90;
		float heartRate = 100f;
		boolean fix = true;
		
		alpaca.hardware.setLongitude(longitude);
		alpaca.hardware.setLatitude(latitude);
		alpaca.hardware.setSpeed(speed);
		alpaca.hardware.setCourse(course);
		alpaca.hardware.setNumSatellites(numSatellites);
		alpaca.hardware.setPitch(pitch);
		alpaca.hardware.setRoll(roll);
		alpaca.hardware.setAltitude(altitude);
		alpaca.hardware.setSignalQuality(signalQuality);
		alpaca.hardware.setTemperature(temperature);
		alpaca.hardware.setFix(fix);
		alpaca.hardware.setBatteryLife(batteryLife);
		alpaca.hardware.setHeartRate(heartRate);

		return alpaca;
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Normal Case
	 */
	@Test
	public void testCase1() {
		
		fred = NormalCaseAlpaca(fred);
		bob = NormalCaseAlpaca(bob);
		bob.hardware.setLatitude(68.02001f);

		assertEquals("Alpaca running", analyzer.analyzeSpeed(fred));
		assertEquals(Float.toString(fred.hardware.getCourse()), analyzer.analyzeCourse(fred));
		assertEquals(Integer.toString(fred.hardware.getNumSatellites()), analyzer.analyzeNumSatellites(fred));
		assertEquals("Alpaca is standing", analyzer.analyzeOrientation(fred));
		assertEquals(Float.toString(fred.hardware.getAltitude()), analyzer.analyzeAltitude(fred));
		assertEquals(Integer.toString(fred.hardware.getSignalQuality()), analyzer.analyzeSignalQuality(fred));
		assertEquals(Float.toString(fred.hardware.getTemperature()), analyzer.analyzeTemperature(fred));
		assertEquals("Alpaca battery is nominal", analyzer.analyzeBattery(fred));
		assertEquals(Float.toString(fred.hardware.getHeartRate()),analyzer.analyzeHeartRate(fred));
		assertEquals("Alpaca is not isolated", analyzer.analyzeIsolation(fred,alpacaList));
		
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Isolated
	 */
	@Test
	public void testCase2() {
		
		fred = NormalCaseAlpaca(fred);
		bob = NormalCaseAlpaca(bob);
		bob.hardware.setLatitude(68.08f);

		assertEquals("Alpaca is isolated", analyzer.analyzeIsolation(fred,alpacaList));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Speed
	 */
	@Test
	public void testCase3() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setSpeed(50f);
		
		assertEquals("Error: High speed value", analyzer.analyzeSpeed(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Speed
	 */
	@Test
	public void testCase4() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setSpeed(-5.0f);
		
		assertEquals("Error: Negative speed value", analyzer.analyzeSpeed(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Course
	 */
	@Test
	public void testCase5() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setCourse(400f);
		assertEquals("Error: High course value", analyzer.analyzeCourse(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Course
	 */
	@Test
	public void testCase6() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setCourse(-100f);
		
		assertEquals("Error: Negative course value", analyzer.analyzeCourse(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low NumSatelites
	 */
	@Test
	public void testCase7() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setNumSatellites(-5);
		
		assertEquals("Error: Negative satellite number", analyzer.analyzeNumSatellites(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Pitch
	 */
	@Test
	public void testCase8() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setPitch(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzeOrientation(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Pitch
	 */
	@Test
	public void testCase9() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setPitch(-20f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzeOrientation(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Roll
	 */
	@Test
	public void testCase10() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setRoll(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzeOrientation(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Roll
	 */
	@Test
	public void testCase11() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setRoll(-200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzeOrientation(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Altitude
	 */
	@Test
	public void testCase12() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setAltitude(10000f);
		
		assertEquals("Error: High altitude value", analyzer.analyzeAltitude(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Altitude
	 */
	@Test
	public void testCase13() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setAltitude(-200f);
		
		assertEquals("Error: Negative altitude value", analyzer.analyzeAltitude(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * High Signal Quality
	 */
	@Test
	public void testCase14() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setSignalQuality(7);
		
		assertEquals("Error: High signal value", analyzer.analyzeSignalQuality(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Signal Quality
	 */
	@Test
	public void testCase15() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setSignalQuality(-3);
		
		assertEquals("Error: Negative signal value", analyzer.analyzeSignalQuality(fred));
	}
	
	/**
	 * @author Sylvia Allain
	 * High Temperature
	 */
	@Test
	public void testCase16() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setTemperature(5000f);
		
		assertEquals("Error: High temperature value", analyzer.analyzeTemperature(fred));
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Temperature
	 */
	@Test
	public void testCase17() {
		
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setTemperature(-300f);
		
		assertEquals("Error: Low temperature value", analyzer.analyzeTemperature(fred));
		
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Battery
	 */
	@Test
	public void testCase18() {
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setBatteryLife(5);
		
		assertEquals("Alpaca battery is low", analyzer.analyzeBattery(fred));
	}
	
	/**
	 * @author Sylvia Allain
	 * Dead Alpaca
	 */
	@Test
	public void testCase19() {
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setHeartRate(0f);
		
		assertEquals("Alpaca is dead", analyzer.analyzeHeartRate(fred));
	}
	
	/**
	 * @author Sylvia Allain
	 * Low Heart Rate
	 */
	@Test
	public void testCase20() {
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setHeartRate(60f);
		
		assertEquals("Low heart rate", analyzer.analyzeHeartRate(fred));
	}
	
	/**
	 * @author Sylvia Allain
	 * High Heart Rate
	 */
	@Test
	public void testCase21() {
		fred = NormalCaseAlpaca(fred);
		fred.hardware.setHeartRate(130f);
		
		assertEquals("High heart rate", analyzer.analyzeHeartRate(fred));
	}
	
}