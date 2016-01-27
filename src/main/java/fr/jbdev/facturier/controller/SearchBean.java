package fr.jbdev.facturier.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.clients.ClientsBean;
import fr.jbdev.facturier.controller.fournisseurs.FournisseursBean;
import fr.jbdev.facturier.controller.produits.ProduitsBean;

/**
 * @author tommy
 * Bean de recherche, permet de trouver :
 * 	- produits
 *  	- fournisseurs
 *  	- clients
 */
/**
 * @author tommy
 *
 */
@ViewScoped
@ManagedBean(name = "searchBean", eager = false)
public class SearchBean {

    @ManagedProperty(value = "#{fournisseursBean}")
    private FournisseursBean fournisseursBean;

    @ManagedProperty(value = "#{clientsBean}")
    private ClientsBean clientsBean;

    @ManagedProperty(value = "#{produitsBean}")
    private ProduitsBean produitsBean;

    // private String selected;
    private Map<String, Object> bigMap;
    private List<String> nom;
    private Object select;

    @PostConstruct
    public void init() {

	// Créer la bigarray List par user
	nom = new ArrayList<String>();
	bigMap = new HashMap<String, Object>();
	List<Object> bigList = new ArrayList<Object>();
	bigList.addAll(fournisseursBean.getFournisseursListBean().getList());
	bigList.addAll(clientsBean.getClientListBean().getList());
	bigList.addAll(produitsBean.getProduitsListBean().getListProduits());
	bigList.addAll(produitsBean.getProduitsListBean().getListServices());

	// Ajouter devis par num et Facture par num (String)

	for (Object obj : bigList) {
	    if (obj instanceof Fournisseurs) {

		bigMap.put(((Fournisseurs) obj).getEntreprisesByNumSiret()
			.getNom(), obj);
	    } else if (obj instanceof Clients) {

		bigMap.put(((Clients) obj).getPersonnes().getNom(), obj);
	    } else if (obj instanceof Produits) {

		bigMap.put(((Produits) obj).getNom(), obj);
	    }
	}

	nom.addAll(bigMap.keySet());

    }

    public List<String> completeText(final String query) {
	List<String> results = new ArrayList<String>();

	for (String nom : nom) {
	    if (nom.toLowerCase().startsWith(query)) {
		results.add(nom);
	    }
	}

	return results;
    }

    /**
     * @param event
     * 
     *            Affiche le dialog en fonction de l'objet sélectionné (
     *            event.getObject() )
     */
    public void getObjectSelect(final SelectEvent event) { // Listener
	final Object obj = bigMap.get(event.getObject());
	final RequestContext context = RequestContext.getCurrentInstance();

	if (obj instanceof Clients) {
	    clientsBean.setClient((Clients) obj);
	    context.execute("PF('clientDialog').show();"); //$NON-NLS-1$
	} else if (obj instanceof Fournisseurs) {
	    fournisseursBean.setFournisseur((Fournisseurs) obj);
	    context.execute("PF('fournisseurDialog').show();"); //$NON-NLS-1$
	} else if (obj instanceof Produits) {
	    produitsBean.setProduit((Produits) obj);
	    context.execute("PF('produitDialog').show();"); //$NON-NLS-1$
	}

    }

    public FournisseursBean getFournisseursBean() {
	return fournisseursBean;
    }

    public void setFournisseursBean(FournisseursBean fournisseursBean) {
	this.fournisseursBean = fournisseursBean;
    }

    public ClientsBean getClientsBean() {
	return clientsBean;
    }

    public void setClientsBean(ClientsBean clientsBean) {
	this.clientsBean = clientsBean;
    }

    public ProduitsBean getProduitsBean() {
	return produitsBean;
    }

    public void setProduitsBean(ProduitsBean produitsBean) {
	this.produitsBean = produitsBean;
    }

    public Map<String, Object> getBigMap() {
	return bigMap;
    }

    public void setBigMap(Map<String, Object> bigMap) {
	this.bigMap = bigMap;
    }

    public List<String> getNom() {
	return nom;
    }

    public void setNom(List<String> nom) {
	this.nom = nom;
    }

    public Object getSelect() {
	return select;
    }

    public void setSelect(Object select) {
	this.select = select;
    }

}
