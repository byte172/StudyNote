package demo;


public class app {
	public static void main(String[] args) {
		wangjie wj = new wangjie();
		Thread proxy = new Thread(wj);
		proxy.start();


	}
}
