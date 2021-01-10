package Machine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
	
	public static void main(String args[]) throws InterruptedException{
		  
	     //Creating shared object
	     BlockingQueue sharedQueue = new LinkedBlockingQueue();
	     for (int i=0 ;i<10 ;i++)
	     {
	    	 sharedQueue.put(i);
	     }
	     BlockingQueue sharedQueue3 = new LinkedBlockingQueue();
	     for (int i=10 ;i<20 ;i++)
	     {
	    	 sharedQueue3.put(i);
	     }
	     BlockingQueue sharedQueue2 = new LinkedBlockingQueue();
	     
	     //Creating Producer and Consumer Thread
	   
	    // Thread consThread1 = new Thread(new Consumer(sharedQueue,sharedQueue2));
	     //Thread consThread2 = new Thread(new Consumer(sharedQueue3,sharedQueue2));
	    
	     //consThread1.start();
	     //consThread2.start();
	     
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     Thread.sleep(20000);
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     Thread.sleep(20000);
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     Thread.sleep(20000);
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     Thread.sleep(20000);
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     Thread.sleep(20000);
	     System.out.println(sharedQueue) ;
	     System.out.println(sharedQueue2) ;
	     System.out.println(sharedQueue3) ;
	     
	    }    
}
