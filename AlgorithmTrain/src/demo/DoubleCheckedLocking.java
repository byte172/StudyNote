package demo;

import java.lang.reflect.Constructor;

public class DoubleCheckedLocking {
	private static volatile DoubleCheckedLocking instace;


	private DoubleCheckedLocking(){
		System.out.println("DoubleCheckedLocking..");
	}

	public static DoubleCheckedLocking getInstance() {

		if(null!=instace) {
			return instace;
		}

		synchronized (DoubleCheckedLocking.class) {
			if(null==instace) {
				instace = new DoubleCheckedLocking();
			}
		}

		return instace;

	}
	public static DoubleCheckedLocking getInstance1() {

		if(null!=instace) {
			return instace;
		}

		if(null==instace) {
			instace = new DoubleCheckedLocking();
		}

		return instace;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(()-> {
			System.out.println(DoubleCheckedLocking.getInstance());
		});
		t.start();
		Thread.sleep(1000);
		System.out.println(DoubleCheckedLocking.getInstance());

	}
}


enum Singleton {
	INSTANCE;

	public void doSomething () {
		System.out.println("11");
	}

	public static void main(String[] args) {
		Singleton.INSTANCE.doSomething();
	}
}