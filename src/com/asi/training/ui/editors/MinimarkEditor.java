package com.asi.training.ui.editors;

import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class MinimarkEditor extends
AbstractTextEditor {
	
	public MinimarkEditor() {
		setDocumentProvider(new
				TextFileDocumentProvider());
	}

}
