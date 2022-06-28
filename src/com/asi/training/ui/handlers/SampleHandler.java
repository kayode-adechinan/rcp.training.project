package com.asi.training.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.asi.training.ui.dialogs.PasswordDialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;


public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "Hello", "Hello, Eclipse world. This is my first plugin!");

		MessageDialog.openConfirm(window.getShell(), "Confirm", "Please confirm");
		MessageDialog.openError(window.getShell(), "Error", "Error occured");
		MessageDialog.openInformation(window.getShell(), "Info", "Info for you");
		MessageDialog.openQuestion(window.getShell(), "Question", "Really, really?");
		MessageDialog.openWarning(window.getShell(), "Warning", "I am warning you!");

		boolean result = MessageDialog.openConfirm(window.getShell(), "Confirm", "Please confirm");

		if (result) {
			// OK Button selected do something
			System.out.println("OK pressed");
		} else {
			// Cancel Button selected do something
		}

		
		 PasswordDialog dialog = new PasswordDialog(window.getShell());

	        // get the new values from the dialog
	        if (dialog.open() == Window.OK) {
	            String user = dialog.getUser();
	            String pw = dialog.getPassword();
	            System.out.println(user);
	            System.out.println(pw);
	        }
		
		
		return null;

	}
}
