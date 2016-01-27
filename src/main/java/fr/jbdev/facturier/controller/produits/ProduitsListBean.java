package fr.jbdev.facturier.controller.produits;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.ProduitsService;

@SessionScoped
@ManagedBean(name = "produitsListBean", eager = true)
public class ProduitsListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Utilisateurs user;

    @ManagedProperty(value = "#{produitsService}")
    private ProduitsService produitsService;

    private Set<Produits> listProduits;
    private Set<Produits> listServices;

    @PostConstruct
    public void initUser() {

	user = MyHttpSession.getUser();
	listProduits = new HashSet<Produits>();
	listServices = new HashSet<Produits>();

	for (Produits pdt : user.getEntreprises().getProduitses()) {
	    if (pdt.isService())
		listServices.add(pdt);
	    else
		listProduits.add(pdt);
	}
	
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

    public ProduitsService getProduitsService() {
	return produitsService;
    }

    public void setProduitsService(ProduitsService produitsService) {
	this.produitsService = produitsService;
    }

    public Set<Produits> getListProduits() {
	return listProduits;
    }

    public void setListProduits(Set<Produits> listProduits) {
	this.listProduits = listProduits;
    }

    public Set<Produits> getListServices() {
	return listServices;
    }

    public void setListServices(Set<Produits> listServices) {
	this.listServices = listServices;
    }

   

}
