package shone.sing.lone.swing.table.sample5;

/*
 *   二级联动案例
 *
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.omg.PortableServer.POAPackage.WrongAdapter;

public class LinkageDemo {

	public static void main(String[] args) {
		// 创建键值对对象
		HashMap<String, ArrayList<String>> city = new HashMap<String, ArrayList<String>>();
		// 广东省
		ArrayList<String> guangdong = new ArrayList<String>();
		guangdong.add("广州市");
		guangdong.add("深圳市");
		guangdong.add("东莞市");
		guangdong.add("韶关市");

		// 湖南省
		ArrayList<String> hunan = new ArrayList<String>();
		hunan.add("长沙市");
		hunan.add("衡阳市");
		hunan.add("张家界");

		// 将数据添加到map集合中
		city.put("广东省", guangdong);
		city.put("湖南省", hunan);

		// 界面
		JFrame jf = new JFrame("二级联动案例");
		// 设置窗口位置及大小
		jf.setBounds(400, 500, 500, 400);
		// 更改窗口布局
		jf.setLayout(new FlowLayout());

		// 设置窗口关闭事件
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// 创建下拉框对象
		JComboBox jb1 = new JComboBox();
		JComboBox jb2 = new JComboBox();

		Set<String> cityArray = city.keySet();
		for (String citys : cityArray) {

			jb1.addItem(citys);

		}

		// 添加下拉框事件监听
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> arrays = city.get(jb1.getSelectedItem());

				jb2.removeAllItems();
				for (String array : arrays) {

					jb2.addItem(array);
				}

			}
		});

		// 将组合框添加到窗口
		jf.add(jb1);
		jf.add(jb2);

		// 设置窗口可视
		jf.setVisible(true);

	}

}
