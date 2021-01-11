
package Service;

import Machine.Consumer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class Strucuture {
    Hashtable<String,Queue> queues=new Hashtable<>();
    Hashtable<String,Consumer> machines=new Hashtable<>(); 
    public void addQueue(String order){
        queues.put(order, new LinkedList<>());
    }
    public void fillqueueZero(String Colour){
        queues.get("Q0").add(Colour);
    }
    public void setConnection(String from,String to){
        if(from.toLowerCase().charAt(0)=='q'&&to.toLowerCase().charAt(0)=='m'){
        try{
             machines.get(to).setConnectedBefore(from); 
          }catch(Exception e){
              machines.put(to, new Consumer());
              machines.get(to).setConnectedBefore(from);
          }
              
        }else if(from.toLowerCase().charAt(0)=='m'&&to.toLowerCase().charAt(0)=='q'){
            machines.get(from).setConnectedAfter(to);
        }
    }
    
}
