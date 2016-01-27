package fr.jbdev.facturier.excepetions;

public class ObjectNullException extends Exception {

    /**
     * ID par Defaut
     */
    private static final long serialVersionUID = 1L;

    public ObjectNullException(final String message) {
	super(message);
    }

}
