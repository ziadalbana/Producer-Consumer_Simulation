package Machine;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Consumer implements Runnable {
	ArrayList<String> connectedBefore;
        String connectedAfter;
	private ArrayList<BlockingQueue> sharedQueue1;
	private BlockingQueue sharedQueue2;
	private int SleepingTime ;
	Random rand = new Random(); 

    public Consumer () {
        this.SleepingTime = rand.nextInt(10);
        this.connectedBefore=new ArrayList<>();
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

    public ArrayList<BlockingQueue> getSharedQueue1() {
        return sharedQueue1;
    }

    public void setSharedQueue1(ArrayList<BlockingQueue> sharedQueue1) {
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
                    BlockingQueue  n = sharedQueue1.get(j) ;
                    String i = (String) n.take() ;
                    System.out.println("Consumed: "+ i);
                    Thread.sleep(this.SleepingTime*1000);
                    System.out.println("Produced: " + i);
                    sharedQueue2.put(i);
                    if (j == this.sharedQueue1.size()) j=0 ;
                }
            } catch (InterruptedException ex) {
            }
        }
    }

}
