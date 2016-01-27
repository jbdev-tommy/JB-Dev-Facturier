package fr.jbdev.facturier.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.UserService;
import fr.jbdev.facturier.utils.CryptUtil;

/**
 * @author Jonathan Bochard
 *
 *         Bean gérant les confirmations d'adresse mail Retourne valid = true si
 *         l'utilisateur est présent dans la db
 */
@RequestScoped
@ManagedBean(name = "activation", eager = true)
public class ConfirmeBean {

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    @ManagedProperty(value = "#{param.key}")
    private String key;

    private boolean valid;

    @PostConstruct
    public void init() {

	final String keyD = CryptUtil.decrypt(key);

	try {
	    final Utilisateurs user = userService.findUserByMail(keyD);
	    if (!user.equals(null)) {
		user.setEnabled(true);
		userService.update(user);
		setValid(true);
	    } else {
		setValid(false);
	    }

	} catch (final ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    public boolean isValid() {
	return valid;
    }

    public void setValid(final boolean valid) {
	this.valid = valid;
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(final UserService userService) {
	this.userService = userService;
    }

    public String getKey() {
	return key;
    }

    public void setKey(final String key) {
	this.key = key;
    }
}
