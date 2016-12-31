package shone.sing.lone.swing.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MainClass extends JFrame {
  ColorName colors[] = { new ColorName("Red"), new ColorName("Green"), new ColorName("Blue"),
      new ColorName("Black"), new ColorName("White") };

  public MainClass() {
    super("Table With DefaultCellEditor Example");
    setSize(500, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JTable table = new JTable(new AbstractTableModel() {
      ColorName data[] = { colors[0], colors[1], colors[2], colors[3], colors[4], colors[0],
          colors[1], colors[2], colors[3], colors[4] };

      public int getColumnCount() {
        return 3;
      }

      public int getRowCount() {
        return 10;
      }

      public Object getValueAt(int r, int c) {
        switch (c) {
        case 0:
          return (r + 1) + ".";
        case 1:
          return "Some pithy quote #" + r;
        case 2:
          return data[r];
        }
        return "Bad Column";
      }

      public Class getColumnClass(int c) {
        if (c == 2)
          return ColorName.class;
        return String.class;
      }

      public boolean isCellEditable(int r, int c) {
        return c == 2;
      }

      public void setValueAt(Object value, int r, int c) {
        data[r] = (ColorName) value;
      }
    });

    table.setDefaultEditor(ColorName.class, new DefaultCellEditor(new JComboBox(colors)));
    table.setDefaultRenderer(ColorName.class, new DefaultTableCellRenderer());
    table.setRowHeight(20);
    getContentPane().add(new JScrollPane(table));
  }

  public static void main(String args[]) {
    MainClass ex = new MainClass();
    ex.setVisible(true);
  }

  public class ColorName {
    String cname;

    public ColorName(String name) {
      cname = name;
    }

    public String toString() {
      return cname;
    }
  }
}