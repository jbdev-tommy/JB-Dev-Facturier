package fr.jbdev.facturier.notifier.message;

import org.aspectj.lang.JoinPoint;

import fr.jbdev.facturier.messages.Messages;
import fr.jbdev.facturier.messages.ResourceMessage;

public class ConsolMessage extends StringMessage implements Message {

    private final JoinPoint joinPoint;

    public ConsolMessage(final String message, final JoinPoint joinPoint) {
	super(message);
	this.joinPoint = joinPoint;
	consolMessage();
    }

    private void consolMessage() {
	System.out.println(Messages.getString(
		"ConsolMessage.0", ResourceMessage.BUNDLE_NAME_MESSAGE)); //$NON-NLS-1$
	System.out
		.println(Messages
			.getString(
				"ConsolMessage.object.call", ResourceMessage.BUNDLE_NAME_MESSAGE) + joinPoint.getThis().getClass().getSimpleName().toString()); //$NON-NLS-1$
	System.out.println(getMessage().toString());
	System.out.println(Messages.getString(
		"ConsolMessage.2", ResourceMessage.BUNDLE_NAME_MESSAGE)); //$NON-NLS-1$
    }

}
