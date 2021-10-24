package 序列化深克隆;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {

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
	
	
	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException, IOException, ClassNotFoundException {
		Mark mark = new Mark(100, 99);
		User user = new User("wangjie", 22, mark);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(user);//序列化
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		User userclone = (User) ois.readObject();//反序列化
		
		System.out.println(user);
		Thread.sleep(1000);
		System.out.println(userclone);
		
		user.mark.setMath(60);
		
		System.out.println(user);
		Thread.sleep(1000);
		System.out.println(userclone);
	}
}
