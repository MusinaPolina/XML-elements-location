package com.github.musinapolina.xmlelementslocation.toolWindow


import com.github.musinapolina.xmlelementslocation.elements.ElementManager
import com.github.musinapolina.xmlelementslocation.elements.ElementTable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import java.awt.event.MouseEvent
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.JScrollPane
import javax.swing.JTable


class MyToolWindowFactory : ToolWindowFactory {

    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow()
        val content = contentFactory.createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow {

        fun getContent() = JBPanel<JBPanel<*>>().apply {

            val popupMenu = JPopupMenu()

            val table = object : JTable(ElementTable) {
                override fun processMouseEvent(e: MouseEvent) {
                    if (e.clickCount >= 2 && e.button == MouseEvent.BUTTON1) {
                        e.consume()
                    }
                    if (e.clickCount == 2 && e.button == MouseEvent.BUTTON1) {
                        goto(e)
                    }
                    if (e.clickCount == 1 && e.button == MouseEvent.BUTTON3) {
                        showPopupMenu(e)
                    }
                    super.processMouseEvent(e)
                }

                private fun goto(e: MouseEvent) {
                    val target = e.source as JTable
                    val row = target.selectedRow
                    val element = ElementTable.getElementAt(row)
                    if (!element.goto()) {
                        Messages.showMessageDialog("It seems that element location doesn't exist. Statement will be removed.", "Element Doesn't Exist", null)
                        ElementManager.removeElement(row)
                    }
                }

                private fun showPopupMenu(e: MouseEvent) {
                    val row = rowAtPoint(e.point)
                    val column = columnAtPoint(e.point)
                    if (row >= 0 && column >= 0) {
                        setRowSelectionInterval(row, row)
                        popupMenu.show(this, e.x, e.y)
                    }
                }
            }

            popupMenu.add(JMenuItem("Remove").apply {
                addActionListener {
                    val response = Messages.showYesNoDialog(
                        "Do you want to remove element?",
                        "Confirmation",
                        null
                    )
                    if (response == Messages.YES) {
                        val row: Int = table.selectedRow
                        ElementManager.removeElement(row)
                    }
                }
            })

            popupMenu.add(JMenuItem("Rename").apply {
                addActionListener {
                    val row: Int = table.selectedRow
                    val newName = Messages.showInputDialog(
                        "Please enter new name:",
                        "Rename",
                        null,
                        ElementManager.getElementName(row),
                        null
                    )
                    if (newName != null) {
                        ElementManager.setElementName(row, newName)
                    }
                }
            })

            val scrollPane = JScrollPane(table)
            table.autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
            add(scrollPane)
            layout = BorderLayout()
            add(scrollPane, BorderLayout.CENTER)
        }
    }
}
