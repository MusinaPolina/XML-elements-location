package com.github.musinapolina.xmlelementslocation.elements

import javax.swing.table.AbstractTableModel

object  ElementTable : AbstractTableModel() {
    override fun getRowCount(): Int {
        return ElementManager.elements().size
    }

    override fun getColumnCount(): Int {
        return 1
    }

    override fun getColumnName(column: Int): String {
        return "Selected XML elements"
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return ElementManager.elements()[rowIndex].name
    }

    fun getElementAt(rowIndex: Int): Element {
        return ElementManager.elements()[rowIndex]
    }

    fun updateData() {
        fireTableDataChanged()
    }
}
