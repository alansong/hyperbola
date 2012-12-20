package org.eclipsercp.hyperbola;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction addContactAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = ActionFactory.QUIT.create(window);
    	register(exitAction);
    	aboutAction = ActionFactory.ABOUT.create(window);
    	register(aboutAction);
    	addContactAction = new AddContactsEntryAction(window);
    	register(addContactAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	IMenuManager hyperbolaMenu = new MenuManager("&Hyperbola","hyperbola");
    	hyperbolaMenu.add(addContactAction);
    	hyperbolaMenu.add(new Separator());
    	hyperbolaMenu.add(exitAction);
    	IMenuManager helpMenu = new MenuManager("&Help","help");
    	helpMenu.add(aboutAction);
    	menuBar.add(hyperbolaMenu);
    	menuBar.add(helpMenu);
    }
    
    protected void fillTrayItem(IMenuManager trayItem) {
    	trayItem.add(aboutAction);
    	trayItem.add(exitAction);
    }

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		toolbar.add(addContactAction);
		coolBar.add(toolbar);
	}
}