package fr.jbdev.facturier.notifier.message;

public class StringMessage implements Message {

    private final String message;

    public String getMessage() {
	return message;
    }

    public StringMessage(final String message) {
	super();
	this.message = message;
    }

    @Override
    public String toString() {
	return this.message;
    }
}
