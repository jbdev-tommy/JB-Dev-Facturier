package fr.jbdev.facturier.notifier.message;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import fr.jbdev.facturier.messages.Messages;
import fr.jbdev.facturier.messages.ResourceMessage;

public class MyFacesMessages extends StringMessage implements Message {

    public MyFacesMessages(final String message) {
	super(message);
	newFaceMessage(message);
    }

    private void newFaceMessage(final String message) {
	System.out.println(Messages.getString(
		"ConsolMessage.0", ResourceMessage.BUNDLE_NAME_MESSAGE)); //$NON-NLS-1$
	System.out
		.println(Messages
			.getString(
				"ConsolMessage.object.call", ResourceMessage.BUNDLE_NAME_MESSAGE) + fr.jbdev.facturier.notifier.message.Messages.getString("MyFacesMessages.0")); //$NON-NLS-1$ //$NON-NLS-2$
	System.out.println(" Face Message envoyé... "); //$NON-NLS-1$
	System.out.println(Messages.getString(
		"ConsolMessage.2", ResourceMessage.BUNDLE_NAME_MESSAGE)); //$NON-NLS-1$
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(
		message,
		new FacesMessage(fr.jbdev.facturier.notifier.message.Messages
			.getString("MyFacesMessages.2"), getMessage()));

    }
}
