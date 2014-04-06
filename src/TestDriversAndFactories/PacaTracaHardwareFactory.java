package TestDriversAndFactories;

import PacaTraca.PacaTraca;
import PacaTraca.PacaTracaImpl;

public class PacaTracaHardwareFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		// TODO Auto-generated method stub
		return new PacaTracaImpl( sensorID );
	}
}
