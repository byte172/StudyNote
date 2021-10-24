package 面试题;
/**
 * 生成订单编号
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.weaver.NewConstructorTypeMunger;

public class ThreadDemo01 extends Thread {

	private Order order = new Order();
	private static Object obj = new Object();
	
	@Override
	public void run() {
		getNumber();
	}
	
	public void getNumber() {
		synchronized (obj) {
			String orderNumber = order.orderNumber();
			System.out.println(Thread.currentThread().getName()+",生成订单编号："+orderNumber);
		}
	}
	
	public static void main(String[] args) {
		ThreadDemo01 threadDemo01 = new ThreadDemo01();
		for (int i = 0; i < 100; i++) {
			ThreadPoolUtil.submitTask(threadDemo01);
		}
	}
}
class Order{
	private static int count=0;
	public String orderNumber() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return simpleDateFormat.format(new Date())+"-"+ ++count;
	}
}