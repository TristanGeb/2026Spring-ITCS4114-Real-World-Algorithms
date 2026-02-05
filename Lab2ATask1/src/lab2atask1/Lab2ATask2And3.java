/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2atask1;

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
public class Lab2ATask2And3 {
    static final int run_amount=21;
    static int count=0;
    static double[] insertionSwapArr = new double[run_amount];
    static double[] bubbleSwapArr = new double[run_amount];
    static double[] insertionSizeArr = new double[run_amount];
    static double[] bubbleSizeArr = new double[run_amount];
    static double[] bubbleCompareArr = new double[run_amount];
    static double[] insertionCompareArr = new double[run_amount];

    //TODO: Implement insertion sort
    static Consumer<int[]> insertion_sort = arr -> {
        insertionSizeArr[count]=arr.length;
        System.out.println("insertion#" + (count+1) + "(len=" + arr.length + ")");
        double swapCount=0;
        double compareCount=0;
        int temp;
        int shiftAmount;
        int lastShiftedKey;
        for (int key=1 ; key<arr.length ; key++){
            compareCount++;
            if(arr[key]>=arr[key-1]){
                continue;
            }
            shiftAmount=0;
            temp=arr[key];
            for (int x=key-1 ;  x>=0;x--){
                compareCount++;
                if(arr[key]>=arr[x]){
                    break;
                }
                shiftAmount++;
            }
            lastShiftedKey=key-shiftAmount;
            for(int toShiftKey=key-1;  toShiftKey>=lastShiftedKey  ;  toShiftKey--){
                arr[toShiftKey+1]=arr[toShiftKey];
                swapCount++;
            }
            swapCount++;
            arr[lastShiftedKey]=temp;
        }
        insertionCompareArr[count]=compareCount;
        insertionSwapArr[count]=swapCount;
        count++;
    };

    //TODO: Implement bubble sort
    static Consumer<int[]> bubble_sort = arr -> {
        int temp;
        bubbleSizeArr[count]=arr.length;
        System.out.println("bubble#" + (count+1) + "(len=" + arr.length + ")");
        double swapCount=0;
        double compareCount=0;
        boolean swapThisRun;
        for(int endElement=arr.length-1;endElement >0;endElement--){
           swapThisRun = false;
            for(int keyElement=0;keyElement <endElement ; keyElement++){
                compareCount++;
                if(arr[keyElement] > arr[keyElement+1] ){
                    swapThisRun = true;
                    temp = arr[keyElement];
                    arr[keyElement] = arr[keyElement+1];
                    arr[keyElement+1] = temp;
                    swapCount++;
                }
            }
            if(swapThisRun == false){
                break;
            }
        }
        bubbleCompareArr[count]=compareCount;
        bubbleSwapArr[count]=swapCount;
        count++;
    };

    public static void main(String[] args) throws IOException,
            RateLimitException, InterruptedException {
        
        Bridges bridges = new Bridges(06, "tristancat101",
			"1276738718144");
        bridges.setTitle("Sorting Benchmark Part1");
        bridges.setDescription("Comparing Run Time Performance of Bubble Sort to Insertion Sort");
        // Review the LineChart and SortingBenchmark Classes 
        // create line chart
        LineChart plot = new LineChart();
        LineChart plot2 = new LineChart();
        // create sorting benchmark
        SortingBenchmark bm = new SortingBenchmark(plot);

        // set parameters, titles, etc
        //bm.linearRange(1000, 2000000, run_amount-1);
bm.linearRange(10000, 200000, run_amount-1);
//insertion_sort
        // Do the benchmark for the bubble sort and insertion sort
        count=0;
        bm.run("Insertion sort",insertion_sort);
        count=0;
        bm.run("Bubble sort",bubble_sort);
        double[] insertionOperationsArr=new double[run_amount];
        double[] bubbleOperationsArr=new double[run_amount];
        for(int x=0; x<run_amount;x++){
            insertionOperationsArr[x]=insertionSwapArr[x] + insertionCompareArr[x];
            bubbleOperationsArr[x]=bubbleSwapArr[x] + bubbleCompareArr[x];
        }
        plot2.setDataSeries("Insertion Swaps & Compares",insertionSizeArr,insertionOperationsArr);
        plot2.setDataSeries("Bubble Swaps & Compares",bubbleSizeArr,bubbleOperationsArr);
        plot2.setTitle("Swaps Done per Array Size");
        plot2.setXLabel("Array Size");
        plot2.setYLabel("Swaps Done");
        // refer to the above classes 
        // use the setdataSeries() method to plot the points (N vs.Operation counts)
        // for both sorting algorithms
        // uncomment these lines in your solution
        
        // set data structure, visualize
        bridges.setDataStructure(plot);
        bridges.visualize();
                Bridges bridges2 = new Bridges(07, "tristancat101",
			"1276738718144");
        bridges2.setTitle("Sorting Benchmark Part2");
        bridges2.setDescription("Comparing Swaps Done of Bubble Sort to Insertion Sort");
        bridges2.setDataStructure(plot2);
        bridges2.visualize();
    }
}
