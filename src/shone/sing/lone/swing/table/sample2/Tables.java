package shone.sing.lone.swing.table.sample2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class Tables implements MouseListener {
	JTable table = new JTable();

	public Tables() {
		JFrame frame = new JFrame("sjh");

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(
				new Object[][] {
						{ new JCheckBox("111"), new JCheckBox("111"), new JCheckBox("111"), new JCheckBox("111"),
								new JCheckBox("111"), new JCheckBox("111") },
						{ new JCheckBox("222"), new JCheckBox("222"), new JCheckBox("222"), new JCheckBox("222"),
								new JCheckBox("222"), new JCheckBox("222") },
						{ new JCheckBox("333"), new JCheckBox("333"), new JCheckBox("333"), new JCheckBox("333"),
								new JCheckBox("333"), new JCheckBox("333") }, },
				new Object[] { "选择", "结果物", "说明", "类型", "发包要求文档", "操作" });

		JTable table = new JTable(dm) {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};
		table.getColumn("选择").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("结果物").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("说明").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("类型").setCellEditor(new CheckBoxEditor(new JCheckBox()));

		table.getColumn("发包要求文档").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("操作").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("选择").setCellRenderer(new CheckBoxRenderer());
		table.getColumn("结果物").setCellRenderer(new CheckBoxRenderer());
		table.getColumn("说明").setCellRenderer(new CheckBoxRenderer());
		table.getColumn("类型").setCellRenderer(new CheckBoxRenderer());
		table.getColumn("发包要求文档").setCellRenderer(new CheckBoxRenderer());
		table.getColumn("操作").setCellRenderer(new CheckBoxRenderer());
		
		
		JScrollPane scrollPane = new JScrollPane(table);

		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int clickColumn = table.columnAtPoint(e.getPoint());
				int selectedRow = table.getSelectedRow(); // 获得选中行索引
				if (selectedRow > -1 && clickColumn == 9) {
					Vector oa = dm.getDataVector();
					System.out.println(table.getValueAt(selectedRow, clickColumn));
				}
				// 获取行数据
				int columnCount = table.getColumnCount();
				for (int i = 0; i <= columnCount - 1; i++) {
					Object oa = dm.getValueAt(selectedRow, i);
				}
			}
		});

		scrollPane.setViewportView(table);
		final JPanel paneMain = new JPanel();
		frame.getContentPane().add(paneMain, BorderLayout.NORTH);

		// 添加一行
		final JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox businessComboBox = new JComboBox();
				businessComboBox.setModel(new DefaultComboBoxModel(new String[] { "登记", "征收" }));
				Object[] rowValues = { new JCheckBox(""), businessComboBox, new JCheckBox("111"), new JCheckBox("111"),
						new JCheckBox("111"), new JCheckBox("111") };
				dm.addRow(rowValues);
			}
		});
		paneMain.add(btnAdd);

		// 删除行
		final JButton btnDel = new JButton("-");
		btnDel.addActionListener(new ActionListener() {// 添加事件
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得选中行的索引
				if (selectedRow != -1) // 存在选中行
				{
					dm.removeRow(selectedRow);
				}
			}
		});
		paneMain.add(btnDel);

		// 确定
		final JButton btnSave = new JButton("确 定");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// card.previous(paneMain);
			}
		});
		paneMain.add(btnSave);
		table.addMouseListener(this);
		JScrollPane src = new JScrollPane(table);
		src.setBounds(0, 0, 400, 200);
		frame.setSize(new Dimension(400, 200));
		frame.add(src);
		frame.setVisible(true);
	}


	public static void main(String args[]) {
		new Tables();
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}