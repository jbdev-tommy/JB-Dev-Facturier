package fr.jbdev.facturier.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.jbdev.domaine.Utilisateurs;

/**
 * Bean de gestion de la session
 * 
 * @author tommy
 *
 */
public class MyHttpSession {

    public static HttpSession getSession() {
	return (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(true);
    }

    public static HttpServletRequest getRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();
    }

    /**
     * @return
     * 
     *         Utiliser MyHttpSession.getUser() à la place
     * 
     */
    @Deprecated
    public static String getUserName() {
	final HttpSession session = (HttpSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
	return session
		.getAttribute(Messages.getString("MyHttpSession.0")).toString(); //$NON-NLS-1$
    }

    /**
     * @return
     * 
     *         Utiliser MyHttpSession.getUser() à la place
     * 
     */
    @Deprecated
    public static String getUserNom() {
	final HttpSession session = (HttpSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
	return session
		.getAttribute(Messages.getString("MyHttpSession.1")).toString(); //$NON-NLS-1$
    }

    /**
     * @return
     * 
     *         Utiliser MyHttpSession.getUser() à la place
     * 
     */
    @Deprecated
    public static String getUserPrenom() {
	final HttpSession session = (HttpSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null)
	    return (String) session.getAttribute(Messages
		    .getString("MyHttpSession.2")); //$NON-NLS-1$
	else
	    return null;
    }

    /**
     * @return
     * 
     *         Retourne l'utilisateur présent en session
     * 
     */
    public static Utilisateurs getUser() {
	final HttpSession session = (HttpSession) FacesContext
		.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null)
	    return (Utilisateurs) session.getAttribute(Messages
		    .getString("MyHttpSession.3")); //$NON-NLS-1$
	else
	    return null;
    }

}
