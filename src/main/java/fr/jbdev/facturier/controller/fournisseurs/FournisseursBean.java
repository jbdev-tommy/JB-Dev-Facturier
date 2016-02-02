package fr.jbdev.facturier.controller.fournisseurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.AdressesBean;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.FournisseursService;

@ViewScoped
@ManagedBean(name = "fournisseursBean", eager = false)
public class FournisseursBean implements GeneralBean<Fournisseurs> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{fournisseursService}")
    private FournisseursService fournisseursService;

    @ManagedProperty(value = "#{fournisseursListBean}")
    private FournisseursListBean fournisseursListBean;

    @ManagedProperty(value = "#{adressesBean}")
    private AdressesBean adressesBean;

    private Fournisseurs fournisseur;
    private Entreprises entreprise;
    private Adresses adresse;
    private Utilisateurs user;

    @PostConstruct
    public void init() {
	this.fournisseur = new Fournisseurs();
	this.adresse = new Adresses();
	this.user = MyHttpSession.getUser();
	this.entreprise = new Entreprises();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void create() {
	// Utilistaire pour la persistance
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	try {
	    if (this.fournisseur != null) {
		entreprise.setAdresses(adressesBean.getAdresse());
		entreprise.setModelPdf("0");
		fournisseur.setEntreprisesByNumSiretEntreprises(user
			.getEntreprises());
		fournisseur.setEntreprisesByNumSiret(entreprise);
		map.put(Entreprises.class, entreprise);
		list.addAll(map.entrySet());

		// Ajoute à la list ( vue )
		fournisseursListBean.getList().add(fournisseur);

		// Persistance ( mettre timer )
		fournisseursService.setObject(list, fournisseur);

	    } else {
		throw new ObjectNullException(
			Messages.getString("FournisseursBean.6"));
	    }
	} catch (final ObjectNullException e) {
	    e.printStackTrace();
	}

	// Recuperer le paramétre action dans la vue
	final Map<String, String> params = FacesContext.getCurrentInstance()
		.getExternalContext().getRequestParameterMap();

	if (params.get("action").equals("quick")) { //$NON-NLS-1$ //$NON-NLS-2$
	    RequestContext context = RequestContext.getCurrentInstance();
	    context.execute("PF('fourOverlay').hide()"); //$NON-NLS-1$
	}
	this.fournisseur = new Fournisseurs();
	fournisseursListBean.init();
    }

    @Override
    public void view(SelectEvent event) {
	// Fournisseur selectionné
	this.fournisseur = (Fournisseurs) event.getObject();
	this.entreprise = fournisseur.getEntreprisesByNumSiret();
	this.adresse = fournisseur.getEntreprisesByNumSiret().getAdresses();

	// Affiche le dialog
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('fournisseurDialog').show();"); //$NON-NLS-1$

    }

    @Override
    public void close() {
	// Reinit
	this.fournisseur = new Fournisseurs();
	this.entreprise = new Entreprises();
	this.adresse = new Adresses();

	// Ferme le dialog
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('fournisseurDialog').hide();"); //$NON-NLS-1$

    }

    @Override
    public void update() {

	try {
	    fournisseursService.update(fournisseur);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

	// Dialog fermé
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('fournisseurDialog').hide()"); //$NON-NLS-1$

    }

    @Override
    public Fournisseurs searchByNom(final String nom) {
	for (Fournisseurs four : fournisseursListBean.getList()) {
	    if (four.getEntreprisesByNumSiret().getNom().startsWith(nom)) {
		this.fournisseur = four;
		final RequestContext context = RequestContext
			.getCurrentInstance();
		context.execute("PF('fournisseurDialog').show();"); //$NON-NLS-1$
		return four;
	    }
	}
	return null;
    }

    @Override
    public Fournisseurs searchByMail(final String mail) {

	for (Fournisseurs four : fournisseursListBean.getList()) {
	    if (four.getEmail().startsWith(mail))
		return four;
	}

	return null;
    }

    @Override
    public void delete() {
	// TODO Auto-generated method stub

    }

    /**
     * GETTER and SETTER
     */

    public FournisseursService getFournisseursService() {
	return fournisseursService;
    }

    public void setFournisseursService(FournisseursService fournisseursService) {
	this.fournisseursService = fournisseursService;
    }

    public FournisseursListBean getFournisseursListBean() {
	return fournisseursListBean;
    }

    public void setFournisseursListBean(
	    FournisseursListBean fournisseursListBean) {
	this.fournisseursListBean = fournisseursListBean;
    }

    public Fournisseurs getFournisseur() {
	return fournisseur;
    }

    public void setFournisseur(Fournisseurs fournisseur) {
	this.fournisseur = fournisseur;
    }

    public Entreprises getEntreprise() {
	return entreprise;
    }

    public void setEntreprise(Entreprises entreprise) {
	this.entreprise = entreprise;
    }

    public void setAdressesBean(AdressesBean adressesBean) {
	this.adressesBean = adressesBean;
    }

    public Adresses getAdresse() {
	return adresse;
    }

    public void setAdresse(Adresses adresse) {
	this.adresse = adresse;
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

}