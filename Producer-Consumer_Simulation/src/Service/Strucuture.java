
package Service;

import Machine.Consumer;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.scene.paint.Color;

public class Strucuture {
    Hashtable<String,BlockingQueue<Color>> queues;
    Hashtable<String,Consumer> machines;

    public Strucuture() {
        this.queues = new Hashtable<>();
        this.machines=new Hashtable<>();
    }
    public void addQueue(String order){
        queues.put(order, new LinkedBlockingQueue<>());
    }
    public void fillqueueZero(Color Colour){
        queues.get("Q0").add(Colour);
    }
    public void setConnection(String from,String to){
        if(from.charAt(0)=='Q'&&to.toLowerCase().charAt(0)=='M'){
        try{
             machines.get(to).setConnectedBefore(from); 
          }catch(Exception e){
              machines.put(to, new Consumer(to));
              machines.get(to).setConnectedBefore(from);
          }
              
        }else if(from.charAt(0)=='M'&&to.toLowerCase().charAt(0)=='Q'){
            machines.get(from).setConnectedAfter(to);
        }
    }
    
}
