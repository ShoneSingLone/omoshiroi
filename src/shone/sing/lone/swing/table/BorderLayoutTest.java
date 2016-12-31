package shone.sing.lone.swing.table;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderLayoutTest {
	public static void main(String[] args) {
		JPanel label1, label2, label3, label4, label5,label11, label22, label33, label44;


JFrame mw = new JFrame("我是一个窗口");// 创建一个窗口容器对象
		mw.setSize(250, 200);
		Container con = mw.getContentPane();
		label1 = new JPanel();
		label1.setBackground(Color.red);
		label2 = new JPanel();
		label2.setBackground(Color.black);
		label3 = new JPanel();
		label3.setBackground(Color.blue);
		label4 = new JPanel();
		label4.setBackground(Color.red);
		label11 = new JPanel();
		label11.setBackground(Color.orange);
		label22 = new JPanel();
		label22.setBackground(Color.red);
		label33 = new JPanel();
		label33.setBackground(Color.green);
		label44 = new JPanel();
		label44.setBackground(Color.black);
		label5 = new JPanel();
		label5.setBackground(Color.blue);
		int currentY = 20; // local 递增量
		int countY = 0; // 计数器
		int localY = 0; // 当前控件localY

		// 业务域
		localY = currentY + (countY++) * 30;
		JLabel business = new JLabel("业务域");
		business.setBounds(10, localY, 110, 25);
		label5.add(business);
		String[] comboBoxModel = { "登记", "征收" };
		JComboBox businessComboBox = new JComboBox(comboBoxModel);
		businessComboBox.setBounds(100, localY, 165, 25);
		label5.add(businessComboBox);
		
		con.add(label1, "East");
		con.add(label2, "South");
		con.add(label3, "West");
		con.add(label4, "North");
		con.add(label5, "Center");
		label5.setLayout(null);
		label5.add(label11);
		label5.add(label22);
		label5.add(label33);
		label5.add(label44);
		mw.setVisible(true);
	}
}