package 数据结构;

import java.lang.reflect.Member;
import java.util.*;

public class demo {
	/**
	 * 冒泡排序最终版
	 * @param arr
	 */
	public static void sortFinal(int[] arr){
		boolean sorted = true;
		int len = arr.length;
		for (int j = 0; j < len-1; j++) {//趟数
			sorted = true;//假定有序
			for (int i = 0; i < len-1-j; i++) {//次数
				if(arr[i]>arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					sorted = false;//假定失败
				}
				System.out.println(Arrays.toString(arr));
			}
			if(sorted){//减少趟数
				break;
			}
		}
	}
	
	/**
     * 冒泡排序：
     * 原理：比较两个相邻的元素，将值大的元素放在后面
     * <p>
     * 第一层的循环是需要遍历的次数
     * 第二层的循环是 每次都是重新两两比较（除去之前得到的最大值），得到值大的元素放在后面
     */
	public<T extends Comparable<T>> void inintMaoPao1(T[] list) {
		boolean swapped = true;
		for (int i = 1,len=list.length; i <len&& swapped; ++i) {
			swapped = false;
			for (int j = 0; j <len-i; ++j) {
				if(list[j].compareTo(list[j+1])>0) {
					T temp = list[j];
					list[j]=list[j+1];
					list[j+1] = temp;
					swapped = true;
				}
			}
		}
		System.out.println(Arrays.toString(list));
	}
	/**
     * 冒泡排序：
     * 原理：比较两个相邻的元素，将值大的元素放在后面
     * <p>
     * 第一层的循环是需要遍历的次数
     * 第二层的循环是 每次都是重新两两比较（除去之前得到的最大值），得到值大的元素放在后面
     */
	public static void inintMaoPao(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	/**
     * 选择排序：s
     * 原理：每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
     * <p>
     * 第一层：从第一个元素开始比较后面的元素。
     * 第二层：得到的值小的元素和该位置交换，下次循环arr[i] 就是那个较小的值，对以后的元素比较，一次类推
     */
	public void chooseSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if(arr[i]>arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j]  =temp;
				}  
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	/**
     * 简单插入排序：
     * 原理：每一个元素都要和之前的已经排好的元素做比较
     */
	public void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i]<arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
	/**
     * 面试中较为常见的算法之一就是快速排序，
     * 快速排序在实际排序应用中也是最好的选择，因为它的平均性能非常好，它的期望复杂度为nlgn，
     * 另外，它还是一种稳定的排序方法。快速排序利用分治思想，将待排序数组分成左右两个部分，然后对其分别递归调用快速排序算法。
     * <p>
     * 采用分治法的思想：
     * 首先设置一个轴值pivot，然后以这个轴值为划分基准将待排序序列分成比pivot大和比pivot小的两部分，
     * 接下来对划分完的子序列进行快排直到子序列为一个元素为止
     */

	public static void quickSort(int[] arr,int left,int right){
		int i,j,base,temp;
		if(left>right) {
			return ;
		}
		i=left;
		j=right;
		base=arr[left];
		while(i<j) {
			while(base<=arr[j] && i<j) {
				j--;
			}
			while(base>=arr[i] && i<j) {
				i++;
			}
			if(i<j) {
				temp = arr[j];
				arr[j]=arr[i];
				arr[i]=temp;
			}
		}
		//把相遇位置的数和基准数调换位置
		arr[left]=arr[i];
		arr[i]=base;
		quickSort(arr, left, j-1);
		quickSort(arr, j+1, right);
		System.out.println(Arrays.toString(arr));

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
	 *
	 * @param array 需要查找的数组
	 * @param from 起始下标
	 * @param to 终止下标
	 * @param key 需要查找的关键字
	 * @param <E>
	 * @return
	 */
	public static <E extends Comparable<E>> int binarySearch2(E[] array,int from ,int to, E key){
		if(from <0 || to < 0){
			throw new IllegalArgumentException("params from & length must larger than 0.");
		}

		if(from <= to){
			int middle = (from >>>1) + (to >>>1);//右移即除2
			E temp = array[middle];

			if(temp.compareTo(key) > 0){
				to = middle - 1;
			}else if (temp.compareTo(key) < 0){
				from = middle +1;
			}else {
				return middle;
			}
		}
		return binarySearch2(array,from,to,key);
	}

	/**
	 * 归并排序
	 * @param a
	 * @param start
	 * @param end
	 */
	public static void mergeSort(int[] a,int start,int end){
		if(start<end){//当子序列中只有一个元素时结束递归
			int mid = (start+end)/2;//划分子序列
			mergeSort(a, start, mid);//对左侧子序列进行递归排序
			mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
			merge(a, start, mid, end);//合并
		}
	}

	public static void merge(int[] a,int left,int mid,int right){
		int[] tmp = new int[a.length];//辅助数组
		int p1=left, p2=mid+1, k=left;//p1、p2是检测指针，k是存放指针

		while(p1<=mid && p2<=right){
			if(a[p1]<=a[p2]) {
				tmp[k++] = a[p1++];
			} else {
				tmp[k++] = a[p2++];
			}
		}

		while(p1<=mid) {tmp[k++] = a[p1++];}//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
		while(p2<=right) {tmp[k++] = a[p2++];}//同上

		for (int i = left; i <= right; i++) {
			a[i] = tmp[i];
		}
	}

	/**
	 * 直接选择排序
	 * @param array
	 */
	public static void sort(int []array) {
		int index;
		for (int i = 1; i < array.length; i++) {
			index=0;// 默认取无序区第一个元素为最小值
			for (int j = 0; j < array.length-i; j++) {// 循环遍历无序区，查找到无序区最小元素
				if(array[j]>array[index]) {
					index=j;
				}
			}
			// 把无序区最小元素,插入到有序区末尾
			int temp=array[array.length-i];
			array[array.length-i]=array[index];
			array[index]=temp;
		}
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
	}

	/**
	 * 希尔排序
	 * @param ins
	 * @return
	 */
	public static int[] xeSort(int[] ins){
		int n = ins.length;
		int gap = n/2;
		while(gap > 0){
			for(int j = gap; j < n; j++){
				int i=j;
				while(i >= gap && ins[i-gap] > ins[i]){
					int temp = ins[i-gap]+ins[i];
					ins[i-gap] = temp-ins[i-gap];
					ins[i] = temp-ins[i-gap];
					i -= gap;
				}
			}
			gap = gap/2;
		}
		return ins;
	}

	public static void main(String[] args) {
		int[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };

//		inintMaoPao(arr);
		int a = arr.length;
		mergeSort(arr, 0, a-1);
//		int[] arr1 = xeSort(arr);
//
//		System.out.println("排好序的数组：");
//		for (int e : arr1) {
//			System.out.print(e+" ");
//		}


//		Integer[] arr = {4,8,5,2,9,47};
//		int a=9;
//		int i = binarySearch2(arr, 0, arr.length - 1, a);
//		demo d = new demo();
//		d.quickSort(arr,0,arr.length-1);
//		d.chooseSort(arr);
//		d.insertSort(arr);
	}

	public static void main1(String[] args) {
		int arr[]=new int[] {23,4,54,18,42};
//		SelectSort selectSort=new SelectSort();
//		sort(arr);

//		sortFinal(arr);
		quickSort(arr,0, 4);
	}


	
}
