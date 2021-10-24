package demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class TestUser {
	//深复制
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		User user = new User("wangjie",18);
		Student student = new Student("zuolina", user);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(student);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		Student student2 = (Student) ois.readObject();
		System.out.println(student.toString());
		System.out.println(student2.toString());
		
	}
	
	
//浅复制
	public static void main1(String[] args) {
		User user = new User("张学友",56);
		User user2 = new User("刘德华",56);
		            
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user2);
		ArrayList<User> userList2 = new ArrayList<User>(userList);
		for (User u : userList2) {
			System.out.println(u.getName()+":"+u.getAge());
		}
		System.out.println(user2.toString());
	}
}
class User implements Serializable{
	private String name;
	private int age;
	
	public User(String name,int age) {
		this.age=age;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}

class Student implements Serializable {
	private String name;
	private User user;
	
	public Student(String name,User u) {
		this.name=name;
		this.user=u;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", user=" + user + "]";
	}
	
	
}

