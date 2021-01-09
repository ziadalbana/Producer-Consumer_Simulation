/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.Queue;

/**
 *
 * @author Alex
 */
public class Machine {
    String name;
    String Colour;
   Queue connectedQueue;
    public Machine(String name){
        this.name=name; 
    }
    public void updateMachineConnected(Queue q){
        this.connectedQueue.add(q);
    }
    
}
