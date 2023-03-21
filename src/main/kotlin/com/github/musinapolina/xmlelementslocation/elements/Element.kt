package com.github.musinapolina.xmlelementslocation.elements

import com.intellij.codeInsight.navigation.NavigationUtil
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.xml.XmlTag
import com.intellij.refactoring.suggested.startOffset

class Element(private val element: XmlTag, initName: String) {
    var name: String
        private set

    init {
        this.name = initName
    }

    fun updateName(newName: String) {
        name = newName
    }

    fun goto(): Boolean {
        if (element.parent == null) {
            return false
        }
        val project = element.project
        NavigationUtil.activateFileWithPsiElement(element, true)
        val editor: Editor? = FileEditorManager.getInstance(project).selectedTextEditor
        if (null != editor) {
            editor.caretModel.moveToOffset(element.startOffset)
            editor.scrollingModel.scrollToCaret(ScrollType.RELATIVE)
        } else {
            thisLogger().error("selectedTextEditor ${project.name} is null")
        }
        return true
    }
}
