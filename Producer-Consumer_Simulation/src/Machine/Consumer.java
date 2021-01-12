package Machine;

import Service.Controller;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Consumer implements Runnable {
	ArrayList<String> connectedBefore;
        String connectedAfter;
        private String name; 
	private ArrayList<BlockingQueue<Color>> sharedQueue1;
	private BlockingQueue sharedQueue2;
	private int SleepingTime ;
	Random rand = new Random(); 
        Controller control ;

    public Consumer (String name,Controller control) {
        this.SleepingTime = rand.nextInt(10);
        this.connectedBefore=new ArrayList<>();
        this.name = name;
        this.control = control;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getConnectedBefore() {
        return connectedBefore;
    }

    public void setConnectedBefore(String connectedBefore) {
        this.connectedBefore.add(connectedBefore);
    }

    public String getConnectedAfter() {
        return connectedAfter;
    }

    public void setConnectedAfter(String connectedAfter) {
        this.connectedAfter = connectedAfter;
    }

    public ArrayList<BlockingQueue<Color>> getSharedQueue1() {
        return sharedQueue1;
    }

    public void setSharedQueue1(ArrayList<BlockingQueue<Color>> sharedQueue1) {
        this.sharedQueue1 = sharedQueue1;
    }

    public BlockingQueue getSharedQueue2() {
        return sharedQueue2;
    }

    public void setSharedQueue2(BlockingQueue sharedQueue2) {
        this.sharedQueue2 = sharedQueue2;
    }


    
  
 
        @Override
    public void run() {
        while(true){
            try {
                for(int j=0 ;j<this.sharedQueue1.size();j++)
                {
                    BlockingQueue<Color>  n = sharedQueue1.get(j) ;
                    Color i = n.take() ;
                    control.getColors().put(name, i);
                    control.reDraw();
                    System.out.println("Consumed: "+ i);
                    Thread.sleep(this.SleepingTime*1000);
                    control.getColors().put(name, Color.WHITE);
                    control.reDraw();
                    System.out.println("Produced: " + i);
                    sharedQueue2.put(i);
                    if (j == this.sharedQueue1.size()) j=0 ;
                }
            } catch (InterruptedException ex) {
            }
        }
    }

}
