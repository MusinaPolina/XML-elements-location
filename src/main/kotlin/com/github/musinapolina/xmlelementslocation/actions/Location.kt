package com.github.musinapolina.xmlelementslocation.actions

import com.github.musinapolina.xmlelementslocation.elements.ElementManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlTag


class Location : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor: Editor? = event.getData(CommonDataKeys.EDITOR)
        val psiFile: PsiFile? = event.getData(CommonDataKeys.PSI_FILE)
        if (editor == null || psiFile == null) {
            return
        }
        val offset = editor.caretModel.offset
        val element: PsiElement? = psiFile.findElementAt(offset)?.parent
        if (element is XmlTag) {
            thisLogger().info("Selected element is XmlTag")
            val defaultName = "${element.containingFile.name}/${element.localName}"
            val name = Messages.showInputDialog("Please enter element name:", "Create Location", null, defaultName, null)
            if (name != null) {
                ElementManager.addElement(element, name)
            }
        } else {
            thisLogger().info("Selected element is not XmlTag")
            Messages.showMessageDialog("Please select the XML element", "No Element Selected", null)
        }
    }
}
