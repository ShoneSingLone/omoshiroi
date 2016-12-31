package com.leavay.dfc.SubjectView.gui;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * This program demonstrates how to show a simple table.
 * 
 * @version 1.12 2012-06-09
 * @author Cay Horstmann
 */
public class Table5 {
			
	public static void main(String[] args) {
		JTable demoTable = new JTable();
		DefaultTableModel dtmDemo = (DefaultTableModel) demoTable.getModel();
		String[] tableHeads = { "序号", "id", "jCheckBox" };
		dtmDemo.setColumnIdentifiers(tableHeads);
		demoTable.getColumnModel().getColumn(2).setCellEditor(new CWCheckBoxCellEditor());
		demoTable.getColumnModel().getColumn(2).setCellRenderer(new CWCheckBoxRenderer());
		for (int i = 0; i < 10; i++) {
			Object[] objdata = { i, 2, new Boolean(false)/** 这里就那个JCheckBox位置 */
			};
			dtmDemo.addRow(objdata);
		}
		
	}

	
}
class CwFrame extends JFrame {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
}



// ~ Inner Classes
// ----------------------------------------------------------------------------------------------------
class CWCheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
	// ~ Static fields/initializers
	// -------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;

	// ~ Instance fields
	// ------------------------------------------------------------------------------------------------

	protected JCheckBox checkBox;

	// ~ Constructors
	// ---------------------------------------------------------------------------------------------------

	public   CWCheckBoxCellEditor() {
		checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		// checkBox.setBackground( Color.white);
	}

	// ~ Methods
	// --------------------------------------------------------------------------------------------------------

	@Override
	public Object getCellEditorValue() {
		return Boolean.valueOf(checkBox.isSelected());
	}

	// ~
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		checkBox.setSelected(((Boolean) value).booleanValue());

		return checkBox;

	}
} // end class CheckBoxCellEditor

class CWCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
	// ~ Static fields/initializers
	// -------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;

	// ~ Instance fields
	// ------------------------------------------------------------------------------------------------

	Border border = new EmptyBorder(1, 2, 1, 2);

	// ~ Constructors
	// ---------------------------------------------------------------------------------------------------

	public CWCheckBoxRenderer() {
		super();
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	// ~ Methods
	// --------------------------------------------------------------------------------------------------------

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof Boolean) {
			setSelected(((Boolean) value).booleanValue());

			// setEnabled(table.isCellEditable(row, column));
			setForeground(table.getForeground());
			setBackground(table.getBackground());

		}

		return this;
	}
} // end class CWCheckBoxRenderer
