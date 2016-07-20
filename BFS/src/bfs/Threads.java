/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import static bfs.BFS.city_list;
import static bfs.BFS.graph_matrix;
import static bfs.BFS.number_of_cities;
import java.util.ArrayList;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Koushik
 */
public class Threads implements Runnable{
    
    String c[]=new String[number_of_cities];
    int parent [] =new int[number_of_cities];
    int level[]=new int[number_of_cities];
    
    
    
    public String t_name;
    public int start; 
    public int end;
    public int point;
    
    public Threads(String t_name, int start,int  end, int point){
        this.t_name=t_name;
        this.start=start;
        this.end=end;
        this.point=point;
    }
    
    
    public void start(){
        Thread t =new Thread(this, t_name); 
        t.start();
    }
    
    
    @Override
    public void run() {
        bfs(start, end);
    }
    
     public synchronized void bfs(int start, int end){
         
        for (int i = 0; i < number_of_cities; i++) {
            c[i]="W";
            parent[i]=-1;
        }
        
        
        Queue<Integer> q=new LinkedList<>();
        
        q.add(start);
        
        while(!q.isEmpty()){
            int t=q.poll();
            BFS.checker(point, t, this, level[t]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Thread Has been Interrupted");
            }
            for (int i = 0; i < number_of_cities; i++) {
               if(graph_matrix[t][i]>0 && c[i].equals("W")){
                   c[i] = "G";
                   parent[i]=t;
                   level[i]=level[t]+1;
                   q.add(i);
               } 
            }
            c[t] = "B";
            if(t==end){
                break;
            }
        }
        //System.out.println("Level"+level[end]);
        //print();
    }
    public Object[]  print(int end){
       ArrayList as=new ArrayList();
        int i=end;
        while(true){
            as.add(city_list[i]);
            //System.out.print(city_list[i]+"->");
            if(i==start){
                break;
            }
            i=parent[i];
        }
        
        return as.toArray();
    }// End of method print
    
}
