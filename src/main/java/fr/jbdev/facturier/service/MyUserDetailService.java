package fr.jbdev.facturier.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.jbdev.domaine.Utilisateurs;

public interface MyUserDetailService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.jbdev.facturier.service.impl.MyUserDetailServiceI#loadUserByUsername
     * (java.lang.String)
     */
    public abstract UserDetails loadUserByUsername(String email)
	    throws UsernameNotFoundException;

    public Utilisateurs getUser();

}