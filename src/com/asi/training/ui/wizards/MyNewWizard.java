package com.asi.training.ui.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class MyNewWizard extends Wizard implements INewWizard {

	protected MyPageOne one;
	protected MyPageTwo two;

	public MyNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// initialize wizard with workbench and the current selection

	}

	@Override
	public String getWindowTitle() {
		return "How to handle wizard";
	}

	@Override
	public void addPages() {
		one = new MyPageOne();
		two = new MyPageTwo();
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		System.out.println(one.getText1());
		System.out.println(two.getText1());
		
		/*
		 PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
	            public void run() {
	                Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	                MessageDialog.openInformation(activeShell,
	                	    "Notification",
	                	    one.getText1() + 
	                	    two.getText1() );
	            }
	        });
		 */
		
		 MessageDialog.openInformation(getShell(),
				 "Notification",
         	    one.getText1() + 
         	    two.getText1() );

		return true;
	}

}
