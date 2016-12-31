package shone.sing.lone.sample.swing.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Swing8{
/**
 * 一个非常简单的JavaBean，POJO。当作数据Model
 */
public class Computer{
private boolean huihuai;	// 是否坏了
private String pinpai;	// 品牌
private String huihuaiyuanyin;	// 毁坏原因
private String yonghu;	// 谁的电脑
public boolean isHuihuai() {
return huihuai;
}
public void setHuihuai(boolean huihuai) {
this.huihuai = huihuai;
}
public String getPinpai() {
return pinpai;
}
public void setPinpai(String pinpai) {
this.pinpai = pinpai;
}
public String getHuihuaiyuanyin() {
return huihuaiyuanyin;
}
public void setHuihuaiyuanyin(String huihuaiyuanyin) {
this.huihuaiyuanyin = huihuaiyuanyin;
}
public String getYonghu() {
return yonghu;
}
public void setYonghu(String yonghu) {
this.yonghu = yonghu;
}
public String toString(){
return "Computer当前属性值是：\n" +
"yonghu:  " + yonghu + "\n" + 
"pinpai:  " + pinpai + "\n" + 
"huihuai:  " + huihuai + "\n" + 
"huihuaiyuanyin:  " + huihuaiyuanyin;
}
}
public Swing8(){
// 创建一个Frame
final JFrame frame = new JFrame("BeansBinding 示例");
frame.setBounds(100, 100, 500, 380);
final JPanel panel = new JPanel();
frame.getContentPane().add(panel, BorderLayout.CENTER);
panel.setLayout(new GridLayout(4, 2, 2, 2));
JLabel label = new JLabel("谁的电脑：");
panel.add(label);
final JTextField ut;	// 用户
final JTextField bt;	// 品牌
final JCheckBox cb;	// 是否坏了
final JTextField rt;	// 毁坏原因
final Computer c = new Computer(); // 创建一个Model
ut = new JTextField();
panel.add(ut);
ut.setColumns(10);
JLabel label_1 = new JLabel("电脑品牌:");
panel.add(label_1);
bt = new JTextField();
panel.add(bt);
bt.setColumns(10);
cb = new JCheckBox("是否坏了");
panel.add(cb);
JLabel label_2 = new JLabel("");
panel.add(label_2);
JLabel label_3 = new JLabel("毁坏原因：");
panel.add(label_3);
rt = new JTextField();
panel.add(rt);
rt.setColumns(10);
// 绑定属性
BindingGroup bg = new BindingGroup();	// 绑定组，绑定的内容都要在绑定组中。
// 使用Bindings.createAutoBinding静态方法创建一个绑定实例。
// UpdateStrategy.READ_WRITE 绑定策略，读写
// ut 绑定源对象
// BeanProperty.create("text") 绑定源的属性
// c 绑定目标对象
// BeanProperty.create("yonghu") 绑定目标属性
// 将ut的text属性绑定到c的yonghu属性上。
Binding userBind = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, ut, BeanProperty.create("text"), c, BeanProperty.create("yonghu"));
// 将绑定实例放入绑定组。
bg.addBinding(userBind);
// 下面的类似，不再详细写
// ut.text -> c.pinpai
bg.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, bt, BeanProperty.create("text"), c, BeanProperty.create("pinpai")));
// rt.text -> c.huihuaiyuanyin
bg.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, rt, BeanProperty.create("text"), c, BeanProperty.create("huihuaiyuanyin")));
// cb.selected -> c.huihuai
bg.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, cb, BeanProperty.create("selected"), c, BeanProperty.create("huihuai")));
// 若程序有需求，只有当勾选了“是否坏了”CheckBox，才能输入"毁坏原因"，怎么办？
// 好办，将cb.selected绑定到rt的enabled属性上。
bg.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, cb, BeanProperty.create("selected"), rt, BeanProperty.create("enabled")));
// 重要的一句话，让版定生效
bg.bind();
//好吧，我们再加一个按钮，看看效果如何。
JButton button = new JButton("查看绑定效果");
button.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
JOptionPane.showMessageDialog(frame, c.toString(), "Model属性值列表", JOptionPane.INFORMATION_MESSAGE);
}
});
frame.getContentPane().add(button, BorderLayout.SOUTH);
frame.setVisible(true);
}
public static void main(String args[]){
new Swing8();
}
}
