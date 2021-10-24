package demo;

public class Web12306 implements Runnable {

	private int ticketNums=10;
	
	@Override
	public synchronized void run() {
		while(true) {
			if(ticketNums<=0) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
		}
	}
	
	public static void main(String[] args) {
		Web12306 web12306 = new Web12306();
		
		System.out.println(Thread.currentThread().getName());
		new Thread(web12306,"aa").start();
		new Thread(web12306,"bb").start();
		new Thread(web12306,"cc").start();
	}

}
