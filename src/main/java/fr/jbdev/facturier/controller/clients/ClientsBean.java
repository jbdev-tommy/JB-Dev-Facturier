package fr.jbdev.facturier.controller.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Personnes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.AdressesBean;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.fournisseurs.Messages;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.ClientsService;

@ViewScoped
@ManagedBean(name = "clientsBean", eager = false)
public class ClientsBean implements GeneralBean<Clients> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{clientsService}")
    private ClientsService clientService;

    @ManagedProperty(value = "#{clientsListBean}")
    private ClientsListBean clientListBean;

    @ManagedProperty(value = "#{adressesBean}")
    private AdressesBean adresseBean;

    private Clients client;
    private Personnes personne;
    private Utilisateurs user;
    private double paiment;

    @PostConstruct
    public void init() {
	this.client = new Clients();
	this.user = MyHttpSession.getUser();
	this.personne = new Personnes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.jbdev.facturier.controller.clients.GeneralBean#create()
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void create() {
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	try {
	    if (this.client != null) {
		personne.setAdresses(adresseBean.getAdresse());
		client.setEntreprises(user.getEntreprises());
		client.setPersonnes(personne);

		// Add List
		clientListBean.getList().add(client);

		// Persistance
		map.put(Personnes.class, personne);
		list.addAll(map.entrySet());
		clientService.setObject(list, client);

		// Reinit
		client = new Clients();
	    } else {
		throw new ObjectNullException(
			Messages.getString("ClientsBean.1")); //$NON-NLS-1$
	    }

	} catch (final ObjectNullException e) {
	    e.printStackTrace();
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.jbdev.facturier.controller.clients.GeneralBean#view(org.primefaces
     * .event.SelectEvent)
     */
    @Override
    public void view(final SelectEvent event) {
	// Le client selectionné
	this.client = (Clients) event.getObject();
	this.personne = client.getPersonnes();
	System.out.println(" Client : " + client.getEmail());
	// Affiche le dialog
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('clientDialog').show();");
    }

    @Override
    public void close() {

	// Close dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('clientDialog').hide()"); //$NON-NLS-1$

	this.client = new Clients();
	this.personne = new Personnes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.jbdev.facturier.controller.clients.GeneralBean#update()
     */
    @Override
    public void update() {
	// Persistance
	try {
	    clientService.update(client);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.jbdev.facturier.controller.clients.GeneralBean#delete()
     */
    @Override
    public void delete() {

	try {
	    clientService.remove(client);
	    clientListBean.getList().remove(client);
	} catch (final ObjectNullException e) {

	    e.printStackTrace();
	}
	close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.jbdev.facturier.controller.clients.GeneralBean#searchByNom(java.lang
     * .String)
     */
    @Override
    public Clients searchByNom(final String nom) {

	for (Clients client : clientListBean.getList()) {
	    if (client.getPersonnes().getNom().startsWith(nom))
		return client;
	}

	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.jbdev.facturier.controller.clients.GeneralBean#searchByMail(java.lang
     * .String)
     */
    @Override
    public Clients searchByMail(final String mail) {

	for (Clients client : clientListBean.getList()) {
	    if (client.getEmail().startsWith(mail))
		return client;
	}

	return null;
    }

    public ClientsService getClientService() {
	return clientService;
    }

    public void setClientService(ClientsService clientService) {
	this.clientService = clientService;
    }

    public ClientsListBean getClientListBean() {
	return clientListBean;
    }

    public void setClientListBean(ClientsListBean clientListBean) {
	this.clientListBean = clientListBean;
    }

    public AdressesBean getAdresseBean() {
	return adresseBean;
    }

    public void setAdresseBean(AdressesBean adresseBean) {
	this.adresseBean = adresseBean;
    }

    public Clients getClient() {
	return client;
    }

    public void setClient(Clients client) {
	this.client = client;
    }

    public Personnes getPersonne() {
	return personne;
    }

    public void setPersonne(Personnes personne) {
	this.personne = personne;
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

    public double getPaiment() {
	return paiment;
    }

    public void setPaiment(double paiment) {
	this.paiment = paiment;
    }

}
