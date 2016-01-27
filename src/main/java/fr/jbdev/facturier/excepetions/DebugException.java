package fr.jbdev.facturier.excepetions;

public class DebugException extends Exception {

    /**
     * ID par Defaut
     */
    private static final long serialVersionUID = 1L;

    public DebugException(final String message) {
	super(message);
    }
}
