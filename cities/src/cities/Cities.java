/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cities;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.USState;
import bridges.data_src_dependent.USCounty;
import bridges.base.SLelement;
import bridges.base.USMap;
import bridges.base.Color;
import java.util.Iterator;
/**
 *
 * @author Trist
 */
public class Cities {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
		// create bridges object
		Bridges bridges = new Bridges(04, "tristancat101",
			"1276738718144");
		// set title
		bridges.setTitle("Linked List of US Cities with a Map Overlay");
		DataSource ds = bridges.getDataSource();

		// get us map data - states and boundaries
		ArrayList<USState> map_data = ds.getUSMapData ();

		for (USState st: map_data) 
			st.setFillColor(new Color("skyblue"));

		// create a USMap object with the map data
		USMap us_maps = new USMap(map_data);
		bridges.setMap (us_maps);

		// TODO: Replace this with your home town or a favorite city in the US
		SLelement<String>  head = new SLelement<String>("Charlotte NC", "Charlotte NC");
		// and set its lat/long 
		head.setLocation(-80.8431, 35.2271); // Charlotte Lat/Long
                head.setOpacity(.8f);
		head.setSize(30.);
		head.setColor(new Color(255, 255, 255));
		// TODO: 
    	// Build a linked list connecting a set of cities starting from your 
		// hometown around the border of  USA. Use at least 15-20 cities
//author used google to find cities and their location
                SLelement<String>  city02 = new SLelement<String>("Raliegh NC","Raliegh NC");
                city02.setLocation(-78.6382,35.7796);head.setNext(city02);
                head.getLinkVisualizer(city02).setThickness(2.0f);
                
                SLelement<String>  city03 = new SLelement<String>("Norfolk VA","Norfolk VA");
                city03.setLocation(-76.2859,36.8508);city02.setNext(city03);
                city02.getLinkVisualizer(city03).setColor("green");
                
                SLelement<String>  city04 = new SLelement<String>("Baltimore MD","Baltimore MD");
                city04.setLocation(-76.6104,39.2905);city03.setNext(city04);
                city03.getLinkVisualizer(city04).setThickness(3.5f);
                
                SLelement<String>  city05 = new SLelement<String>("Philadelphia PA","Philadelphia PA");
                city05.setLocation(-75.1652,39.9526);city04.setNext(city05);
                city05.setSize(20);
                
                SLelement<String>  city06 = new SLelement<String>("Boston MA","Boston MA");
                city06.setLocation(-71.0565,42.3555);city05.setNext(city06);
                city05.getLinkVisualizer(city06).setColor("green");
                
                SLelement<String>  city07 = new SLelement<String>("Buffalo NY","Buffalo NY");
                city07.setLocation(-78.8789,42.8869);city06.setNext(city07);
                city06.getLinkVisualizer(city07).setThickness(4.0f);
                
                SLelement<String>  city08 = new SLelement<String>("Detroit MI","Detroit MI");
                city08.setLocation(-83.0425,42.3297);city07.setNext(city08);
                city08.setColor(new Color(255, 0, 255));
                                
                SLelement<String>  city09 = new SLelement<String>("Seattle WA","Seattle WA");
                city09.setLocation(-122.3328,47.6061);city08.setNext(city09);
                city08.getLinkVisualizer(city09).setThickness(7.0f);
                
                SLelement<String>  city10 = new SLelement<String>("Portland OR","Portland OR");
                city10.setLocation(-122.6784,45.5152);city09.setNext(city10);
                city09.getLinkVisualizer(city10).setColor("green");
                
                SLelement<String>  city11 = new SLelement<String>("Los Angeles CA","Los Angeles CA");
                city11.setLocation(-118.2426,34.0549);city10.setNext(city11);
                city10.getLinkVisualizer(city11).setThickness(5.0f);
                
                SLelement<String>  city12 = new SLelement<String>("Phoenix AZ","Phoenix AZ");
                city12.setLocation(-112.0725,33.4483);city11.setNext(city12);
                city12.setShape ("triangle");
                
                SLelement<String>  city13 = new SLelement<String>("Austin TX","Austin TX");
                city13.setLocation(-97.7431,30.2672);city12.setNext(city13);
                city12.getLinkVisualizer(city13).setThickness(5.0f);
                
                SLelement<String>  city14 = new SLelement<String>("New Orleans LA","New Orleans LA");
                city14.setLocation(-90.0758,29.9509);city13.setNext(city14);
                SLelement<String>  city15 = new SLelement<String>("Birmingham AL","Birmingham AL");
                city15.setLocation(-86.81/*lol was given incorrect cords by the google search functions*/,33.52);city14.setNext(city15);
                head.setColor(new Color(255, 255, 0));
                
                SLelement<String>  city16 = new SLelement<String>("Miami FL","Miami FL");
                city16.setLocation(-80.1918,25.7617);city15.setNext(city16);
                city15.getLinkVisualizer(city16).setColor("green");

                SLelement<String>  city17 = new SLelement<String>("Atlanta GA","Atlanta GA");
                city17.setLocation(-84.3885,33.7501);city16.setNext(city17);
                city17.setSize(50);
    	// Review the tutorial on single linked list to build the list and
    	// apply attributes like color, size, link thickness, link color


		// set data structure
		bridges.setDataStructure(head);

		// visualize
		bridges.visualize();
	
	}
    
}
