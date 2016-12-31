package shone.sing.lone.swing.table.sample;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CheckedBoxTabel extends JFrame {
	private JTable table;
	private JScrollPane sPane;
	private DefaultTableModel model;
	private JButton button, button2;
	private JPanel pane;

	public CheckedBoxTabel() {
		// TODO Auto-generated constructor stub
		// 窗口设置，不用管
		this.setSize(400, 400);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize;
		scrnsize = toolkit.getScreenSize();
		setLocation(scrnsize.width / 2 - getWidth() / 2, scrnsize.height / 2 - getHeight() / 2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// table的model设置
		model = new DefaultTableModel(new Object[][] {}, new String[] { "论文ID", "论文标题", "是否提交", "论文负责人" });
		// table加载model
		table = new JTable(model);
		// 将第三列的显示设为checkbox类型，楼主想设置哪列自己决定
		// 但是记得该列的值是Boolean型的
		TableColumn tc = table.getColumnModel().getColumn(2);
		table.getColumnModel().getColumn(2).setCellEditor(table.getDefaultEditor(Boolean.class));
		table.getColumnModel().getColumn(2).setCellRenderer(table.getDefaultRenderer(Boolean.class));
		sPane = new JScrollPane();
		sPane.setViewportView(table);
		// 添加增添一行按钮的事件处理
		button = new JButton("加一行");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model = (DefaultTableModel) table.getModel();
				Object[] data = new Object[4];
				data[0] = "1";
				data[1] = "论文";
				data[2] = new Boolean(false);
				data[3] = "张三";
				model.addRow(data);
				/*
				 * //或者也可以用 model.addRow(new Object[]{"1","论文",new
				 * Boolean(false),"张三"});
				 */
				table.setModel(model);
			}
		});
		// 添加显示所选行的按钮的事件处理
		button2 = new JButton("显示所选行");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model = (DefaultTableModel) table.getModel();
				int count = model.getRowCount();
				for (int i = 0; i < count; i++) {
					if (model.getValueAt(i, 2).equals(true)) {
						// 在这里添加你要处理行的方法
						System.out.println("第" + (i + 1) + "行被选中");
					}
				}
			}
		});
		
		pane = new JPanel();
		pane.setLayout(new FlowLayout());
		pane.add(button);
		pane.add(button2);
		this.add(sPane, BorderLayout.CENTER);
		this.add(pane, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new CheckedBoxTabel().setVisible(true);
	}
}
