package demo;

public class SheelSort {
	
	public static void main(String[] args) {
		int[] a = {2,7,2,1,8,235,65};
		sheelSort(a);
		for (int i=0;i<a.length;i++) {
			System.out.print(a[i]+",");
		}
	}
	
	
	public static void sheelSort(int a[]) {
		int d = a.length;
		while(d!=0) {
			d=d/2;
			for(int x=0;x<d;x++) {
				for(int i=x+d;i<a.length;i+=d) {
					int j=i-d;
					int temp=a[i];
					for(;j>=0&&temp<a[j];j-=d) {
						a[j+d]=a[j];
					}
					a[j+d]=temp;
				}
			}
		}
	}
}
