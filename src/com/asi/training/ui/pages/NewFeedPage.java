package com.asi.training.ui.pages;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NewFeedPage extends WizardPage {

	private Text descriptionText;
	private Text urlText;

	public NewFeedPage() {
		super("NewFeedPage");
		setTitle("Add New Feed");
		setMessage("Please enter a URL and description for a news feed");
		
		setImageDescriptor(ImageDescriptor.createFromFile(NewFeedPage.class,
				"/icons/newfeed_wiz.png"));
		
	}

	private class CompleteListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			boolean hasDescription = !"".equals(getTextFrom(descriptionText));
			boolean hasUrl = !"".equals(getTextFrom(urlText));
			if (!hasDescription) {
				setMessage("Please enter a description", IMessageProvider.ERROR);
			}
			if (!hasUrl) {
				setMessage("Please enter a URL", IMessageProvider.ERROR);
			}
			if (hasDescription && hasUrl) {
				setMessage(null);
			}
			setPageComplete(hasDescription && hasUrl);
		}
	}

	public void createControl(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);
		setControl(page);
		setPageComplete(false);

		page.setLayout(new GridLayout(2, false));
		page.setLayoutData(new GridData(GridData.FILL_BOTH));
		Label urlLabel = new Label(page, SWT.NONE);
		urlLabel.setText("Feed URL:");
		urlText = new Text(page, SWT.BORDER);
		urlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label descriptionLabel = new Label(page, SWT.NONE);
		descriptionLabel.setText("Feed description:");
		descriptionText = new Text(page, SWT.BORDER);
		descriptionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		CompleteListener listener = new CompleteListener();
		urlText.addKeyListener(listener);
		descriptionText.addKeyListener(listener);
	}

	public String getDescription() {
		return getTextFrom(descriptionText);
	}

	private String getTextFrom(Text text) {
		return text == null || text.isDisposed() ? null : text.getText();
	}

	public String getURL() {
		return getTextFrom(urlText);
	}
	
	@Override
	public void performHelp() {
		MessageDialog
				.openInformation(
						getShell(),
						"Help for Add New Feed",
						"You can add your feeds into this as an RSS or Atom feed, "
						+ "and optionally specify an additional description "
						+ "which will be used as the feed title.");
	}

}
