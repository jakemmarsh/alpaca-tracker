package TestDriversAndFactories;

import PacaTraca.PacaTraca;
import PacaTraca.PacaTracaHardware;

public class PacaTracaHardwareFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		// TODO Auto-generated method stub
		return new PacaTracaHardware( sensorID );
	}
}
