import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import bridges.base.Element;
import bridges.base.SLelement;
import bridges.base.GraphAdjList;
import bridges.base.Edge;
import bridges.base.Color;
import bridges.base.ElementVisualizer;
import bridges.base.LinkVisualizer;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.OsmData;
import bridges.data_src_dependent.OsmVertex;
import bridges.data_src_dependent.OsmEdge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Project2B {

    public static void main(String[] args) throws Exception {
        Path file = Paths.get("output.txt");
        String content="";
//create the Bridges object, set credentials
		Bridges bridges = new Bridges(12, "tristancat101","1276738718144");
        bridges.setTitle("Graph : Shortest Path Algorithms on OpenStreet Map Data");
//Getting Data
        DataSource ds = bridges.getDataSource();
        OsmData osm_data = ds.getOsmData("Charlotte, North Carolina", "secondary");
        GraphAdjList<Integer, OsmVertex, Double> graph = osm_data.getGraph();
        
        System.out.println(graph.getVertices().size() + "vertices..");
        
        double[] latRange = new double[2];
        double[] longRange = new double[2];
        osm_data.getLatLongRange(latRange,longRange);
        
        int closestToCenterKey=-1;
        double closestToCenterDistance=-1;
        double centerLat=latRange[0]+((latRange[1]-latRange[0])/2);
        double centerLong=longRange[0]+((longRange[1]-longRange[0])/2);
        boolean firstpass=true;
        for(Map.Entry<Integer,Element<OsmVertex>> entry: graph.getVertices().entrySet()){
            //entry.getValue().setSize(5);
            //entry.getValue().setShape("circle");
            entry.getValue().setColor(new Color(255, 255, 255));
            double distanceToCenter=Math.pow(
                            Math.pow(centerLat-entry.getValue().getValue().getLatitude(),2) +
                            Math.pow(centerLong-entry.getValue().getValue().getLongitude(),2)
                        ,.5);
            if(firstpass){
                firstpass=false;
                closestToCenterKey = entry.getKey();
                closestToCenterDistance = distanceToCenter;
            }else{
                if(closestToCenterDistance > distanceToCenter){
                    closestToCenterKey = entry.getKey();
                    closestToCenterDistance=distanceToCenter;
                }
            }
        }
        System.out.println("Closest to center is \n\tKey="+closestToCenterKey +"\n\tlat="+graph.getVertex(closestToCenterKey).getValue().getLatitude()+"\n\tlong="+graph.getVertex(closestToCenterKey).getValue().getLongitude());
        graph.getVertex(closestToCenterKey).setSize(50);
        graph.getVertex(closestToCenterKey).setShape("Star");
        graph.getVertex(closestToCenterKey).setColor(new Color(0, 0, 0));
        System.out.println(graph.getVertex(closestToCenterKey).getClass());
        System.out.println(graph.outgoingEdgeSetOf(closestToCenterKey));
        
        
        MyComparator myComparator = new MyComparator();
        // Create a PriorityQueue using the custom comparator
        PriorityQueue<VectorInfo> prioQueue = new PriorityQueue<>(myComparator);
        Map<Integer,VectorInfo> vectorData=new HashMap<>();
        for(Map.Entry<Integer,Element<OsmVertex>> entry: graph.getVertices().entrySet()){
            vectorData.put(entry.getKey(),new VectorInfo(entry.getKey()));
            if(entry.getKey()==0){System.out.println("cats");}
        }
        vectorData.get(closestToCenterKey).distance=closestToCenterDistance;//updates teh vectorData on the main node
        for(Map.Entry<Integer,VectorInfo> entry: vectorData.entrySet()){
            prioQueue.offer(vectorData.get(entry.getKey()));
        }
        while(!prioQueue.isEmpty()){
            int key=prioQueue.poll().key;
            System.out.println(vectorData.get(key).toString());
            //SLelement<Edge<Integer,Double>> next=graph.getAdjacencyList(key);
            if(vectorData.get(key).visted==true){System.out.println("allready visted");}
            content=content + vectorData.get(key).distance+"\n";
            if(graph.getAdjacencyList(key)==null){vectorData.get(key).visted=true;continue;}
            for(Edge<Integer,Double> ent:graph.getAdjacencyList(key)){
                if(vectorData.get(key).visted==true){System.out.println("allready visted skipping");continue;}
                if(ent.getTo()==key){System.out.println("error with getTo() going to prevous key");}
                double distanceToNextNode=Math.pow(
                        Math.pow(graph.getVertex(key).getValue().getLatitude()-graph.getVertex(ent.getTo()).getValue().getLatitude(),2) +
                        Math.pow(graph.getVertex(key).getValue().getLongitude()-graph.getVertex(ent.getTo()).getValue().getLongitude(),2)
                        ,.5);
                double newDistance=distanceToNextNode+vectorData.get(key).distance;
                if(newDistance < vectorData.get(ent.getTo()).distance){//new distance is shorter
                    vectorData.get(ent.getTo()).distance=newDistance;
                    System.out.println("\tupdated "+ent.getTo()+" with "+vectorData.get(ent.getTo()).distance);
                    prioQueue.remove(vectorData.get(ent.getTo()));
                    prioQueue.offer(vectorData.get(ent.getTo()));
                }
            }
            vectorData.get(key).visted=true;
        }
        
        double greatestDistance=0;//not max double value
        for(Map.Entry<Integer,VectorInfo> entry: vectorData.entrySet()){//get highest distance
            if(entry.getValue().distance==Double.MAX_VALUE){continue;}
            if(greatestDistance <entry.getValue().distance){greatestDistance=entry.getValue().distance;}
        }
        for(Map.Entry<Integer,VectorInfo> entry: vectorData.entrySet()){//set color
            if(entry.getValue().distance==Double.MAX_VALUE){
                continue;
            }else{
                double colorValue= 255 *(greatestDistance-entry.getValue().distance)/greatestDistance;
                int colorint= (int) Math.round(colorValue);
                graph.getVertex(entry.getValue().key).setColor(new Color (colorint,colorint,colorint));
            }
        }
        System.out.println(String.format("greatest distance is %f.5",greatestDistance)+"");
        bridges.setTitle("Dijkshtra's shortest path styled by distance from "+closestToCenterKey);
        graph.forceLargeVisualization(true);
        bridges.setDataStructure(graph);
        bridges.visualize();
        try{
            System.out.println(file.getFileName());
            Files.writeString(file,content);
        }catch(IOException e){System.out.println("error writing to file");}
    }
}
class VectorInfo{
    public boolean visted;
    public double distance;
    public int key;
    public VectorInfo(){
        visted=false;
        distance=Double.MAX_VALUE;//infi
    }
    public VectorInfo(int k){
        visted=false;
        distance=Double.MAX_VALUE;//infi
        key=k;
    }
    @Override
    public String toString(){
        return String.format("key=%d\tdistance=%f.5\tvisted=%b",key,distance,visted);
    }
};
class MyComparator implements Comparator<VectorInfo> {
    @Override
    public int compare(VectorInfo v1, VectorInfo v2) {
    // Implement your comparison logic here
    // Example: compare based on a 'value' field
    return Double.compare(v1.distance,v2.distance);
    }
}