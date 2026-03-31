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
 * /**
 *
 * @author Trist
 */
public class Lab2B {

    static final int RUN_AMOUNT = 20;
    static int count = 0;
    static double[] insertionSwapArr = new double[RUN_AMOUNT];
    static double[] quickSwapArr = new double[RUN_AMOUNT];
    static double[] insertionSizeArr = new double[RUN_AMOUNT];
    static double[] quickSizeArr = new double[RUN_AMOUNT];
    static double[] insertionCompareArr = new double[RUN_AMOUNT];
    static double[] quickCompareArr = new double[RUN_AMOUNT];
    static int temp;
    static double compareCount;
    static double swapCount;
    //TODO: Implement insertion sort
    static Consumer<int[]> insertion_sort = arr -> {
        insertionSizeArr[Lab2B.count] = arr.length;
        System.out.println("insertion#" + (Lab2B.count + 1) + "(len=" + arr.length + ")");
        int shiftAmount;
        int lastShiftedKey;
        for (int key = 1; key < arr.length; key++) {
            Lab2B.compareCount++;
            if (arr[key] >= arr[key - 1]) {
                continue;
            }
            shiftAmount = 0;
            temp = arr[key];
            for (int x = key - 1; x >= 0; x--) {
                Lab2B.compareCount++;
                if (arr[key] >= arr[x]) {
                    break;
                }
                shiftAmount++;
            }
            lastShiftedKey = key - shiftAmount;
            for (int toShiftKey = key - 1; toShiftKey >= lastShiftedKey; toShiftKey--) {
                arr[toShiftKey + 1] = arr[toShiftKey];
                Lab2B.swapCount++;
            }
            Lab2B.swapCount++;
            arr[lastShiftedKey] = Lab2B.temp;
        }
        insertionCompareArr[Lab2B.count] = Lab2B.compareCount;
        insertionSwapArr[Lab2B.count] = Lab2B.swapCount;
        Lab2B.count++;
        Lab2B.compareCount = 0;
        Lab2B.swapCount = 0;
    };
    static int passingFront;
    static int passingBack;

    //TODO: Implement quick sort
    static Consumer<int[]> quick_sort = arr -> {
        if (Lab2B.passingBack == -1) {
            System.out.println("quicsort#" + (Lab2B.count + 1) + "(len=" + arr.length + ")");
            quickSizeArr[Lab2B.count] = arr.length;
            passingFront = 0;
            passingBack = arr.length - 1;
            Lab2B.quick_sort.accept(arr);
            quickCompareArr[Lab2B.count] = compareCount;
            quickSwapArr[Lab2B.count] = swapCount;
            Lab2B.count++;
            passingFront = 0;
            passingBack = -1;
            compareCount = 0;
            swapCount = 0;
        } else {
            int front = passingFront;
            int back = passingBack;
            int fronttemp = front;
            int backtemp = back;
            //System.out.println(front + ","+back);
            if (back - front < 50) {
                int shiftAmount;
                int lastShiftedKey;
                for (int key = front + 1; key < back + 1; key++) {
                    compareCount++;
                    if (arr[key] >= arr[key - 1]) {
                        continue;
                    }
                    shiftAmount = 0;
                    temp = arr[key];
                    for (int x = key - 1; x >= front; x--) {
                        compareCount++;
                        if (arr[key] >= arr[x]) {
                            break;
                        }
                        shiftAmount++;
                    }
                    lastShiftedKey = key - shiftAmount;
                    for (int toShiftKey = key - 1; toShiftKey >= lastShiftedKey; toShiftKey--) {
                        arr[toShiftKey + 1] = arr[toShiftKey];
                        swapCount++;
                    }
                    swapCount++;
                    arr[lastShiftedKey] = temp;
                }
                return;
            }
            if (back - front == 1) {
                compareCount++;
                if (arr[front] > arr[back]) {
                    swapCount = swapCount + 3;
                    temp = arr[back];
                    arr[back] = arr[front];
                    arr[front] = temp;
                    return;
                }
                return;
            }
            if (back - front < 1) {
                return;
            }
            int head = front;
            front = front + 1;
            boolean keysOverlapFlag = false;

            do {
                while (true) {//break conditions are instant
                    compareCount++;
                    if (arr[front] > arr[head]) {
                        break;
                    }
                    front++;
                    if (front == back) {//keys point to same
                        Lab2B.compareCount++;
                        if (arr[head] > arr[front]) {
                            Lab2B.swapCount++;
                            temp = arr[head];
                            arr[head] = arr[front];
                            arr[front] = temp;

                            /*for(int x=fronttemp;x<=front-1;x++){System.out.print(","+arr[x]+"("+x+")");}
                        System.out.print("     " + arr[front]+"     ");
                        for(int x=front+1;x<=backtemp;x++){System.out.print(","+arr[x]);}
                        System.out.println();*/
                            passingFront = fronttemp;
                            passingBack = front - 1;
                            Lab2B.quick_sort.accept(arr);

                            passingFront = front + 1;
                            passingBack = backtemp;
                            Lab2B.quick_sort.accept(arr);
                            return;
                        } else {
                            swapCount = swapCount + 3;
                            temp = arr[head];
                            arr[head] = arr[front - 1];
                            arr[front - 1] = temp;

                            /*for(int x=fronttemp;x<=front-2;x++){System.out.print(","+arr[x]+"("+x+")");}
                        System.out.print("     " + arr[front-1]+"     ");
                        for(int x=front;x<=backtemp;x++){System.out.print(","+arr[x]);}
                        System.out.println();*/
                            passingFront = fronttemp;
                            passingBack = front - 2;
                            Lab2B.quick_sort.accept(arr);

                            passingFront = front;
                            passingBack = backtemp;
                            Lab2B.quick_sort.accept(arr);
                            return;
                        }
                    }
                }
                while (true) {
                    compareCount++;
                    if (arr[back] < arr[head]) {
                        break;
                    }
                    back--;
                    if (front == back) {
                        if (front - 1 != head) {
                            swapCount = swapCount + 3;
                            temp = arr[head];
                            arr[head] = arr[front - 1];
                            arr[front - 1] = temp;
                        }

                        /*for(int x=fronttemp;x<=  front-2  ;x++){System.out.print(","+arr[x]+"("+x+")");}
                    System.out.print("     " + arr[front-1]+"     ");
                    for(int x=  front  ;x<=backtemp;x++){System.out.print(","+arr[x]);}
                    System.out.println("");*/
                        passingFront = fronttemp;
                        passingBack = front - 2;
                        Lab2B.quick_sort.accept(arr);

                        passingFront = front;
                        passingBack = backtemp;
                        Lab2B.quick_sort.accept(arr);
                        return;
                    }
                }
                if (front != back) {
                    swapCount = swapCount + 3;
                    temp = arr[front];
                    arr[front] = arr[back];
                    arr[back] = temp;
                }
            } while (front < back);
            System.out.println("this should not run");
            return;
        }
    };

    public static void main(String[] args) throws IOException, RateLimitException, InterruptedException {
        Bridges bridges = new Bridges(8, "tristancat101", "1276738718144");
        bridges.setTitle("Sorting Benchmark Part1 Insertion Sort");
        bridges.setDescription("Benchmark of Insertion Sort");
        // Review the LineChart and SortingBenchmark Classes 
        // create line chart
        LineChart plot = new LineChart();
        LineChart plot2 = new LineChart();
        // create sorting benchmark
        SortingBenchmark bm = new SortingBenchmark(plot);
        SortingBenchmark bm2=new SortingBenchmark(plot2);
        //bm.linearRange(10000, 200000, RUN_AMOUNT-1);
        bm.linearRange(10000, 500000, RUN_AMOUNT - 1);
        count = 0;
        compareCount = 0;
        swapCount = 0;
        bm.run("Insertion sort Runtime", insertion_sort);

        count = 0;
        compareCount = 0;
        swapCount = 0;
        for (int x = 0; x < RUN_AMOUNT; x++) {
            quickSizeArr[Lab2B.count] = 0;
        }
        passingFront = 0;
        passingBack = -1;
        bm2.linearRange(10000, 100000000, RUN_AMOUNT - 1);
        bm2.run("Quick sort runtime", quick_sort);
        double[] insertionOperationsArr = new double[RUN_AMOUNT];
        double[] quickOperationsArr = new double[RUN_AMOUNT];
        for (int x = 0; x < RUN_AMOUNT; x++) {
            insertionOperationsArr[x] = insertionSwapArr[x] + insertionCompareArr[x];
            quickOperationsArr[x] = quickSwapArr[x] + quickCompareArr[x];
        }
        //plot.setD
        plot.setDataSeries("Insertion Swaps & Compares", insertionSizeArr, insertionOperationsArr);
        bridges.setDataStructure(plot);
        bridges.visualize();
        plot2.setDataSeries("Quick Swaps & Compares", quickSizeArr, quickOperationsArr);
        plot2.setTitle("Swaps Done per Array Size");
        plot2.setXLabel("Array Size");
        plot2.setYLabel("Swaps Done");
        // refer to the above classes 
        // use the setdataSeries() method to plot the points (N vs.Operation counts)
        // for both sorting algorithms
        // uncomment these lines in your solution

        // set data structure, visualize
        
        Bridges bridges2 = new Bridges(9, "tristancat101",
                "1276738718144");
        bridges2.setTitle("Sorting Benchmark Part2");
        bridges2.setDescription("Comparing Swaps Done of Quick Sort to Insertion Sort");
        bridges2.setDataStructure(plot2);
        bridges2.visualize();
    }
}
