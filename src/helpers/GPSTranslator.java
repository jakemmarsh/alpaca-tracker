package helpers;

/**
 * @author Jonathan Cole
 * Contains methods for calculating distances between two
 * sets of coordinates, assuming various projections across
 * the Earth.
 * Reference:
 * http://www.movable-type.co.uk/scripts/latlong.html
 * http://andrew.hedges.name/experiments/haversine/
 */
public class GPSTranslator {
	
	public static enum ProjectionType{
		Haversine,
		Spherical
	}
	
	/**
	 * @author Jonathan Cole
	 * Returns the distance between (lat1, lon1) and (lat2, lon2), assuming
	 * each is in decimal degrees.
	 * @param projection is the desired projection type depending on where on Earth you are.
	 * @return
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2, ProjectionType projection){
		double distance = -1;
		switch(projection){
			case Haversine:
				distance = haversineDistance(lat1, lon1, lat2, lon2);
				break;
			case Spherical:
				distance = sphericalDistance(lat1, lon1, lat2, lon2);
				break;
		}
		
		return distance;
	}
	
	/**
	 * @author Jonathan Cole
	 * A more accurate method of determining distance between coordinates.
	 * Optimized for coordinates around Washington, DC (around 39 degrees from the equator)
	 * @return the distance between (lat1, lon1) and (lat2, lon2)
	 */
	static double haversineDistance(double lat1, double lon1, double lat2, double lon2){
		double R = 6371; // km
		double dLat = Math.toRadians(lat2-lat1);
		double dLon = Math.toRadians(lon2-lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		return d * 3280.84; //distance in kilometers -> converted to feet
	}
	
	/**
	 * @author Jonathan Cole
	 * Gets the distance between a pair of coordinates using a spherical projection.
	 * Accurate to around 1 meter.
	 * @return the distance between (lat1, lon1) and (lat2, lon2)
	 */
	public static double sphericalDistance(double lat1, double lon1, double lat2, double lon2){
		Pair coord1 = degreesToFeet(lat1, lon1);
		Pair coord2 = degreesToFeet(lat2, lon2);
		double t1 = Math.pow(coord2.x - coord1.x, 2);
		double t2 = Math.pow(coord2.y - coord1.y, 2);
		double distance = Math.sqrt(t1 + t2);
		return distance;
	}
	
	/**
	 * @author Jonathan Cole
	 * Converts decimal degrees to feet, assuming a spherical projection of coordinates.
	 * Accurate to around 1 meter.
	 * @return
	 */
	static Pair degreesToFeet(double x, double y){
		//60 nautical miles in a degree of latitude, 6076 feet in a nautical mile
		double xFeet = x * 60 * 6076;
		//A degree longitude is 60 nm at the equator and 0 nm at the poles.
		double yFeet = (Math.cos(y) * 60) * 6076;
		Pair p = new Pair((float)xFeet, (float)yFeet);
		//double t1 = Math.pow(alpFeet.x - baseFeet.x, 2);
		//double t2 = Math.pow(alpFeet.y - baseFeet.y, 2);
		//double distance = Math.sqrt(t1 + t2);
		return p;
	}

}
