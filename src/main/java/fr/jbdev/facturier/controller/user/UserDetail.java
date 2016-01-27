package fr.jbdev.facturier.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.Personnes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.UploadControllerBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.service.UserService;

/**
 * @author tommy
 *
 */
@ManagedBean(name = "userDetail", eager = false)
@ViewScoped
public class UserDetail {

    @ManagedProperty(value = "#{myUploadBean}")
    private UploadControllerBean uploaded;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    private Utilisateurs user;
    private Personnes personne;
    private Entreprises entreprise;
    private Adresses adresse;

    private byte[] byt;

    @PostConstruct
    public void init() {

	uploaded.getImageBean().setByt(null);
	user = MyHttpSession.getUser();
	personne = user.getPersonnes();
	entreprise = user.getEntreprises();
	adresse = entreprise.getAdresses();
	byt = entreprise.getLogo();
    }

    // TODO vérifier la gestion du logo
    public void initLogo() {
	uploaded.getImageBean().setByt(null);
    }

    @SuppressWarnings("rawtypes")
    public void updateUser() {
	// Utilistaire pour la persistance
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	// Appliquer les changements
	if (uploaded.getFile() != null) {
	    entreprise.setLogo(uploaded.getFile().getContents());
	}
	entreprise.setAdresses(adresse);
	personne.setAdresses(adresse);

	// Map à remplir dans l'ordre de persistance, on créer une entreprise,
	// aprés une personne ensuite un utilisateur.
	map.put(Entreprises.class, entreprise);
	map.put(Personnes.class, personne);

	// On regroupe
	list.addAll(map.entrySet());

	// Persistance
	try {
	    userService.update(list, user);
	} catch (ObjectNullException e) {
	    // TODO Message
	    e.printStackTrace();
	}

	new MyFacesMessages(Messages.getString("UserDetail.0")); //$NON-NLS-1$

    }

    public UploadControllerBean getUploaded() {
	return uploaded;
    }

    public void setUploaded(UploadControllerBean uploaded) {
	this.uploaded = uploaded;
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	this.userService = userService;
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

    public Adresses getAdresse() {
	return adresse;
    }

    public void setAdresse(Adresses adresse) {
	this.adresse = adresse;
    }

    public byte[] getByt() {
	return byt;
    }

    public void setByt(byte[] byt) {
	this.byt = byt;
    }

}
