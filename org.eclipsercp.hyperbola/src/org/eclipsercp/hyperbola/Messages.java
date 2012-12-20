package org.eclipsercp.hyperbola;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipsercp.hyperbola.messages"; //$NON-NLS-1$
	public static String AddContactsEntryAction_Add_Contact;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME,Messages.class);
	}

	private Messages() {
	}
}
