package fr.jbdev.facturier.excepetions;

public class UserIsNotExist extends Exception {

    /**
     * ID par default
     */
    private static final long serialVersionUID = 1L;

    public UserIsNotExist(final String message) {
	super(message);
    }
}
