import java.lang.String;
import java.util.*;
import java.io.IOException;
import bridges.base.GraphAdjList;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.Edge;
import bridges.validation.RateLimitException;
import bridges.data_src_dependent.City;

public class Project2A {

	public static void main(String[] args) throws IOException, RateLimitException {

		Bridges bridges = new Bridges(11, "tristancat101","1276738718144");
		bridges.setTitle("Displaying MST of a weighted graph");

		GraphAdjList<String, String, Double> gr = new GraphAdjList<> ();

		bridges.setCoordSystemType("albersusa");
		bridges.setMapOverlay(true);

		// Get US cities data with population larger than 25000
		DataSource ds = bridges.getDataSource();

		// set the parameters
		HashMap<String, String> params = new HashMap<String, String>();
			params.put("min_pop", "100000");    

		// now get the data
		Vector<City> cities = ds.getUSCitiesData(params);

                for(City cit : cities){
                    cit.;
                }
		// print the first two cities and the distance between them
		System.out.println ("Acquired " + cities.size() + " cities with a population over 100,000");

		// plot a few cities
		for (int k = 0; k < 10; k++) {
			City c = cities.get(k);	
			gr.addVertex (c.getCity(), c.getCity());	
			gr.getVertex(c.getCity()).setLocation(c.getLongitude(), c.getLatitude());
		}
		bridges.setDataStructure(gr);
		bridges.visualize();
	}

	static double getDist(double lat1, double long1, double lat2, 
										double long2) {
		//uses the haversine formula
		final int R = 6371000; // meters
		final double phi1 = Math.toRadians(lat1);
		final double phi2 = Math.toRadians(lat2);
		final double delPhi = Math.toRadians((lat2 - lat1));
		final double delLambda = Math.toRadians((long2 - long1));

		final double a = Math.sin(delPhi/2) * Math.sin(delPhi/2)
				+ Math.cos(phi1) * Math.cos(phi2)
					* Math.sin(delLambda/2) * Math.sin(delLambda/2);
		final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R * c; //meters
	}
}
