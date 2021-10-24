package demo;

public class proxy {
	public static void main(String[] args) {
		you y = new you();
		componey c = new componey(y);
		c.marry();
	}
}

interface marry{
	void marry();
}
class you implements marry{

	@Override
	public void marry() {
		System.out.println("marry..");
	}
	
}
class componey implements marry{
	
	private you y;
	
	public componey() {
	}
	
	public componey(you y){
		this.y=y;
	} 
	
	@Override
	public void marry() {
		y.marry();
	}
}