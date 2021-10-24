package 面试题;
/*
 * final修饰局部变量的问题
		基本类型：基本类型的值不能发生改变。
		引用类型：引用类型的地址值不能发生改变，但是，该对象的堆内存的值是可以改变的。
 */

import javax.net.ssl.SSLContext;

public class FinalTest {
	public static void main(String[] args) {
//		int x=10;
//		x=100;
//		System.out.println(x);
//		final int y = 10;
//		y=100;基本类型的值不能发生改变
//		System.out.println(y);
		
		//局部变量是引用数据类型
//		Student student = new Student();
//		student.age = 100;
//		System.out.println(student.age);
//		
		final Student student2 = new Student();
		student2.age=100;
		System.out.println(student2.age);
		//重新分配内存空间
		//无法为最终变量ss分配值
//		student2 = new Student();
	}
}
class Student{
	int age=10;
}