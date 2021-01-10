package Machine;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Consumer implements Runnable {
	ArrayList<Integer> connectedBefore;
        int connectedAfter;
	private BlockingQueue sharedQueue1;
	private BlockingQueue sharedQueue2;
	private int SleepingTime ;
	Random rand = new Random(); 

    public Consumer () {
        this.SleepingTime = rand.nextInt(10);
        this.connectedBefore=new ArrayList<>();
    }

    public ArrayList<Integer> getConnectedBefore() {
        return connectedBefore;
    }

    public void setConnectedBefore(int connectedBefore) {
        this.connectedBefore.add(connectedBefore);
    }

    public int getConnectedAfter() {
        return connectedAfter;
    }

    public void setConnectedAfter(int connectedAfter) {
        this.connectedAfter = connectedAfter;
    }

    public BlockingQueue getSharedQueue1() {
        return sharedQueue1;
    }

    public void setSharedQueue1(BlockingQueue sharedQueue1) {
        this.sharedQueue1 = sharedQueue1;
    }

    public BlockingQueue getSharedQueue2() {
        return sharedQueue2;
    }

    public void setSharedQueue2(BlockingQueue sharedQueue2) {
        this.sharedQueue2 = sharedQueue2;
    }
    
  
    
    public void run() {
        while(true){
            try {
            	int i = (int) sharedQueue1.take() ;
                System.out.println("Consumed: "+ i);
                Thread.sleep(this.SleepingTime*1000);
                System.out.println("Produced: " + i);
                sharedQueue2.put(i);
            } catch (InterruptedException ex) {
            }
        }
    }

}
