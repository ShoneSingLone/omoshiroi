package shone.sing.lone.sample.swing.components;

/**
  很简单的一个例子，看看是不是你想要的呀。

*/

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CheckBoxDemo extends JFrame{
	
	//声明组件
	private JPanel panel;
	private JCheckBox cb_swin;
	private JCheckBox cb_run;
	private JCheckBox cb_read;
	
	public CheckBoxDemo() {
		// TODO Auto-generated constructor stub
		
		//初始化组件
		panel = new JPanel();
		cb_swin = new JCheckBox("游泳");
		cb_run = new JCheckBox("跑步");
		cb_read = new JCheckBox("读书");
		//添加组件
		panel.add(cb_swin);
		panel.add(cb_run);
		panel.add(cb_read);
		
		this.add(panel);
		//设置窗口的基本属性.
		this.setVisible(true);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

public static void main(String[] args) {
		new CheckBoxDemo();
	}
}