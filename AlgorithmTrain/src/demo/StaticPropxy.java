package demo;


public class StaticPropxy {

	public static void main(String[] args) {
		Merry you = new You();
		Merry weddingCompony = new WeddingCompony(you);
		weddingCompony.merry();
	}
	
}

interface Merry {
	public void merry();
}

class You implements Merry{

	@Override
	public void merry() {
		System.out.println("you and me merry..");
	}
	
}

class WeddingCompony implements Merry{

	private Merry you;
	
	public WeddingCompony() {
	}
	public WeddingCompony(Merry you) {
		this.you=you;
	}
	
	private void before() {
		System.out.println("布置猪窝。。。");
	} 
	private void after() {
		System.out.println("闹洞房。。。");
	} 
	
	@Override
	public void merry() {
		before();
		you.merry();
		after();
	}
	
}