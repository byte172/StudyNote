package demo;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
	
	public class AutoDemoFrame extends JFrame {
	
	private JButton btnTest = null;
	private JButton btnDemo = null;
	
	public AutoDemoFrame() {
	super("AutoDemoFrame");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setBounds(60, 60, 500, 360);
	getContentPane().setLayout(null);
	this.btnTest = new JButton("Test");
	this.btnTest.setBounds(30, 30, 120, 36);
	this.btnTest.addMouseListener(
	 new MouseAdapter() {
		 public void mouseClicked(MouseEvent e) {
	 btnTest.setText("Clicked !");
	 }
	 }
);
	this.btnDemo = new JButton("点击一下");
	this.btnDemo.setBounds(350, 250, 76, 23);
	this.btnDemo.addMouseListener(
	new MouseAdapter() {
	public void mouseClicked(MouseEvent e) {
	startDemo();
	}
	});
	getContentPane().add(this.btnTest);
	getContentPane().add(this.btnDemo);
	}
	public void startDemo() {
		Thread rt = new Thread(
	new Runnable() {
	public void run() {
		try {
			Robot rbt = new Robot();
			Point p = btnTest.getLocationOnScreen();
			rbt.delay(100);
			rbt.mouseMove(p.x + 6, p.y + 6);
			rbt.delay(100);
			rbt.mousePress(InputEvent.BUTTON1_MASK);
			rbt.delay(100);
			rbt.mouseRelease(InputEvent.BUTTON1_MASK);
			}catch (AWTException e) {
				e.printStackTrace();
			}
		}
	});
		rt.start();
		}
	
	public static void main(String[] args) {
		AutoDemoFrame adf = new AutoDemoFrame();
		adf.setVisible(true);
		}
	
	}
