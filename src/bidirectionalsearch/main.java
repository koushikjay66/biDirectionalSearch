/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidirectionalsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author koushik
 */
public class main {

    /**
     * @param args the command line arguments
     */
    
    static int number_of_cities;
    static int number_of_roads;
    static String start;
    static String end;
    static int broken_road_count;
    static String broken_roads="";
    static String city_list[];
    static int graph_matrix[][];
    static boolean visited[];
    
    public static void main(String[] args) {
      
        
    }
    
    public static void fileReader() throws FileNotFoundException, IOException{
        BufferedReader br=new BufferedReader(new FileReader("sample"));
        
        String line=br.readLine();
        String temp[]=line.split(",");
        number_of_cities=Integer.parseInt(temp[0]);
        number_of_roads=Integer.parseInt(temp[1]);
        city_list=new String[number_of_cities];
        visited=new boolean[number_of_cities];
        graph_matrix=new int[number_of_cities][number_of_cities];
        
        start=br.readLine();
        end=br.readLine();
        
        broken_road_count=Integer.parseInt(br.readLine());
        
        for (int i = 0; i < broken_road_count; i++) {
            broken_roads=broken_roads+br.readLine();
        }
        int count=0;

        for (int i = 0; i < number_of_roads; i++) {
            String temp2=br.readLine();
            String roads[]=temp2.split(",");
            
            int row=count;
           int r=search(city_list, roads[0]);
           if(r==-1){
               city_list[count]=roads[0];
               count++;
           }else{
               row=r;
           }
           int col=count;
           r=search(city_list, roads[1]);
           if(r==-1){
               city_list[count]=roads[1];
               count++;
           }else{
               col=r;
           }
            
           
            graph_matrix[row][col]=graph_matrix[col][row]=1;
            
            if(broken_roads.contains(temp2)){
                graph_matrix[row][col]=graph_matrix[col][row]=-1;
            }
            
            
        }
        
        
    }// End of read File Method
    
    public static int search(String []arr, String val){
        //System.out.println(val);
        for (int i = 0; i < arr.length; i++) {
            
           if(arr[i]!=null){
                if( arr[i].equalsIgnoreCase(val)){
                   
                return i;     
            }
           }
        }
        return -1;
    }// End of function search
    
    
      public static void ddd(int start, int end){
          if(start!=end){
              visited[start]=true;
          }
          
          System.out.println("Visiting ->"+city_list[start]+"->");
          for (int i = 0; i < number_of_cities; i++) {
              if(visited[i]!=true && graph_matrix[start][i]!=0){
                  ddd(i, end);
              }
          }
      }
    
    
}
