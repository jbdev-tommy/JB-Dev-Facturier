package fr.jbdev.facturier.messages;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

    private static String files;

    private static ResourceBundle RESOURCE_BUNDLE;

    public static String getString(final String key,
	    final String ressourceMessage) {
	Messages.files = ressourceMessage;
	RESOURCE_BUNDLE = ResourceBundle.getBundle(files);

	try {
	    return RESOURCE_BUNDLE.getString(key);
	} catch (final MissingResourceException e) {
	    return '!' + key + '!';
	}
    }

    public static String getFiles() {
	return files;
    }

    public static void setFiles(final String files) {
	Messages.files = files;
    }

}
