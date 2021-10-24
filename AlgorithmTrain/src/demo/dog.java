package demo;

public class dog {
	private static dog d = new dog();
	private dog(){
	}
	public dog getdog(){
		return d;
	}
	public void eat(){
		System.out.println("asdasd");
	}
}
