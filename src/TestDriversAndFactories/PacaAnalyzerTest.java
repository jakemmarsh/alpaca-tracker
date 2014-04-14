package TestDriversAndFactories;

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
	PacaTracaTestHardwareFactory factory;
	Alpaca fred;
	
	@Before
	public void setUp() throws Exception {
		pacaWorld = new PacaWorld();
		analyzer = new PacaAnalyzer(pacaWorld);
		factory = new PacaTracaTestHardwareFactory();
		fred = new Alpaca();
		fred.hardware.setIsRandom(false);
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
	 *
	 */
	
	//Set Normal Case
	@Ignore
	private Alpaca NormalCaseAlpaca() {

		float longitude = 108.0f;
		float latitude = 80.4f;
		float speed = 10.3f;
		float course = 123.3f;
		int numSatellites = 3;
		float heading = 32.8f;
		float pitch = 93.1f;
		float roll = -22.0f;
		float altitude = 1023.3f;
		int signalQuality = 2;
		float temperature = 100.3f;
		boolean fix = true;
		
		fred.hardware.setLongitude(longitude);
		fred.hardware.setLatitude(latitude);
		fred.hardware.setSpeed(speed);
		fred.hardware.setCourse(course);
		fred.hardware.setNumSatellites(numSatellites);
		fred.hardware.setHeading(heading);
		fred.hardware.setPitch(pitch);
		fred.hardware.setRoll(roll);
		fred.hardware.setAltitude(altitude);
		fred.hardware.setSignalQuality(signalQuality);
		fred.hardware.setTemperature(temperature);
		fred.hardware.setFix(fix);

		return fred;
		
	}
	
	//Normal Case
	@Test
	public void testCase1() {
		
		fred = NormalCaseAlpaca();
		
		assertEquals("", analyzer.analyzeLocationBounds(fred));
		assertEquals("Alpaca running", analyzer.analyzeSpeed(fred));
		assertEquals(Float.toString(fred.hardware.getCourse()), analyzer.analyzeCourse(fred));
	}
	
	//High Longitude
	@Test
	public void testCase2() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setLongitude(200f);
		
		assertEquals("", analyzer.analyzeLocationBounds(fred));
		
	}
	
	//Low Longitude
	@Test
	public void testCase3() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setLongitude(-200f);
		
		assertEquals("", analyzer.analyzeLocationBounds(fred));
		
	}
	
	//High Latitude
	@Test
	public void testCase4() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setLatitude(100f);
		
		assertEquals("", analyzer.analyzeLocationBounds(fred));
		
	}
	
	//Low Latitude
	@Test
	public void testCase5() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setLatitude(-100f);
		
		assertEquals("", analyzer.analyzeLocationBounds(fred));
		
	}
	
	//High Speed
	@Test
	public void testCase6() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setSpeed(50f);
		
		assertEquals("Error: High speed value", analyzer.analyzeSpeed(fred));
		
	}
	
	//Low Speed
	@Test
	public void testCase7() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setSpeed(-5.0f);
		
		assertEquals("Error: Negative speed value", analyzer.analyzeSpeed(fred));
		
	}
	
	//High Course
	@Test
	public void testCase8() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setCourse(400f);
		assertEquals("Error: High course value", analyzer.analyzeCourse(fred));
		
	}
	
	//Low Course
	@Test
	public void testCase9() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setCourse(-100f);
		
		assertEquals("Error: Negative course value", analyzer.analyzeCourse(fred));
		
	}
	
	//Low NumSatelites
	@Test
	public void testCase10() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setNumSatellites(-5);
		
		assertEquals("Error: Negative satellite number", analyzer.analyzeNumSatellites(fred));
		
	}
	
	//High Heading
	@Test
	public void testCase11() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setHeading(400f);
		
		assertEquals("Error: High heading value", analyzer.analyzeHeading(fred));
		
	}
	
	//Low Heading
	@Test
	public void testCase12() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setHeading(-100f);
		
		assertEquals("Error: Negative heading value", analyzer.analyzeHeading(fred));
		
	}
	
	//High Pitch
	@Test
	public void testCase13() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setPitch(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(fred));
		
	}
	
	//Low Pitch
	@Test
	public void testCase14() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setPitch(-20f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(fred));
		
	}
	
	//High Roll
	@Test
	public void testCase15() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setRoll(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(fred));
		
	}
	
	//Low Roll
	@Test
	public void testCase16() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setRoll(-200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(fred));
		
	}
	
	//High Altitude
	@Test
	public void testCase17() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setAltitude(10000f);
		
		assertEquals("Error: High altitude value", analyzer.analyzeAltitude(fred));
		
	}
	
	//Low Altitude
	@Test
	public void testCase18() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setAltitude(-200f);
		
		assertEquals("Error: Negative altitude value", analyzer.analyzeAltitude(fred));
		
	}
	
	//High Signal Quality
	@Test
	public void testCase19() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setSignalQuality(7);
		
		assertEquals("Error: High signal value", analyzer.analyzeSignalQuality(fred));
		
	}
	
	//Low Signal Quality
	@Test
	public void testCase20() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setSignalQuality(-3);
		
		assertEquals("Error: Negative signal value", analyzer.analyzeSignalQuality(fred));
		
	}
	
	//High Temperature
	@Test
	public void testCase21() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setTemperature(5000f);
		
		assertEquals("Error: High temperature value", analyzer.analyzeTemperature(fred));
		
	}
	
	//Low Temperature
	@Test
	public void testCase22() {
		
		fred = NormalCaseAlpaca();
		fred.hardware.setTemperature(-300f);
		
		assertEquals("Error: Low temperature value", analyzer.analyzeTemperature(fred));
		
	}
	
}