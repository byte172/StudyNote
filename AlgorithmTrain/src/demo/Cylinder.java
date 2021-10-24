package demo;

public class Cylinder extends Circle {

	private double height;
	
	public Cylinder(double r,double h) {
		super(r);
		this.height=h;
	}
	
	public double getVol() {
		return getArea()*height;
	}
	
	public void dispVol() {
		System.out.println("圆柱体体积="+getVol());
	}
	
}
