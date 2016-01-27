package fr.jbdev.facturier.controller.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.service.MyUserDetailService;

@ManagedBean(name = "myLoginBean", eager = false)
@RequestScoped
public class LoginBean {

    private String userName;
    private String password;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager;

    @Autowired
    // @ManagedProperty(value="#{myUserDetailsService}")
    private MyUserDetailService userService;

    public LoginBean() {
	this.userName = null;
	this.password = null;
    }

    public String login() {
	try {
	    final Authentication request = new UsernamePasswordAuthenticationToken(
		    this.getUserName(), this.getPassword());
	    final Authentication result = authenticationManager
		    .authenticate(request);

	    SecurityContextHolder.getContext().setAuthentication(result);

	} catch (final AuthenticationException e) {
	    new MyFacesMessages("Mot de passe incorrect"); //$NON-NLS-1$

	    return "incorrect"; //$NON-NLS-1$
	}

	final HttpSession session = MyHttpSession.getSession();
	session.setAttribute("user", userService.getUser());

	return "correct"; //$NON-NLS-1$
    }

    public MyUserDetailService getUserService() {
	return userService;
    }

    public void setUserService(MyUserDetailService userService) {
	this.userService = userService;
    }

    public String cancel() {
	FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
	return null;
    }

    public String logout() {
	SecurityContextHolder.clearContext();
	FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
	return "/login"; //$NON-NLS-1$
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(
	    final AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

}
