package com.asi.training.ui.core;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class FeedContentProvider implements ITreeContentProvider, IResourceChangeListener {

	private static final Object[] NO_CHILDREN = new Object[0];

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = NO_CHILDREN;
		if (parentElement instanceof IResource) {
			IResource resource = (IResource) parentElement;
			if (resource.getName().endsWith(".feeds")) {
				try {
					Properties properties = new Properties();
					InputStream stream = resource.getLocationURI().toURL().openStream();
					properties.load(stream);
					stream.close();
					result = new Object[properties.size()];
					int i = 0;
					Iterator it = properties.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, String> entry = (Entry<String, String>) it.next();
						result[i++] = new Feed(entry.getValue(), entry.getKey());
					}
				} catch (Exception e) {
					return NO_CHILDREN;
				}
			}
		}
		return result;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
