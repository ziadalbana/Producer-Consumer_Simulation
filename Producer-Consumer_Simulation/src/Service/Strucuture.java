
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
