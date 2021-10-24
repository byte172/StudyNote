package 面试题;
/**
 * 如果方法没有在规定的时间内执行完，将抛出异常
 */
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
	public static void main(String[] args) {
		final ExecutorService es = Executors.newFixedThreadPool(1);

		Callable<String> call = new Callable<String>() {
			@Override
			public String call() throws Exception {
				//开始执行耗时操作
				Thread.sleep(1000*5);
				return "线程执行完成。";
			}
		};

		try {
			Future<String> future = es.submit(call);
			String obj = future.get(100000*1,TimeUnit.MILLISECONDS);//任务处理超时时间设为1秒
			System.out.println("处理完成");
		} catch (Exception e) {
			System.out.println("处理超时啦。。。");
			e.printStackTrace();
		}
		es.shutdown();
	}
}
