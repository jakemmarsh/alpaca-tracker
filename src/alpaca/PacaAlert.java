package alpaca;

/**
 * @author Jonathan Cole
 *
 */
public class PacaAlert {
	public boolean readByUser = false;
	public Alpaca alpaca;
	public static enum EventPriority{ Normal, Warning, Critical };
	public EventPriority priority = EventPriority.Normal;
	public static enum EventType{
		TemperatureLow,
		TemperatureHigh,
		Dead,
		Sick,
		Isolated,
		OutOfBounds
	};
	public EventType type;
	
	public PacaAlert(Alpaca alp, EventType t){
		alpaca = alp;
		type = t;
	}

	public PacaAlert(Alpaca alp, EventPriority p, EventType t){
		alpaca = alp;
		priority = p;
		type = t;
	}
		
	/** Returns a string in the form
	 *  "Alpaca [name] [information]"
	 */
	public String ToString(){
		StringBuilder s = new StringBuilder();
		s.append("Alpaca");
		s.append(" ");
		//s.append(alpaca.name);
		s.append(" ");
		s.append(GetInfoPostfix());
		return s.toString();
	}
	
	/**
	 * Returns a string representing the English form of the current EventType.
	 */
	public String GetInfoPostfix(){
		String outStr = "";
		switch(type){
			case TemperatureLow:
				outStr = "is below the lower temperature threshold";
				break;
			case TemperatureHigh:
				outStr = "is above the upper temperature threshold";
				break;
			case Dead:
				outStr = "has died :(";
				break;
			case Sick:
				outStr = "is sick :(";
				break;
			case Isolated:
				outStr = "is lonely :(";
				break;
			case OutOfBounds:
				outStr = "is out of bounds";
				break;
		}
		return outStr;
	}
}
