/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2b;
import bridges.base.LineChart;
import bridges.benchmark.SortingBenchmark;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
/**
 *
/**
 *
 * @author Trist
 */
public class Lab2B {
    static final int run_amount=20;
    static int count=0;
    static double[] insertionSwapArr = new double[run_amount];
    static double[] quickSwapArr = new double[run_amount];
    static double[] insertionSizeArr = new double[run_amount];
    static double[] quickSizeArr = new double[run_amount];
    static double[] quickCompareArr = new double[run_amount];
    static double[] insertionCompareArr = new double[run_amount];
    static int temp;
    static double compareCount;
    static double swapCount;
    //TODO: Implement insertion sort
    static Consumer<int[]> insertion_sort = arr -> {
        insertionSizeArr[count]=arr.length;
        System.out.println("insertion#" + (count+1) + "(len=" + arr.length + ")");
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

    
    static void quicksortalg(int[] arr,int front,int back){
        int fronttemp=front;
        int backtemp=back;
        //System.out.println(front + ","+back);
        if(back-front == 1){
            compareCount++;
            if(arr[front] > arr[back]){
                swapCount++;
                temp = arr[back];
                arr[back] = arr[front];
                arr[front] = temp;
                return;
            }
            return;
        }
        if(back-front <1){
            return;
        }
        int head=front;
        front = front+1;
        boolean keysOverlapFlag=false;
        
        do{
            while(true){//break conditions are instant
                compareCount++;
                if(arr[front]>arr[head]){
                    break;
                }
                front++;
                if(front==back){//keys point to same
                    compareCount++;
                    if(arr[head]>arr[front]){
                        swapCount++;
                        temp=arr[head];
                        arr[head] = arr[front];
                        arr[front] = temp;
                        
                        /*for(int x=fronttemp;x<=front-1;x++){System.out.print(","+arr[x]+"("+x+")");}
                        System.out.print("     " + arr[front]+"     ");
                        for(int x=front+1;x<=backtemp;x++){System.out.print(","+arr[x]);}
                        System.out.println();*/
                        
                        quicksortalg(arr,fronttemp,front-1);
                        quicksortalg(arr,front+1,backtemp);
                        return;
                    }
                    else{
                        swapCount++;
                        temp=arr[head];
                        arr[head]=arr[front-1];
                        arr[front-1] =temp;
                        
                        /*for(int x=fronttemp;x<=front-2;x++){System.out.print(","+arr[x]+"("+x+")");}
                        System.out.print("     " + arr[front-1]+"     ");
                        for(int x=front;x<=backtemp;x++){System.out.print(","+arr[x]);}
                        System.out.println();*/
                        
                        quicksortalg(arr,fronttemp,front-2);
                        quicksortalg(arr,front,backtemp);
                        return;
                    }
                }
            }
            while(true){
                compareCount++;
                if(arr[back]<arr[head]){
                    break;
                }
                back--;
                if(front == back){
                    if(front-1 != head){
                        swapCount++;
                        temp = arr[head];
                        arr[head] = arr[front-1];
                        arr[front-1] = temp;
                    }
                    
                    /*for(int x=fronttemp;x<=  front-2  ;x++){System.out.print(","+arr[x]+"("+x+")");}
                    System.out.print("     " + arr[front-1]+"     ");
                    for(int x=  front  ;x<=backtemp;x++){System.out.print(","+arr[x]);}
                    System.out.println("");*/
                        
                    quicksortalg(arr,fronttemp,  front-2  );
                    quicksortalg(arr,front,backtemp);
                    return;
                }
            }
            if(front!=back){
                swapCount++;
                temp=arr[front];
                arr[front]=arr[back];
                arr[back]=temp;
            }
        }while(front<back);
        System.out.println("this should not run");
        return;
    }
    //TODO: Implement quick sort
    static Consumer<int[]> quick_sort = arr -> {
        quickSizeArr[count]=arr.length;
        System.out.println("quicsort#" + (count+1) + "(len=" + arr.length + ")");
        //for(int x=0;x<arr.length;x++){System.out.print(","+arr[x]);}
        //System.out.println("\n");
        quicksortalg(arr,0,arr.length-1);
        
        //for(int x=0;x<arr.length;x++){System.out.print(","+arr[x]);}
        //System.out.println();
 
        quickCompareArr[count]=compareCount;
        quickSwapArr[count]=swapCount;
        count++;
    };

    public static void main(String[] args) throws IOException,
            RateLimitException, InterruptedException {
        
        Bridges bridges = new Bridges(8, "tristancat101","1276738718144");
        bridges.setTitle("Sorting Benchmark Part1");
        bridges.setDescription("Comparing Run Time Performance of Quick Sort to Insertion Sort");
        // Review the LineChart and SortingBenchmark Classes 
        // create line chart
        LineChart plot = new LineChart();
        LineChart plot2 = new LineChart();
        // create sorting benchmark
        SortingBenchmark bm = new SortingBenchmark(plot);

        //bm.linearRange(10000, 200000, run_amount-1);
        bm.linearRange(10000, 100000, run_amount-1);
        count=0;compareCount=0;swapCount=0;
        bm.run("Insertion sort",insertion_sort);
        count=0;compareCount=0;swapCount=0;
        for(int x=0;x<run_amount;x++){quickSizeArr[count] = 0;}
        bm.run("Quick sort",quick_sort);
        double[] insertionOperationsArr=new double[run_amount];
        double[] quickOperationsArr=new double[run_amount];
        for(int x=0; x<run_amount;x++){
            insertionOperationsArr[x]=insertionSwapArr[x] + insertionCompareArr[x];
            quickOperationsArr[x]=quickSwapArr[x] + quickCompareArr[x];
        }
        plot2.setDataSeries("Insertion Swaps & Compares",insertionSizeArr,insertionOperationsArr);
        plot2.setDataSeries("Quick Swaps & Compares",quickSizeArr,quickOperationsArr);
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
                Bridges bridges2 = new Bridges(9, "tristancat101",
			"1276738718144");
        bridges2.setTitle("Sorting Benchmark Part2");
        bridges2.setDescription("Comparing Swaps Done of Quick Sort to Insertion Sort");
        bridges2.setDataStructure(plot2);
        bridges2.visualize();
    }
}
