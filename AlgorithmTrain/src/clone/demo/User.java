package clone.demo;

public class User implements Cloneable {

	private String name;
	private int age;
	private Mark mark;
	
	public User(String name,int age,Mark mark) {
		this.name=name;
		this.age=age;
		this.mark=mark;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", mark=" + mark + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		User user = (User) super.clone();
		user.mark = (Mark) this.mark.clone();
		return user;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
		Mark mark = new Mark(100, 99);
		User user = new User("wangjie", 22, mark);
		
		User userclone = (User) user.clone();
		
		System.out.println(user);
		Thread.sleep(1000);
		System.out.println(userclone);
		
		user.mark.setMath(60);
		
		System.out.println(user);
		Thread.sleep(1000);
		System.out.println(userclone);
	}
}
