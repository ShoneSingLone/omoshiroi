package com.leavay.dfc.SubjectView.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Table2 extends JFrame {

	private JPanel paneMain = null; // 主要的JPanel，该JPanel的布局管理将被设置成CardLayout
	private JPanel paneSub = null; // 主要的JPanel，该JPanel的布局管理将被设置成CardLayout
	private JPanel paneBtn = null; // 放按钮的JPanel
	private CardLayout card = null; // CardLayout布局管理器
	private JButton btnSave = null; // 保存
	private JButton btnNext = null; // 下一步
	private JPanel firstPage = null, nextPage = null; // 要切换的三个JPanel
	private Boolean bFlag = false;

	public static void main(String[] args) {
		new Table2();
	}

	public Table2() {
		card = new CardLayout(5, 5);
		paneMain = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
		paneBtn = new JPanel(); // 构造放按钮的JPanel

		btnSave = new JButton("保  存");
		btnNext = new JButton("下一步 >");
		paneBtn.add(btnSave);
		paneBtn.add(btnNext);
		firstPage = new JPanel(null);
		nextPage = new JPanel(null);

		// 运行环境
		JLabel runEvn = new JLabel("运行环境");
		runEvn.setBounds(10, 20, 80, 25);
		firstPage.add(runEvn);

		JTextField runEvnText = new JTextField(20);
		runEvnText.setBounds(100, 20, 165, 25);
		firstPage.add(runEvnText);

		// 字段对照
		JLabel fieldMap = new JLabel("字段对照");
		fieldMap.setBounds(10, 50, 80, 25);
		firstPage.add(fieldMap);

		JTextField fieldMapText = new JTextField(20);
		fieldMapText.setBounds(100, 50, 165, 25);
		firstPage.add(fieldMapText);

		// 自定义配置
		JLabel customConfig = new JLabel("自定义配置");
		customConfig.setBounds(10, 80, 80, 25);
		firstPage.add(customConfig);

		JTextField customConfigText = new JTextField(20);
		customConfigText.setBounds(100, 80, 165, 25);
		firstPage.add(customConfigText);

		paneMain.add(firstPage, "p1");
		paneMain.add(nextPage, "p2");

		btnNext.addActionListener(new ActionListener() { // 下一步的按钮动作
			public void actionPerformed(ActionEvent e) {
				if (bFlag) {
					card.show(paneMain, "p1");
					btnNext.setText("下一步 >");
				} else {
					card.show(paneMain, "p2");
					btnNext.setText("< 上一步");
				}
				bFlag = !bFlag;
			}
		});

		this.getContentPane().add(paneMain);
		this.getContentPane().add(paneBtn, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(335, 627);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
