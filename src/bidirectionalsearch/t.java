/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidirectionalsearch;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author koushik
 */
public class t extends Thread{

    public int id;
    t(int id){
        this.id=id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(id);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(t.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start(){
        Thread t=new Thread(this);
        t.start();
    }
    
}
