package com.asi.training.ui.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import com.asi.training.ui.Activator;

public class MinimarkVisitor implements IResourceProxyVisitor, IResourceDeltaVisitor {

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(IResourceProxy proxy) throws CoreException {
		String name = proxy.getName();
		if (name != null && name.endsWith(".minimark")) {
			// found a source file
			processResource(proxy.requestResource());
			System.out.println("Processing " + name);
		}
		return true;
	}

	private void processResource(IResource resource) throws CoreException {

		if (resource instanceof IFile) {
			try {
				IFile file = (IFile) resource;

				String htmlName = file.getName().replace(".minimark", ".html");
				IContainer container = file.getParent();
				IFile htmlFile = container.getFile(new Path(htmlName));

				InputStream in = file.getContents();
				// MinimarkTranslator.convert(new InputStreamReader(in), new
				// OutputStreamWriter(System.out));

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MinimarkTranslator.convert(new InputStreamReader(in), new OutputStreamWriter(System.out));
				// The following commented line needs to be removed
				/* new OutputStreamWriter(baos)); */
				ByteArrayInputStream contents = new ByteArrayInputStream(baos.toByteArray());

				if (htmlFile.exists()) {
					htmlFile.setContents(contents, true, false, null);
				} else {
					htmlFile.create(contents, true, null);
				}
				htmlFile.setDerived(true,null);

			} catch (IOException e) {
				throw new CoreException(
						(IStatus) new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to generate resource", e));
			}
		}

	}

}
