package fr.jbdev.facturier.controller.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCrypt;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.aspect.Messages;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.excepetions.UserIsNotExist;
import fr.jbdev.facturier.notifier.message.MailSenderService;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.service.UserService;

@ManagedBean(name = "passwordBean", eager = false)
@RequestScoped
public class passwordForget implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // @ManagedProperty(value = "#{param.key}")
    private String parametre;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    private Utilisateurs user;
    private String password;

    @PostConstruct
    public void init() {
	user = new Utilisateurs();
    }

    public void getPassWordForget() {

	try {
	    // TODO : block inutilisé, à revoir en version 2
	    if (parametre != null) {
		user = new Utilisateurs();

		user = userService.findUserByMail(parametre);

		if (password != null) {
		    user.setPassWord(BCrypt.hashpw(password, BCrypt.gensalt()));
		    userService.update(user);
		} else
		    throw new UserIsNotExist(parametre);

	    } else {
		Utilisateurs user = userService.findUserByMail(this.user
			.getEmail());

		String pass = String.valueOf(user.hashCode());
		user.setPassWord(BCrypt.hashpw(pass, BCrypt.gensalt()));
		userService.update(user);

		new MailSenderService(user.getEmail(),
			Messages.getString("passwordForget.0") //$NON-NLS-1$
				+ " " + pass); //$NON-NLS-1$
		new MyFacesMessages(Messages.getString("NotifierAspect.5")); //$NON-NLS-1$
	    }
	} catch (UserIsNotExist | ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	final FacesContext facesContext = FacesContext.getCurrentInstance();
	final String redirect = "login.xhtml"; //$NON-NLS-1$
	final NavigationHandler myNav = facesContext.getApplication()
		.getNavigationHandler();
	myNav.handleNavigation(facesContext, null, redirect);
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(final UserService userService) {
	this.userService = userService;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    public String getParametre() {
	return parametre;
    }

    public void setParametre(final String parametre) {
	this.parametre = parametre;
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

}
