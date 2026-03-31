/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project1;

import java.util.List;
import java.util.Random;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.ArrayList;
import java.lang.String;
import bridges.base.Element;
import bridges.base.SLelement;
import bridges.base.GraphAdjListSimple;
import bridges.base.Edge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author Trist
 */
public class Project1Part1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //create the Bridges object
        Bridges bridges = new Bridges(10, "tristancat101", "1276738718144");

        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB> mylist = ds.getActorMovieIMDBData(1813);
        
        Map<String, Set<String>> movies = new HashMap<>();
        int x=0;
        GraphAdjListSimple<String> graph = new GraphAdjListSimple<String>();
        for(ActorMovieIMDB actorIterator: mylist){
            
            //if(actorIterator.getActor().contains("Kevin_Bac")){System.out.print(actorIterator.getMovie()+",");}
            if("Fleshing_Out_the_'Hollow_Man'_(2000),Hollow_Man:_Anatomy_of_a_Thriller_(2000),Hollow_Man_(2000),New_York_Skyride_(1994),Trapped_(2002),Stir_of_Echoes_(1999),Footloose_(1984),Queens_Logic_(1991),Flatliners_(1990),Telling_Lies_in_America_(1997),Lost_Moon:_The_Triumph_of_Apollo_13_(1996),We_Married_Margo_(2000),Friday_the_13th_(1980),My_Dog_Skip_(2000),Only_When_I_Laugh_(1981),Criminal_Law_(1988),Enormous_Changes_at_the_Last_Minute_(1983),White_Water_Summer_(1987),End_of_the_Line_(1988),Beauty_Shop_(2005),Mystic_River_(2003),Loverboy_(2004),Tremors_(1990),Forty_Deuce_(1982),He_Said,_She_Said_(1991),JFK_(1991),Picture_Perfect_(1997),Yearbook:_An_'Animal_House'_Reunion,_The_(1998),Murder_in_the_First_(1995),Mystic_River:_Beneath_the_Surface_(2004),Apollo_13_(1995),Air_Up_There,_The_(1994),Diner_(1982),Little_Vicious,_A_(1991),Sleepers_(1996),Quicksilver_(1986),River_Wild,_The_(1994),Starting_Over_(1979),Novocaine_(2001),Cavedweller_(2004),Imagine_New_York_(2003),Big_Picture,_The_(1989),Code_of_Conduct_(2001),Animal_House_(1978),Where_the_Truth_Lies_(2005),Destination_Anywhere_(1997),In_the_Cut_(2003),Hero_at_Large_(1980),Pyrates_(1991),Woodsman,_The_(2004),Balto_(1995),Digging_to_China_(1998),Few_Good_Men,_A_(1992),Where_Are_They_Now?:_A_Delta_Alumni_Update_(2003),Wild_Things_(1998),She's_Having_a_Baby_(1988),Planes,_Trains_&_Automobiles_(1987)".contains(actorIterator.getMovie())){
                System.out.println(actorIterator.getActor());
                x=x+1;
            }
            
            //atomic opeartion
            //.computeIfAbsent performs operation if absent and returns the value held, else just returns value
            movies.computeIfAbsent(actorIterator.getMovie(), k -> new HashSet<>())
                    .add(actorIterator.getActor());//no longer need ot do this just move to if statment
            /*if(graph.){//if movie present
            }
            if(){//if actor present in graph
            }*/
        }
        System.out.println(x);
        bridges.setTitle("A Simple Adjacency list based Graph Example.");
        bridges.setDescription("Demonstrate how to create a graph with a few nodes and display it");
        
        // create an adjacency list based graph
        
        
        // create some actor names to be added to the graph



        // Pass the graph object to BRIDGES
        bridges.setDataStructure(graph);

        // Finaly we call the visualize function
        bridges.visualize();
        
        
    }
    
}
