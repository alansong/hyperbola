package org.eclipsercp.hyperbola;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class AddContactsEntryAction extends Action implements ISelectionListener, IWorkbenchAction {
	public static final String ID = "org.eclipsercp.hyperbola.addContact";
	
	private final IWorkbenchWindow window;
	private IStructuredSelection selection;
	
	public AddContactsEntryAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Add Contact...");
		setToolTipText(Messages.AddContactsEntryAction_Add_Contact);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.ADD_CONTACT));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part,ISelection incoming) {
		if(incoming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof ContactsGroup);
		} else {
			setEnabled(false);
		}
	}
	
	@Override
	public void run() {
		AddContactDialog d = new AddContactDialog(window.getShell());
		int code = d.open();
		if(code == Window.OK) {
			ContactsGroup group = (ContactsGroup) selection.getFirstElement();
			ContactsEntry entry = new ContactsEntry(group,d.getUserId(),d.getNickname(),d.getServer());
			group.addEntry(entry);
		}
	}
}