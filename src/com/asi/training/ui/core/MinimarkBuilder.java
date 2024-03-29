package com.asi.training.ui.core;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class MinimarkBuilder extends IncrementalProjectBuilder {

	public static final String ID
	="com.asi.training.ui.core.MinimarkBuilder";
	
	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {

		if (kind == FULL_BUILD) {
			fullBuild(getProject(), monitor);
		} else {
			incrementalBuild(getProject(), monitor, getDelta(getProject()));
		}

		return null;
	}

	private void incrementalBuild(IProject project, IProgressMonitor monitor, IResourceDelta delta)
			throws CoreException {
		if (delta == null) {
			fullBuild(project, monitor);
		} else {
			delta.accept(new MinimarkVisitor());
			System.out.println("Doing an incremental build");
		}
	}

	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
	
		project.accept(new
				MinimarkVisitor(),IResource.NONE);
		
		System.out.println("Doing a full build");
	}

}
