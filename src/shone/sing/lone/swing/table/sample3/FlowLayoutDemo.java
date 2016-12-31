package shone.sing.lone.swing.table.sample3;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @功能：演示FlowLayout布局管理器的用法
 * @版本：20150609
 */

public class FlowLayoutDemo extends JFrame {

    FlowLayout contentPanelLayout = new FlowLayout();
    Map<String, Integer> alignmentMap = new HashMap<String, Integer>();
    JPanel configPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JComboBox<String> alignmentComboBox = new JComboBox<String> ();
    JTextField textHgap = new JTextField("10");
    JTextField textVgap = new JTextField("20");
    MyListener myListener = new MyListener();
    
    public FlowLayoutDemo() {
        //init
        alignmentMap.put("LEFT", 0);
        alignmentMap.put("CENTER", 1);
        alignmentMap.put("RIGHT", 2);
        alignmentMap.put("LEADING", 3);
        alignmentMap.put("TRAILING", 4);
        
        
        //设置面板
        configPanel.setLayout(new FlowLayout());
        configPanel.add(new JLabel("对齐方式"));
        
        for (String alignment : alignmentMap.keySet()) {
            alignmentComboBox.addItem(alignment);
        }
        
        configPanel.add(alignmentComboBox);
        configPanel.add(new JLabel("水平间距"));
        
        configPanel.add(textHgap);
        configPanel.add(new JLabel("垂直间距"));
        
        configPanel.add(textVgap);
        
        JButton actionBtn = new JButton("Action!!!");
        actionBtn.addActionListener(myListener);
        configPanel.add(actionBtn);
        
        //展示面板
        
        contentPanel.setLayout(contentPanelLayout);
        contentPanel.add(new JButton("Button 1"));
        contentPanel.add(new JButton("Button 2"));
        contentPanel.add(new JButton("Button 3"));
        contentPanel.add(new JButton("Button 4"));
        
        //主窗体
         setLayout(new BorderLayout());
        add("North",configPanel);
        add("South", contentPanel);
    }

    class MyListener implements ActionListener
    {
         public void actionPerformed(ActionEvent e)
         {
             String alignmentStr = alignmentComboBox.getSelectedItem().toString();
             int alignment = alignmentMap.get(alignmentStr);
             contentPanelLayout.setAlignment(alignment);
             int hgap = Integer.valueOf(textHgap.getText());
                int vgap = Integer.valueOf(textVgap.getText());
               contentPanelLayout.setHgap(hgap);
               contentPanelLayout.setVgap(vgap);
               
               contentPanel.updateUI();
         }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FlowLayoutDemo window = new FlowLayoutDemo();
        window.setTitle("FlowLayoutDemo");
        // 该代码依据放置的组件设定窗口的大小使之正好能容纳你放置的所有组件
        
        window.setPreferredSize(new Dimension(500, 200));
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); // 让窗体居中显示
    }
}