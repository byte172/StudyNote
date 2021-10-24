package demo;

import java.lang.Thread.State;
import java.util.ArrayList;

public class TestLambda {

	public static void main(String[] args) throws InterruptedException {
		  Thread thread =new Thread(()-> { 
			  for(int i=0;i<20;i++) { 
				  try {
					  Thread.sleep(200); 
					  } 
				  catch (InterruptedException e) { 
					  e.printStackTrace(); 
					  }
				  		System.out.println("aaaa"); 
				  		} 
			  });
		  
		  thread.start(); State state = thread.getState(); System.out.println(state); 
		 
//		Me me = new Me();
//		God god = new God();
//		
//		Thread t = new Thread(me);
//		Thread t2 = new Thread(god);
//		
//		System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().isAlive());
//		
//		t.setDaemon(true);
//		t.start();
//		State state = t.getState();
//		for(int i=0;i<100;i++) {
//			t2.sleep(2000);
//			t2.start();
//			System.out.println(Thread.currentThread().getName()+"-->"+state);
//		}
	}
}
class Me implements Runnable {

	@Override
	public void run() {
		System.out.println("you..");
	}
}

class God implements Runnable{

	@Override
	public void run() {
		System.out.println("god...");
		System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().isAlive());
	}
	
}
