
package Service;

import java.util.LinkedList;


public class services {
    Graph graph=new Graph();
   public void addQueue(int order){
       graph.AddQueue(order, new LinkedList<>());
   } 
   public void updateQueueZero(String Colour){
       graph.UpdataQueueZero(Colour);
   }
   public void setConnection(String from,String to){
       if(from.toLowerCase().charAt(0)=='q'&&to.toLowerCase().charAt(0)=='m'){
           int QueueNum=getInt(from);
           Machine m=new Machine(to);
           graph.updateMachine(QueueNum, m);
       }else if(from.toLowerCase().charAt(0)=='m'&&to.toLowerCase().charAt(0)=='q'){
           graph.updateMachineconnection(from);
       }
   }
   public int getInt(String word){
       return Integer.parseInt(word.substring(1));
   }
    
}
