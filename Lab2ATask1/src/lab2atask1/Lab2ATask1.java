/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2atask1;
import bridges.base.LineChart;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
/**
 *
 * @author Trist
 */
public class Lab2ATask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException, RateLimitException, InterruptedException{
        Bridges bridges = new Bridges(05, "tristancat101",
			"1276738718144");
        bridges.setTitle("Lab2ATask1");
        LineChart plot = new LineChart();
        	
	plot.setTitle("linechart plot");

	double x1[] = new double[] {1, 3, 5, 20};
	double y1[] = new double[] {2, 3, 5, 20};
	plot.setDataSeries("1", x1, y1);
	bridges.setDataStructure(plot);
	bridges.visualize();

	double x2[] = new double[] {2, 15.2,  40};
	double y2[] = new double[] {4, 30.5, 400.99};
	plot.setDataSeries("2", x2, y2);
	bridges.visualize();


	plot.toggleLogarithmicX(true);

	bridges.visualize();

	plot.toggleMouseTrack(true);
	plot.toggleLogarithmicY(true);


	bridges.visualize();

    }
    
}
