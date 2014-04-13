package TestDriversAndFactories;

public class PacaTracaTestHardwareFactory implements PacaTracaFactory {

	@Override
	public PacaTracaTestHardware createPacaTraca(String sensorID) {
		// TODO Auto-generated method stub
		return new PacaTracaTestHardware( sensorID );
	}
}