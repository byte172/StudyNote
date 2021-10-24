package demo;

import java.io.ObjectInputStream.GetField;

public class Circle {
	private double radius;
	
//	public Circle() {
//		radius=0.0;
//	}
	
	public Circle(double r) {
		this.radius=r;
	}
	
	public double getPerimeter() {
		return 2*Math.PI*radius;
	} 
	public double getArea() {
		return Math.PI*radius*radius;
	}
	
	public void disp() {
		System.out.println("圆半径="+radius);
		System.out.println("圆周长="+getPerimeter());
		System.out.println("圆面积="+getArea());
	}

}
