package Machine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Consumer implements Runnable {
	
	private final BlockingQueue sharedQueue1;
	private final BlockingQueue sharedQueue2;
	private int SleepingTime ;
	Random rand = new Random(); 

    public Consumer (BlockingQueue sharedQueue1,BlockingQueue sharedQueue2) {
        this.sharedQueue1 = sharedQueue1;
        this.sharedQueue2 = sharedQueue2;
        this.SleepingTime = rand.nextInt(10);
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
