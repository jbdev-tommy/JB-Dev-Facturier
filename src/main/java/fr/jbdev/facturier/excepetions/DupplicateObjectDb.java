package fr.jbdev.facturier.excepetions;

public class DupplicateObjectDb extends Exception {

    /**
     * ID par Defaut
     */
    private static final long serialVersionUID = 1L;

    public DupplicateObjectDb(final String message) {
	super(message);
    }

}
