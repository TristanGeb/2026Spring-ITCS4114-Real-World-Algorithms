/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2a;
import bridges.base.LineChart;
import bridges.benchmark.SortingBenchmark;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @author Trist
 */
public class Lab2A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, 
				RateLimitException, InterruptedException {

    	Bridges bridges = new Bridges(05, "tristancat101",
			"1276738718144");

    bridges.setTitle("Sorting Benchmark");
    bridges.setDescription("Comparing Run Time Performance of Bubble Sort to Insertion Sort");

	// Review the LineChart and SortingBenchmark Classes 

	// create line chart
    LineChart plot = new LineChart();

	// create sorting benchmark
    SortingBenchmark bm = new SortingBenchmark(plot);

	// set parameters, titles, etc
	bm.linearRange(1000, 2000000, 20);

	// Do the benchmark for the bubble sort and insertion sort
	// refer to the above classes 

	// use the setdataSeries() method to plot the points (N vs.Operation counts)
    // for both sorting algorithms

	// uncomment these lines in your solution
	// set data structure, visualize
     bridges.setDataStructure(plot);
     bridges.visualize();
  }
    
}
