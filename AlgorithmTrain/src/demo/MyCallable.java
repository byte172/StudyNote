package demo;

import java.util.Date;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {


	@Override
	public String call() throws Exception {

		for (int i = 0; i < 1; i++) {
			Thread.sleep(11);
			System.out.println(Thread.currentThread().getName()+"执行时间是："+new Date().getTime());
		}
		return "执行完成！";
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		FutureTask<String> task = new FutureTask<String>(new MyCallable());
		Thread thread = new Thread(task,"MyCallable");
		thread.start();
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute((Runnable) new MyCallable());
		
	}
}
