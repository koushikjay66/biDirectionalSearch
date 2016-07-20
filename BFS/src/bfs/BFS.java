/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Koushik
 */
public class BFS {

    static int number_of_cities;
    static int number_of_roads;
    static String start;
    static String end;
    static String city_list[];
    static int graph_matrix[][];
    static boolean visited[];
    static int tuntuni[]=new int[2];
    static Object nir[]=new Object[2];
    static int l[]=new int[2];
   
    
    
    public static void main(String[] args) throws IOException {
        fileReader();
        
        Threads tuni = new Threads("tuni",search(city_list, start), search(city_list, end), 0);
        tuni.start();
        
        Threads koushik = new Threads("koushik",search(city_list, end), search(city_list, start), 1);
        koushik.start();
    }
    
    
    public static void checker(int point, int position, Threads t, int level){
        
        tuntuni[point]=position;
        l[point]=level;
        nir[point]=t;
        int i=Math.abs(point-1);
        
        if(tuntuni[point]==tuntuni[i]){
            
            
            Object p[]=((Threads)nir[0]).print(position);
            Object p1[]=((Threads)nir[1]).print(position);
            for (int j = p.length-1; j>0; j--) {
                System.out.print(p[j]+"->");
            }
            for (int j = 0; j<p1.length-1; j++) {
                System.out.print(p1[j]+"->");
            }
            
            System.out.print(p1[p1.length-1]);
            
            
            System.out.println("");
            System.out.println("Length: "+(l[point]+l[i]));
            System.out.print("City: "+city_list[position]+" Direction: "+ ((Threads)nir[i]).t_name);
            System.out.println(" #Roads: "+(l[i]));
            System.exit(0);
        }
    }
    /*
        public static void bfs(int start, int end){
            String c[]=new String[number_of_cities];
            int parent [] =new int[number_of_cities];
            int level[]=new int[number_of_cities];

            for (int i = 0; i < number_of_cities; i++) {
                c[i]="W";
                parent[i]=-1;
                level[i]=-1;
            }

            c[start]="W";
            level[start]=0;

            Queue<Integer> q=new LinkedList<Integer>();

            q.add(start);

            while(!q.isEmpty()){
                int t=q.poll();

                //System.out.print(city_list[t]+"->");

                for (int i = 0; i < number_of_cities; i++) {
                   if(graph_matrix[t][i]>0 && c[i].equals("W")){
                       c[i] = "G";
                       parent[i]=t;
                       level[i]=level[t]+1;
                       q.add(i);
                   } 
                }
            }
             System.out.println("Level"+level[end]);
            print(parent, end, start );
        }
        public static void print(int parent[], int end, int start){

            int i=end;
            while(true){
                System.out.print(city_list[i]+"->");
                if(i==start){
                    break;
                }
                i=parent[i];
            }
        }// End of method print
    
*/
     public static void fileReader() throws FileNotFoundException, IOException{
        BufferedReader br=new BufferedReader(new FileReader("sample.txt"));
        
        String line=br.readLine();
        String temp[]=line.split(",");
        number_of_cities=Integer.parseInt(temp[0]);
        number_of_roads=Integer.parseInt(temp[1]);
        city_list=new String[number_of_cities];
      
        visited=new boolean[number_of_cities];
        graph_matrix=new int[number_of_cities][number_of_cities];
        
        start=br.readLine();
        end=br.readLine();

        int count=0;

        for (int i = 0; i <number_of_roads; i++) {
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
            
            graph_matrix[row][col]=graph_matrix[col][row]=Integer.parseInt(roads[2]);

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
    
}
