package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonEventDemo extends JPanel implements ActionListener {

	protected JButton b1;
	public ButtonEventDemo() {
		ImageIcon ButtonIcon = new ImageIcon("images/green.png");//创建按钮的图标对象
		b1 = new JButton("退出按钮",ButtonIcon);//生成按钮对象
		b1.setMnemonic(KeyEvent.VK_E);
		b1.setToolTipText("这是退出按钮。");
		this.add(b1);
		b1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	public static void createGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame jFrame = new JFrame("按钮测试");
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		
		ButtonEventDemo CPane = new ButtonEventDemo();
		CPane.setOpaque(true);
		jFrame.setContentPane(CPane);
		jFrame.pack();
		jFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createGUI();
				
			}
		});
	}
}
