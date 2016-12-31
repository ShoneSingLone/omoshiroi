package shone.sing.lone.sample.swing.layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridBagLayoutExample extends Frame {
	private static final long serialVersionUID = 1L;

	public GridBagLayoutExample() {
		super("GB记忆编程");
		JLabel receiveJLabel = new JLabel("收件人：");
		JLabel ccJLabel = new JLabel("抄送：");
		JLabel subjectJLabel = new JLabel("主题");
		JTextField receiveField = new JTextField(); // 收件人;
		JTextField ccField = new JTextField(); // 抄送;
		JTextField subjectField = new JTextField(); // 主题；
		TextArea mailArea = new TextArea(8, 40); // 输入邮件文字区域;
		setLayout(new GridBagLayout());
		GridBagConstraints gridBag = new GridBagConstraints();

		gridBag.fill = GridBagConstraints.HORIZONTAL; // 以水平方式填充；
		gridBag.weightx = 0; // 行长不变
		gridBag.weighty = 0; // 行高不变
		addToBag(receiveJLabel, gridBag, 0, 0, 1, 1); // 收信人标签；
		addToBag(ccJLabel, gridBag, 0, 1, 1, 1); // 抄送人标签；
		addToBag(subjectJLabel, gridBag, 0, 2, 1, 1); // 主题标签位置
		gridBag.weightx = 100; // 行自适应缩放
		gridBag.weighty = 0; // 行高不变
		addToBag(receiveField, gridBag, 1, 0, 2, 1);
		addToBag(ccField, gridBag, 1, 1, 2, 1);
		addToBag(subjectField, gridBag, 1, 2, 2, 1);
		JPanel blackPanel = new JPanel();
		blackPanel.setBackground(Color.magenta);
		addToBag(blackPanel, new GridBagConstraints(), 0, 3, 3, 3);
		gridBag.fill = GridBagConstraints.BOTH; // 采用全填充方式布局；
		gridBag.weightx = 100; // 行自适应缩放
		gridBag.weighty = 100; // 列自适应缩放；
		addToBag(mailArea, gridBag, 0, 4, 3, 1); // 占3栏一行

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setSize(300, 300);
		setVisible(true);

	}

	/* 将一个部件按指定大小加入到GridBagLayout布局的指定位置 */
	void addToBag(Component c, GridBagConstraints gdc, int x, int y, int w, int h) {
		gdc.gridx = x;// 指定包含组件的显示区域开始边的单元格，其中行的第一个单元格为 gridx=0。
		gdc.gridy = y;// 指定位于组件显示区域的顶部的单元格，
		gdc.gridwidth = w;// 指定组件显示区域的某一行中的单元格数。
		gdc.gridheight = h;// 指定在组件显示区域的一列中的单元格数。
		add(c, gdc); // 按指定约束加入部件；
	}

	public static void main(String[] args) {
		new GridBagLayoutExample();

	}

}
