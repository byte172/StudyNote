package demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Racer implements Callable<Integer> {

	private String winner;
	
	@Override
	public Integer call() throws Exception {
		for(int steps=1;steps<=100;steps++) {
			if(Thread.currentThread().getName().equals("pool-1-thread-1")&&steps%10==0) {
					Thread.sleep(100);
			}
			
			System.out.println(Thread.currentThread().getName()+"-->"+steps);
			boolean flag=gameOver(steps);
			if(flag) {
				return steps;
			}
		}
		return null;
	}

	private boolean gameOver(int steps) {
		if(winner!=null) {
			return true;
		}else {
			if(steps==100) {
				winner=Thread.currentThread().getName();
				System.out.println("winner==>"+winner);
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Racer racer = new Racer();

		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<Integer> result1 = service.submit(racer);
		Future<Integer> result2 = service.submit(racer);
		
		Integer r1 = result1.get();
		Integer r2 = result2.get();
		
		System.out.println(r1+"-->"+r2);
		
		service.shutdown();
	}

}
