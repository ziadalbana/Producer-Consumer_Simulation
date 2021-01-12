/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Machine.Consumer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import javafx.scene.paint.Color;

public class Controller {
    Strucuture data=new Strucuture();
    ArrayList<Thread> activeThreads=new ArrayList<>();
    public void addQueue(String order){
        data.addQueue(order);
    }
    public void fillqueueZero(Color colour){
        data.fillqueueZero(colour);
    }
    public void setConnection(String from,String to){
        data.setConnection(from, to);
    }
    public void startTreads(){
        int size=data.machines.size();
         Set<String> keys=data.machines.keySet();
        setMachinesQueues();
        keys.forEach((key) -> {
            activeThreads.add(new Thread(data.machines.get(key)));
        });
        for(int i=0;i<size;i++){
            activeThreads.get(i).start();
        }
    }
    private void setMachinesQueues(){
        Set<String> keys=data.machines.keySet();
        keys.forEach((key) -> {
            ArrayList<BlockingQueue> beforeQueues=new ArrayList<>();
            for (int j = 0; j < data.machines.get(key).getConnectedBefore().size(); j++) {
                BlockingQueue queue = (BlockingQueue) data.queues.get(data.machines.get(key).getConnectedBefore().get(j));
                beforeQueues.add(queue);
            }
            BlockingQueue afterQueue = (BlockingQueue) data.queues.get(data.machines.get(key).getConnectedAfter());
            data.machines.get(key).setSharedQueue1(beforeQueues);
            data.machines.get(key).setSharedQueue2(afterQueue);
        });
    }
    
}
