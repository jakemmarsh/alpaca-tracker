package TestDriversAndFactories;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import static org.junit.Assert.*;
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
	PacaTracaTestHardware hardware;
	
	@Before
	public void setUp() throws Exception {
		pacaWorld = new PacaWorld();
		analyzer = new PacaAnalyzer(pacaWorld);
		factory = new PacaTracaTestHardwareFactory();
		hardware = factory.createPacaTraca("Fred");
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
	private PacaTracaTestHardware NormalCaseAlpaca() {

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

		return hardware;
		
	}
	
	//Normal Case
	@Test
	public void testCase1() {
		
		hardware = NormalCaseAlpaca();
		
		assertEquals("", analyzer.analyzeLocation(hardware.getLatitudeDecimalDegrees(),hardware.getLongitudeDecimalDegrees()));
		assertEquals("Alpaca running", analyzer.analyzeSpeed(hardware.getSpeed()));
		assertEquals(Float.toString(hardware.getCourse()), analyzer.analyzeCourse(hardware.getCourse()));
	}
	
	//High Longitude
	@Test
	public void testCase2() {
		
		hardware = NormalCaseAlpaca();
		hardware.setLongitude(200f);
		
		assertEquals("", analyzer.analyzeLocation(hardware.getLatitudeDecimalDegrees(),hardware.getLongitudeDecimalDegrees()));
		
	}
	
	//Low Longitude
	@Test
	public void testCase3() {
		
		hardware = NormalCaseAlpaca();
		hardware.setLongitude(-200f);
		
		assertEquals("", analyzer.analyzeLocation(hardware.getLatitudeDecimalDegrees(),hardware.getLongitudeDecimalDegrees()));
		
	}
	
	//High Latitude
	@Test
	public void testCase4() {
		
		hardware = NormalCaseAlpaca();
		hardware.setLatitude(100f);
		
		assertEquals("", analyzer.analyzeLocation(hardware.getLatitudeDecimalDegrees(),hardware.getLongitudeDecimalDegrees()));
		
	}
	
	//Low Latitude
	@Test
	public void testCase5() {
		
		hardware = NormalCaseAlpaca();
		hardware.setLatitude(-100f);
		
		assertEquals("", analyzer.analyzeLocation(hardware.getLatitudeDecimalDegrees(),hardware.getLongitudeDecimalDegrees()));
		
	}
	
	//High Speed
	@Test
	public void testCase6() {
		
		hardware = NormalCaseAlpaca();
		hardware.setSpeed(50f);
		
		assertEquals("Error: High speed value", analyzer.analyzeSpeed(hardware.getSpeed()));
		
	}
	
	//Low Speed
	@Test
	public void testCase7() {
		
		hardware = NormalCaseAlpaca();
		hardware.setSpeed(-5.0f);
		
		assertEquals("Error: Negative speed value", analyzer.analyzeSpeed(hardware.getSpeed()));
		
	}
	
	//High Course
	@Test
	public void testCase8() {
		
		hardware = NormalCaseAlpaca();
		hardware.setCourse(400f);
		
		assertEquals("Error: High course value", analyzer.analyzeCourse(hardware.getCourse()));
		
	}
	
	//Low Course
	@Test
	public void testCase9() {
		
		hardware = NormalCaseAlpaca();
		hardware.setCourse(-100f);
		
		assertEquals("Error: Negative course value", analyzer.analyzeCourse(hardware.getCourse()));
		
	}
	
	//Low NumSatelites
	@Test
	public void testCase10() {
		
		hardware = NormalCaseAlpaca();
		hardware.setNumSatellites(-5);
		
		assertEquals("Error: Negative satellite number", analyzer.analyzeNumSatellites(hardware.getNumSatellites()));
		
	}
	
	//High Heading
	@Test
	public void testCase11() {
		
		hardware = NormalCaseAlpaca();
		hardware.setHeading(400f);
		
		assertEquals("Error: High heading value", analyzer.analyzeHeading(hardware.getCompassHeading()));
		
	}
	
	//Low Heading
	@Test
	public void testCase12() {
		
		hardware = NormalCaseAlpaca();
		hardware.setHeading(-100f);
		
		assertEquals("Error: Negative heading value", analyzer.analyzeHeading(hardware.getCompassHeading()));
		
	}
	
	//High Pitch
	@Test
	public void testCase13() {
		
		hardware = NormalCaseAlpaca();
		hardware.setPitch(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(hardware.getPitch(), hardware.getRoll()));
		
	}
	
	//Low Pitch
	@Test
	public void testCase14() {
		
		hardware = NormalCaseAlpaca();
		hardware.setPitch(-20f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(hardware.getPitch(), hardware.getRoll()));
		
	}
	
	//High Roll
	@Test
	public void testCase15() {
		
		hardware = NormalCaseAlpaca();
		hardware.setRoll(200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(hardware.getPitch(), hardware.getRoll()));
		
	}
	
	//Low Roll
	@Test
	public void testCase16() {
		
		hardware = NormalCaseAlpaca();
		hardware.setRoll(-200f);
		
		assertEquals("Error: Invalid pitch and roll values", analyzer.analyzePosition(hardware.getPitch(), hardware.getRoll()));
		
	}
	
	//High Altitude
	@Test
	public void testCase17() {
		
		hardware = NormalCaseAlpaca();
		hardware.setAltitude(10000f);
		
		assertEquals("Error: High altitude value", analyzer.analyzeAltitude(hardware.getAltitude()));
		
	}
	
	//Low Altitude
	@Test
	public void testCase18() {
		
		hardware = NormalCaseAlpaca();
		hardware.setAltitude(-200f);
		
		assertEquals("Error: Negative altitude value", analyzer.analyzeAltitude(hardware.getAltitude()));
		
	}
	
	//High Signal Quality
	@Test
	public void testCase19() {
		
		hardware = NormalCaseAlpaca();
		hardware.setSignalQuality(7);
		
		assertEquals("Error: High signal value", analyzer.analyzeSignalQuality(hardware.getSignalQuality()));
		
	}
	
	//Low Signal Quality
	@Test
	public void testCase20() {
		
		hardware = NormalCaseAlpaca();
		hardware.setSignalQuality(-3);
		
		assertEquals("Error: Negative signal value", analyzer.analyzeSignalQuality(hardware.getSignalQuality()));
		
	}
	
	//High Temperature
	@Test
	public void testCase21() {
		
		hardware = NormalCaseAlpaca();
		hardware.setTemperature(5000f);
		
		assertEquals("Error: High temperature value", analyzer.analyzeTemperature(hardware.getTemperature()));
		
	}
	
	//Low Temperature
	@Test
	public void testCase22() {
		
		hardware = NormalCaseAlpaca();
		hardware.setTemperature(-300f);
		
		assertEquals("Error: Low temperature value", analyzer.analyzeTemperature(hardware.getTemperature()));
		
	}
	
}