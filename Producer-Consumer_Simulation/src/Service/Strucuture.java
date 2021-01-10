
package Service;

import Machine.Consumer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Strucuture {
    Hashtable<Integer,Queue> queues=new Hashtable<>();
    Hashtable<Integer,Consumer> machines=new Hashtable<>(); 
    public void addQueue(int order){
        queues.put(order, new LinkedList<>());
    }
    public void fillqueueZero(String Colour){
        queues.get(0).add(Colour);
    }
    public void setConnection(String from,String to){
        int fromNum=getInt(from);
        int toNum=getInt(to);
        if(from.toLowerCase().charAt(0)=='q'&&to.toLowerCase().charAt(0)=='m'){
        try{
             machines.get(toNum).setConnectedBefore(fromNum); 
          }catch(Exception e){
              machines.put(toNum, new Consumer());
              machines.get(toNum).setConnectedBefore(fromNum);
          }
              
        }else if(from.toLowerCase().charAt(0)=='m'&&to.toLowerCase().charAt(0)=='q'){
            machines.get(fromNum).setConnectedAfter(toNum);
        }
    }
    private int getInt(String word){
       return Integer.parseInt(word.substring(1));
   }
}
