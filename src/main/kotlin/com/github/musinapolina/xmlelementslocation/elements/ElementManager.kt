package com.github.musinapolina.xmlelementslocation.elements

import com.intellij.psi.xml.XmlTag

object ElementManager {
    private val elementList: MutableList<Element> = listOf<Element>().toMutableList()

    @JvmStatic
    fun addElement(element: XmlTag, name: String) {
        elementList.add(Element(element, name))
        ElementTable.updateData()
    }

    fun removeElement(index: Int) {
        elementList.removeAt(index)
        ElementTable.updateData()
    }

    fun getElementName(index: Int): String {
        return elementList[index].name
    }

    fun setElementName(index: Int, newName: String) {
        elementList[index].updateName(newName)
        ElementTable.updateData()
    }

    @JvmStatic
    fun elements() = elementList
}
