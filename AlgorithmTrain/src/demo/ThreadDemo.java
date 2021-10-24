package demo;

import sun.jvm.hotspot.utilities.BitMap;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadDemo extends Thread {

	public ThreadDemo(String str) {
		super(str);
	}
	
	@Override
	public void run() {
//		super.run();
		for(int i=0;i<20;i++) {
			System.out.println(" "+this.getName());
			try {
				sleep(300);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}

/**
 * Callable + FutureTask
 */
class A implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		return -1;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Executor executor = Executors.newCachedThreadPool();
		A a = new A();
		FutureTask<Integer> futureTask = new FutureTask<>(a);
		executor.execute(futureTask);
		((ExecutorService) executor).shutdown();
		System.out.println(futureTask.get());
	}
}

class B implements Runnable {

	@Override
	public void run() {
		System.out.println(1);
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new B());
		thread.start();
		thread.interrupt();
		boolean interrupted = thread.isInterrupted();
		System.out.println(interrupted);
		B b = new B();
		AtomicReference<B> atomicReference = new AtomicReference<>();
		BitMap bitMap = new BitMap(99);

	}
}
