package 面试题;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apple.laf.JRSUIConstants;
import org.omg.CORBA.INITIALIZE;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Demo {


	private static int count=0;
	//二分查找插入
	public static void insert(int value) {
		int[] a = new int[10];

		if(count==0) {
			a[0] = value;
			count++;
			return;
		}
		
		int low = 0;
		int high = count-1;
		int mid;
		
		while(true) {
			mid = (low+high)/2;
			if(low>high) {
				break;
			}
			if(a[mid] == value) {
				break;
			}else if (a[mid]<value) {
				if(mid==count-1) {
					mid = mid+1;
					break;
				}else if (a[mid+1]>=value) {
					mid=mid+1;
					break;
				}else {
					low=mid+1;
				}
			}else {
				if(mid==0) {
					break;
				}else if (a[mid-1]<=value) {
					break;
				}else {
					high=mid-1;
				}
			}
		}
		
		for(int k=count;k>mid;k--) {
			a[k]=a[k-1];
		}
		a[mid] = value;
		count++; 
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	//一列数的规则如下: 1、1、2、3、5、8、13、21、34… 求第30位数是多少， 用递归算法实现
	public static int foo(int i) {
		if(i<=0) {
			return 0;
		}else if (i>0&&i<2) {
			return 1;
		}
		return foo(i-1)+foo(i-2);
	}
	
	/**
	 * 翻转整数
	 * @param x
	 * @return
	 */
	public  static int reverse(int x) {
		int result=0;
		int num =0;
		while(x!=0) {
			num = x%10;
			x /= 10;
			if(result>Integer.MAX_VALUE/10 || result==Integer.MAX_VALUE/10 && num>7) {
				return 0;
			}
			if(result<Integer.MIN_VALUE/10 || result==Integer.MIN_VALUE/10 && num<-8) {
				return 0;
			}
			result = result*10+num;
		}
		return result;
	} 
	
	/**
	 * 使用集合实现数组翻转
	 */
	public static void reverseArray(String[] array) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[array.length-i-1]);
		}
		array  = list.toArray(array);
	}
	
	/**
	 * 查找一个字符串在文件中出现的次数
	 * @param fileName
	 * @param string
	 * @throws Exception
	 */
	public static void findStringCounts(String fileName,String string) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str==null) {
				break;
			}
			sb.append(str);
		}


		Pattern pattern = Pattern.compile(string);
		Matcher matcher = pattern.matcher(sb);
		int count = 0;
		while(matcher.find()) {
			count++;
			System.out.println(matcher.toString());
		}
		System.out.println(count);
//		BufferedWriter bw = new BufferedWriter(new FileWriter("src/a.txt"));
//		bw.write(sb.toString(), 0, sb.length()-1);
//		bw.close();
		br.close();
	}
	
	/**
	 * 使用循环方式实现二分查找
	 * @param x
	 * @param key
	 * @param comp
	 * @return
	 */
	public static  <T> int binarySearch(T[] x,T key,Comparator<T> comp) {
		int low = 0;
		int high = x.length-1;
		while(low<=high) {
			int mid = (low+high)>>>1;
			int cmp = comp.compare(x[mid],key);
			if(cmp<0) {
				low=mid+1;
			}
			else if (cmp>0){
				high=mid-1;
			}
			else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 查找字符串中是否存在这个字符串
	 * @param text
	 */
	public static void findText(String text) {
		String string = "asdasf";
		if((string.indexOf(text))!=-1) {
			System.out.println("存在字符串text");
		}
		System.out.println(string.contains(text));
	}
	/**
	 * 如何判断一个字符串是否为合法的IP地址
	 * @param ip
	 * @return
	 */
	public static boolean isIP(String ip) {
		if(ip==null||ip=="") {
			return false;
		}
		String string1 = ip.substring(0,ip.indexOf('.'));
		String result1 = ip.substring(ip.indexOf('.')+1);
		String string2 = result1.substring(0,result1.indexOf('.'));
		String result2 = result1.substring(result1.indexOf('.')+1);
		String string3 = result2.substring(0,result2.indexOf('.'));
		String result3 = result2.substring(result2.indexOf('.')+1);
		
		System.out.println(string1 +" "+string2+" "+string3+" "+result3);
		
		int ip1 = Integer.parseInt(string1);
		int ip2 = Integer.parseInt(string2);
		int ip3 = Integer.parseInt(string3);
		int ip4 = Integer.parseInt(result3);
		
		if(ip1<0||ip1>255) {
			return false;
		}
		if(ip2<0||ip2>255) {
			return false;
		}
		if(ip3<0||ip3>255) {
			return false;
		}
		if(ip4<0||ip4>255) {
			return false;
		}
		
		return true;
	}
	/**
	 * 找到字符串中字母只出现一次的字母
	 * @param str
	 * @return
	 */
	public static char findStringFirst(String str) {
		if(str == null || str.trim().length()==0){
			return '0';
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		str = str.toLowerCase(); //防止出现大小写混乱
		int len = str.length();
		int count = 0;
		for(int i = 0; i < len; i++){
			if(map.containsKey(str.charAt(i))){
				count = map.get(str.charAt(i));
				map.put(str.charAt(i), ++count);
			}else{
				map.put(str.charAt(i), 1);
			}
		}
		for(int i = 0; i < len; i++){
			if(map.get(str.charAt(i)) == 1){
				System.out.println(str.charAt(i));
			}
		}
		return '0';
	}

	/**
	 * 将一个整数数组的顺序打乱
	 * @param arr
	 */
	public static void shuffle(int[] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		Collections.shuffle(list);
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next().toString()+",");
		}
	}
	/**
	 * 找数组中数字出现的次数
	 * @param arr
	 */
	public static void findRepeatCounts(int[] arr) {
		Arrays.sort(arr);
		int count = 0;
		int temp = arr[0];

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; ++i) {
			if(temp!=arr[i]) {
				temp = arr[i];
				count = 1;
			}else {
				count++;
			}
			map.put(arr[i], count);
		}
		for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}


	public static void main3(String[] args) {
		int count=0;
		int sum=0;
		for(int i=1;i<=6;i++){
			for(int j=1;j<=6;j++){
				for(int m=1;m<=6;m++){
					if((i+j+m)%2 ==0){
						count += 1;
					}
					if((i+j+m)<6){
						sum += 1;
					}
				}
			}
		}
		System.out.println("偶数："+count+",小于6:"+sum);
	}

	public static void main2(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1;i<=50;i++){
			list.add(i);
		}
		int count = 0;
		for (int i = 0; i < 48; i++) {
			int m = list.get(i);
			int n = list.get(i+1);
			int l = list.get(i+2);
			if((m+n+l)%3 == 0){
				System.out.println(m+"-"+n+"-"+l);
				count++;
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) throws Exception {

//		findText("as");//
//		findStringCounts("src/test.txt", "5");

//		insert(1);
//		insert(2);
//
//		String[] strings = {"a","b","d"};
//		reverseArray(strings);
//		for (int i = 0; i < strings.length; i++) {
//			System.out.print(strings[i]+" ");
//		}
//		System.out.println(reverse(52));

//		int foo = foo(2);
//		System.out.println(foo);
		
//		String string = "sadafewasdkla";
//		findStringFirst(string);
//		System.out.println(string.charAt(0)-'a');
		
//		int[] arr = {1,2,3,4 };
//		shuffle(arr);
		
//		int[] arr = {1,5,14,4,5,5,2};
//		findRepeatCounts(arr);
	}
}
