package shone.sing.lone.sample.swing;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class TableJComboBox {
    private JFrame frame = new JFrame("下拉列表与表格");
    private String[] title = {"项目名称", "S", "O", "D", "RPN"};
    private String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                              "10"};
    private Object[][] cellData = { {"1", "", "", "", ""}, {"2", "", "", "", ""},
                                  {"3", "", "", "", ""}
    };
    JTable table;
    public void init() {
        ExtendedTableModel model = new ExtendedTableModel(cellData, title);
        JComboBoxTableCellRenderer render = new JComboBoxTableCellRenderer();
        final JComboBox boxOne = new JComboBox(number);
        final JComboBox boxTwo = new JComboBox(number);
        final JComboBox boxThree = new JComboBox(number);
        table = new JTable(model);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(40);
        //获得第二、三、四列
        TableColumn second = table.getColumnModel().getColumn(1);
        TableColumn third = table.getColumnModel().getColumn(2);
        TableColumn forth = table.getColumnModel().getColumn(3);
        //设置第二至四列的单元格绘制器及单元格编辑器
        second.setCellRenderer(render);
        second.setCellEditor(new DefaultCellEditor(boxOne));
        third.setCellRenderer(render);
        third.setCellEditor(new DefaultCellEditor(boxTwo));
        forth.setCellRenderer(render);
        forth.setCellEditor(new DefaultCellEditor(boxThree));
        //对第一个JComboBox添加事件监听器，监听其选项的改变，获得计算结果
        boxOne.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (String) boxOne.getSelectedItem();
                int rowSelected = table.getEditingRow();
                int columnSelected = table.getEditingColumn();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (rowSelected >= 0 && columnSelected >= 0) {
                        table.setValueAt(item, rowSelected, columnSelected);
                        //如果所选行的第二、三、四列不为空，计算所选行的二、三、四列的乘积
                        if ((String) table.getValueAt(rowSelected, 1) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 2) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 3) !=
                            "") {
                            table.setValueAt((Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 1))) *//获得所选行第二列的值
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 2))) *//获得所选行第三列的值
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 3))), rowSelected,
                                             4);//获得所选行第四列的值

                        }

                    }
                }
            }
        });
        boxTwo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (String) boxTwo.getSelectedItem();
                int rowSelected = table.getEditingRow();
                int columnSelected = table.getEditingColumn();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (rowSelected >= 0 && columnSelected >= 0) {
                        table.setValueAt(item, rowSelected, columnSelected);
                        if ((String) table.getValueAt(rowSelected, 1) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 2) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 3) !=
                            "") {
                            table.setValueAt((Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 1))) *
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 2))) *
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 3))), rowSelected,
                                             4);

                        }
                    }
                }
            }
        });
        boxThree.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (String) boxThree.getSelectedItem();
                int rowSelected = table.getEditingRow();
                int columnSelected = table.getEditingColumn();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (rowSelected >= 0 && columnSelected >= 0) {
                        table.setValueAt(item, rowSelected, columnSelected);
                        if ((String) table.getValueAt(rowSelected, 1) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 2) !=
                            "" &&
                            (String) table.getValueAt(rowSelected, 3) !=
                            "") {
                            table.setValueAt((Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 1))) *
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 2))) *
                                             (Integer.parseInt((String) table.
                                    getValueAt(rowSelected, 3))), rowSelected,
                                             4);

                        }

                    }
                }
            }
        });

        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new TableJComboBox().init();
    }

    //构建DefaultTableModel的子类，获取每一列的真实数据类型
    class ExtendedTableModel extends DefaultTableModel {
        //利用父类DefaultTableModel的构造器来重新构造一个新的构造器
        public ExtendedTableModel(Object[][] cellData, String[] title) {
            super(cellData, title);
        }

        //重写getColumnClass方法，根据每列的第一个值来返回其真实的数据类型
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }


    //定义定制的单元格绘制器，采用该绘制器的每个单元格的组件是JLabel
    class JComboBoxTableCellRenderer extends JLabel implements
            TableCellRenderer {
        private String cellValue;
        //重写TableCellRenderer的getTableCellRendererComponent方法
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            cellValue = (String) value;
            this.setText(cellValue);
            if (hasFocus) {
                //选中单元格边框高亮设置
                setBorder(UIManager.getBorder("Table.focusCellHighlinghtBorder"));
            } else {
                setBorder(null);
            }
            return this;
        }
    }
}
