/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2b;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Trist
 */
public class PQExample {
    public static void main(String[] args) {
        Path file = Paths.get("output.txt");
        String content="adf";
        try{
            System.out.println(file.getFileName());
            Files.writeString(file,content);
        }catch(IOException e){System.out.println("error");}
        // Create an instance of your comparator
        MyComparator myComparator = new MyComparator();
        // Create a PriorityQueue using the custom comparator
        PriorityQueue<MyObject> priorityQueue = new PriorityQueue<>(myComparator);
        // Add elements to the PriorityQueue
        MyObject obj=new MyObject(5);
        priorityQueue.offer(new MyObject(2));
        priorityQueue.offer(new MyObject(3));
        priorityQueue.offer(new MyObject(7));
        priorityQueue.offer(obj);
        priorityQueue.offer(new MyObject(1));
        priorityQueue.offer(new MyObject(4));
        priorityQueue.offer(new MyObject(9));
        obj.setValue(8);
        priorityQueue.offer(new MyObject(10));
        // Poll elements from the PriorityQueue (they will be ordered based on yourcomparator)
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().getValue());
        }
    }
}
class MyObject {
    private int value;
    // Constructor
    public MyObject(int value) {
        this.value = value;
    }
    // Getter for the value
    public int getValue() {
    return value;
    }
    // Setter for the value
    public void setValue(int value) {
    this.value = value;
    }
}
class MyComparator implements Comparator<MyObject> {
    @Override
    public int compare(MyObject o1, MyObject o2) {
    // Implement your comparison logic here
    // Example: compare based on a 'value' field
    return Integer.compare(o1.getValue(), o2.getValue());
    }
}
