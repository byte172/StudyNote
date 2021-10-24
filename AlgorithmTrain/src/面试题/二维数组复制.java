package 面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 二维数组复制 {
	public static void main(String[] args) {
//		int[][] a= {{1,2},{2,3,4},{5,6,7,8}};
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}
		
		int[][] a = {{1,2,2},{4,5,6}};
		int[][] b = {{1,2,2},{6,8,9}};
		
		int[][] unite = unite(a,b);
		for (int i = 0; i < unite.length; i++) {
			for (int j = 0; j < unite[i].length; j++) {
				System.out.print(unite[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int[][] unite(int[][] os1, int[][] os2) {
		List<int[]> list = new ArrayList<int[]>(Arrays.<int[]>asList(os1));
		list.addAll(Arrays.asList(os2));
		return list.toArray(os1);
	}
}
