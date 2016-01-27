package fr.jbdev.facturier.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCrypt;

import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.Personnes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.AdressesBean;
import fr.jbdev.facturier.controller.UploadControllerBean;
import fr.jbdev.facturier.controller.formesJuridique.FormesJuridiqueBean;
import fr.jbdev.facturier.excepetions.DupplicateObjectDb;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.service.UserService;

@ManagedBean(name = "signInBean", eager = false)
@RequestScoped
public class SignInBean implements Serializable {

    /**
     * Defaut
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    @ManagedProperty(value = "#{myUploadBean}")
    private UploadControllerBean uploaded;

    @ManagedProperty(value = "#{adressesBean}")
    private AdressesBean adressesBean;

    @ManagedProperty(value = "#{formesJuridiqueBean}")
    private FormesJuridiqueBean formesJuridiqueBean;

    private Utilisateurs user;
    private Personnes personne;
    private Entreprises entreprise;

    private boolean accept;

    @PostConstruct
    public void init() {

	user = new Utilisateurs();
	setPersonne(new Personnes());
	setEntreprise(new Entreprises());

    }

    public void checkUser() {

	try {
	    userService.findUserByMail(user.getEmail());

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

    }

    @SuppressWarnings("rawtypes")
    public Utilisateurs create() throws ObjectNullException, DupplicateObjectDb {
	// Si les conditions général sont accepté si non FacesMessage
	if (accept) {

	    // Utilistaire pour la persistance
	    List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	    Map<Class, Object> map = new HashMap<Class, Object>();

	    // Assignation du Roles ( responsable ou pas )
	    if (user.isResponsable())
		user.setRole("ROLE_ADMIN");
	    else
		user.setRole("ROLE_USER");

	    // mettre l'email en petit caractére ( evite les problémes )
	    user.setEmail(user.getEmail().toLowerCase());

	    // Crypter mot de passe
	    user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt()));

	    // L'utilisateur à la meme adresse que l'entreprise, inutile d'avoir
	    // son adresse
	    // ( pas de gestion de paye prévu )
	    personne.setAdresses(adressesBean.getAdresse());

	    // Récupérer l'adresse selectionné
	    entreprise.setAdresses(adressesBean.getAdresse());

	    // Récupérer la forme juridique
	    entreprise.setFormesJuridiques(formesJuridiqueBean.getForme());

	    if (uploaded.getFile() != null) {
		// Logo
		entreprise.setLogo(uploaded.getFile().getContents());
	    }

	    // Lien entre les données
	    user.setEntreprises(entreprise);
	    user.setPersonnes(personne);

	    // Map à remplir dans l'ordre de persistance, on créer une
	    // entreprise, aprés une personne ensuite un utilisateur.
	    map.put(Entreprises.class, entreprise);
	    map.put(Personnes.class, personne);

	    // On regroupe
	    list.addAll(map.entrySet());

	    // Test si l'utilisateur existe

	    userService.setObject(list, user);

	    // Redirection Faces vers Login
	    final FacesContext facesContext = FacesContext.getCurrentInstance();
	    final String redirect = "login.xhtml"; //$NON-NLS-1$
	    final NavigationHandler myNav = facesContext.getApplication()
		    .getNavigationHandler();
	    myNav.handleNavigation(facesContext, null, redirect);

	    return user;
	} else {
	    new MyFacesMessages(Messages.getString("SignInBean.4"));
	}

	return user;
    }

    public boolean isAccept() {
	return accept;
    }

    public void setAccept(boolean accept) {
	this.accept = accept;
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    public FormesJuridiqueBean getFormesJuridiqueBean() {
	return formesJuridiqueBean;
    }

    public void setFormesJuridiqueBean(FormesJuridiqueBean formesJuridiqueBean) {
	this.formesJuridiqueBean = formesJuridiqueBean;
    }

    public UploadControllerBean getUploaded() {
	return uploaded;
    }

    public void setUploaded(UploadControllerBean uploaded) {
	this.uploaded = uploaded;
    }

    public AdressesBean getAdressesBean() {
	return adressesBean;
    }

    public void setAdressesBean(AdressesBean adressesBean) {
	this.adressesBean = adressesBean;
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

    public Personnes getPersonne() {
	return personne;
    }

    public void setPersonne(Personnes personne) {
	this.personne = personne;
    }

    public Entreprises getEntreprise() {
	return entreprise;
    }

    public void setEntreprise(Entreprises entreprise) {
	this.entreprise = entreprise;
    }
}