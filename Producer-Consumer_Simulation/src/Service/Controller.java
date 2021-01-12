/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Gui.FrameController;
import Machine.Consumer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Controller {
    Strucuture data=new Strucuture();
    ArrayList<Thread> activeThreads=new ArrayList<>();
    private Hashtable<String,Rectangle> rectangles =new Hashtable<>();
    private Hashtable<String,Circle> circles =new Hashtable<>();
    private Hashtable<String,String> machineColors =new Hashtable<>();
    List<Pair<String,String>> lines = new ArrayList<>();
    
    public void addQueue(String order){
        data.addQueue(order);
    }
    public void fillqueueZero(Color colour){
        data.fillqueueZero(colour);
    }
    public void setConnection(String from,String to){
        lines.add(new Pair(from,"M"+to));
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
    public Hashtable<String,String> getColors(){
        return machineColors;
    }
    
    public Pair<Double,Double> getPoint(String name){
        double x=0,y=0;
        if(name.charAt(0)=='Q'){
            x = (2*rectangles.get(name).getX()+rectangles.get(name).getWidth())/2;
            y = (2*rectangles.get(name).getY()+rectangles.get(name).getHeight())/2;
        }else if(name.charAt(0)=='M'){
            x = circles.get(name).getCenterX()+25;
            y = circles.get(name).getCenterY()+25;
        }
        return new Pair(x,y);
    }
    public void addRectangle(String name,Rectangle rec){
        rectangles.put(name, rec);
    }
    public void addCircle(String name,Circle c){
        circles.put(name, c);
        machineColors.put(name, Color.WHITE.toString());
    }
    public synchronized void reDraw(){
        lines.forEach(p-> FrameController.drawLine(p));
        FrameController.reDraw(rectangles.values().iterator(), circles.values().iterator(),machineColors.values().iterator());
    }
    
}
