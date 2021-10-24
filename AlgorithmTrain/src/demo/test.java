package demo;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;import javax.print.DocFlavor.STRING;

public  class test {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		int[] arr = {1,2,3,4};
		
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		System.out.print(sb.toString());
		
		
		char a = 'a';
		int b = a;
		System.out.println(b);
	}
	
	public static void main15(String[] args) {
		AtomicInteger integer = new AtomicInteger();
		integer.compareAndSet(0, 2);
		System.out.println(integer);
		ArrayList<Integer> list = new ArrayList<>();
		Integer i=1;
		list.add(i);
		Iterator<Integer> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		Object[] arr = list.toArray();
		System.out.println(arr[0]);
		List<Object> list2 = Arrays.asList(arr);
		System.out.println(list2.get(0));
	}
	
	public void init1() throws Throwable {
		System.out.println("sada");
	}
	
	public static void main14(String[] args) throws Exception {
		Class class1 = Class.forName("demo.test");
		Object instance = class1.newInstance();
		Method method = class1.getDeclaredMethod("init1");
		method.invoke(instance);
		String i = "1";
		System.out.println(Integer.parseInt(i));
	}
	
	public static void main13(String[] args) throws Exception {
		String num = "wangjie";
		int[] a= {1,2,3};
		System.out.println(num.indexOf("a"));
		System.out.println(Arrays.binarySearch(a, 2));
		Vector<String> vector = new Vector<>();
		vector.add("1");
		System.out.println(vector.elementAt(0));
		
		Method method = num.getClass().getMethod("toUpperCase");
		System.out.println(method.invoke(num));
		
		
	}
	
	public static void main12(String[] args) throws Exception {
		byte[] a= {1,2,3};
		String string = new String(a);
		System.out.println(string);
		System.out.println(string.getBytes("utf-8"));
	}
	
	public static void main11(String[] args) {
		short s1 = 1;
		s1+=1;
		System.out.println(Math.round(3.4));
		System.out.println(Math.sqrt(4));
		System.out.println(4>>>2);
		System.out.println(8>>3);
		System.out.println(2<<3);
		
		int[][] a= {{1,2},{3,4}};
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		System.out.println(a.length);
	}
	
	
	public static void main10(String[] args) {
		String a="123";
		String b = new String("123");
		System.out.println(a==b);
		System.out.println(a.equals(b));
		
	}
	
	public static void main9(String[] args) {
		ThreadDemo t1 = new ThreadDemo("t1");
		ThreadDemo t2 = new ThreadDemo("t2");
		ThreadDemo t3 = new ThreadDemo("t3");
		
		t1.start();
		try {
			t1.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		t3.start();
	}
	
	public static void main8(String[] args) {
		int[] data= {123,1234,45,546,745};
		int[] t = new int[200];
		
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("dest.txt"));
			for(int i=0;i<data.length;i++) {
				dos.writeInt(data[i]);//写入
			}
			dos.close();
			DataInputStream dis = new DataInputStream(new FileInputStream("dest.txt"));
			for(int i=0;i<data.length;i++) {
				t[i]=dis.readInt();
			}
			for(int i=data.length-1;i>=0;i--) {
				System.out.print(" "+t[i]);
			}
			dis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main7(String[] args) {
		byte[] buf= new byte[2056];
		try {
			FileInputStream fileInputStream = new FileInputStream("E:/Users/chdn/workspace/11.3/src/Object/Dog.java");
			int bytes = fileInputStream.read(buf,0,2056);
			String str = new String(buf,0,bytes);
			System.out.println(str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main6(String[] args) {
		String s1=null,s2=null,ss,si,sf;
		int i1,i2;
		float f1,f2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("输入第一个字符串：");
			s1=br.readLine();
			System.out.println("输入第二个字符串：");
			s2=br.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		i1=Integer.parseInt(s1);
		i2=Integer.parseInt(s2);
		f1=Float.parseFloat(s1);
		f2=Float.parseFloat(s2);
		
		ss=strAdd(s1, s2);
//		si=strAdd(i1, i2);
		
		System.out.println("两个字符串相加的结果："+ss);
//		System.out.println("两个字符串转换为整数相加的结果："+si);
	}
	static String strAdd(String str1,String str2) {
		return str1+str2;
	}
	static String strAdd(int i1,int i2) {
		return String.valueOf(i1+i2);
	}
	
	public static void main5(String[] args) {
		Circle circle = new Circle(5.0);
		circle.disp();
		Cylinder cylinder = new Cylinder(5.0, 10.0);
		cylinder.dispVol();
	}
	
	public static void main4(String[] args) {
		Tourist tourist = new Tourist();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入年龄：");
		tourist.setAge(scanner.nextInt());
		tourist.ticket();
		
	}
	
	public static void main3(String[] args) {
		int[] arr= {2,34,6,234,4};
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length-1;j++) {
				if(arr[j]>arr[j+1]) {
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		showArr(arr);
	}
	
	
	private static void showArr(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}


	public static void main2(String[] args) {

		int sum=0;
		for(int i=1;i<=10;i++) {
			int mul=1;
			for(int j=1;j<=i;j++) {
				mul=mul*j;
			}
			sum=mul+sum;
		}
		System.out.println("1!=2!+++10!="+sum);
	}
	
	
	public static void main1(String[] args) {

		System.out.println("请输入年份：");
		Scanner scanner = new Scanner(System.in);
		int year = scanner.nextInt();
		if((year%4==0 && year%100!=0)||(year%400==0)){
			System.out.println(year+"年是闰年");
		}else {
			System.out.println(year+"年不是闰年");
		}
	}
}
