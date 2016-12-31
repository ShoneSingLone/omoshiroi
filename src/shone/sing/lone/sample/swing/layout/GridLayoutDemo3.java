package shone.sing.lone.sample.swing.layout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GridLayoutDemo3 extends javax.swing.JFrame {
	public static void main(String[] args) {
		GridLayoutDemo3 f = new GridLayoutDemo3();
	}

	JLabel label1;
	JLabel label2;
	JLabel label4;
	JTextField tf3;
	JPasswordField psf5;
	JRadioButton rb6;
	JRadioButton rb7;

	JButton bt8;

	public GridLayoutDemo3() {
		this.setVisible(true);
		this.setSize(250, 220);
		this.setVisible(true);
		this.setLocation(400, 200);
		label1 = new JLabel("华软BBS快捷登陆");
		label2 = new JLabel("账号：");
		label4 = new JLabel("密码：");
		tf3 = new JTextField();
		psf5 = new JPasswordField();
		rb6 = new JRadioButton("记住密码");
		rb7 = new JRadioButton("自动登陆");
		bt8 = new JButton("登陆");
		// 为指定的 Container 创建 GroupLayout
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		// 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(5);// 添加间隔
		hGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(label4));
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(tf3).addComponent(psf5).addComponent(rb6)
				.addComponent(rb7).addComponent(bt8));
		hGroup.addGap(5);
		
		layout.setHorizontalGroup(hGroup);
		// 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label1));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf3));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label4).addComponent(psf5));
		vGroup.addGroup(layout.createParallelGroup().addComponent(rb6));
		vGroup.addGroup(layout.createParallelGroup().addComponent(rb7));
		vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(bt8));
		vGroup.addGap(10);
		// 设置垂直组
		layout.setVerticalGroup(vGroup);
	}
}
