package demo;

public class demo2 {
	
	public static void main(String[] args) {
		System.out.println(3+5*(6+1)-(7*(9*(8+2))));
	}
	
	public static void main1(String[] args) {
		w w1 = new w();
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w1);
		Thread t3 = new Thread(w1);
		t1.start();
		t2.start();
		t3.start();
	}
}

class w implements Runnable {
	private int num=50;
	private boolean flag = true;
	@Override
	public void run() {
		while(flag){
			test1();
		}
	}
	public synchronized void  test1(){
		if(num<=0){
			this.flag=false;
			return ;
		}
		try {
			Thread.sleep(123);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("wo..."+num--);
	}
	
}