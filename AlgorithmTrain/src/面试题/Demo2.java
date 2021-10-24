package 面试题;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdk.internal.util.xml.impl.Input;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.cglib.transform.impl.AddStaticInitTransformer;
import org.springframework.util.StringUtils;

public class Demo2 {

	/**
	 * 分解质因数
	 * @param num
	 * @return
	 */
	public static ArrayList<Integer> primeFactor(int num){
		ArrayList<Integer> result = new ArrayList<>();
		if(num==1) {
			result.add(1);
		}
		int i =2;
		while(num>1) {
			if(num%i==0) {
				result.add(i);
				num/=i;
				i=2;
			}else {
				i++;
			}
		}
		return result;
	}

	/**
	 * 如果一串字符如"aaaabbc中国1512"要分别统计英文字符的数量，
	 * 中文字符的数量，和数字字符的数量，假设字符中没有中文字符、英文字符、数字字符之外的其他特殊字符
	 * @param str
	 */
	public static void strCount(String str){
		int englishCount=0;
		int chineseCount=0;
		int digitCount=0;

		for (int i = 0; i < str.length(); i++) {
			char ch =  str.charAt(i);
			if(ch>='0' && ch<='9'){
				digitCount++;
			}else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
				englishCount++;
			}else {
				chineseCount++;
			}
		}
		System.out.println("englishCount:"+englishCount+",chineseCount:"+chineseCount+",digitCount:"+digitCount);
	}

	/**
	 * 写一段 Java 程序将 byte 转换为 long
	 * @param input
	 * @param offset
	 * @param littleEndian
	 * @return
	 */
	public static long bytesToLong(byte[] input,int offset,boolean littleEndian){
		// 将byte[] 封装为 ByteBuffer
		ByteBuffer buffer = ByteBuffer.wrap(input,offset,8);
		if(littleEndian){
			buffer.order(ByteOrder.LITTLE_ENDIAN);
		}
		return buffer.getLong();
	}

	/**
	 * 翻转字符串
	 */
	public static void fanzhuanStr(){
		Scanner in = new Scanner(System.in);
		String str=in.nextLine();
		char str1[];
		char[] chrCharArray; //创建一个字符数组chrCharArray
		chrCharArray = str.toCharArray();//将字符串变量转换为字符数组
		int len=str.length();
		str1=new char[len];
		for (int i =0,j=len-1; i < len&&j>=0;j--, i++) {
			str1[i]=chrCharArray[j];
		}
		str= String.valueOf(str1); //将字符数组转换为字符串
		System.out.println(str);
	}

	public static void main6(String[] args) {
//		String a = "a";
//		String b = new String("a");
//		String c = "a" + "c";
//
//		System.out.println(a==b);
//		System.out.println(a==c);
//		System.out.println(a.equals(b));
//		System.out.println(a.equals(c));
//		System.out.println(a.intern() == b.intern());

		Integer a = new Integer(1);
		Integer b = new Integer(1);
		System.out.println(a==b);
	}



	public static void main4(String[] args) {
		//JAVA笔试之打印昨天的当前时刻
//		Calendar cal=Calendar.getInstance();
//		cal.add(Calendar.DATE, -2);
//		System.out.println(cal.getTime());
//		String a = "helloworld";
//		String b = "hello" + "world";
//		System.out.println(a.equals(b));
//
//		Date date=new Date();
//		System.out.printf("%tD%n",date);

		Integer n1 = new Integer(23);
		Integer n2 = new Integer(23);
		System.out.print(n1 == n2);
		System.out.print(",");
		System.out.println(n1.equals(n2));

	}

	//得到当前时间
	public static void main2(String[] args) {
//		得到long类型当前时间

		long l = System.currentTimeMillis();

//new日期对

		Date date = new Date(l);

//转换提日期输出格式

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM- dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
	}

	public static void main1(String[] args) {
		//取得从1970年1月1日0时0分0秒到现在的毫秒数
		System.out.println(Calendar.getInstance().getTimeInMillis());
	}

	public static void main(String[] args){
//		fanzhuanStr();
//		String input="net.gdface.facelog.dborm.person.FlPersonBeanBase";
//		byte[] md5 = input.getBytes();
////
//		long ln1 = bytesToLong(md5,0, false);
//		long ln2 = bytesToLong(md5,8, false);
//		System.out.printf("ln1=0x%x ln2=0x%x,ByteBuffer\n", ln1,ln2);
//		strCount("asdasf埃菲尔32432");
		System.out.println(primeFactor(7));
		
		
//		String str = "zhangsan";
//		change(str);
//		System.out.println(str);

	}
}
