package fr.jbdev.facturier.controller.produits;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Categories;
import fr.jbdev.domaine.Contient;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.categorie.CategorieBean;
import fr.jbdev.facturier.controller.commande.CommandesBean;
import fr.jbdev.facturier.controller.entrepots.EntrepotBean;
import fr.jbdev.facturier.controller.fournisseurs.FournisseursBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "produitsBean", eager = false)
public class ProduitsBean implements GeneralBean<Produits> {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{produitsListBean}")
    private ProduitsListBean produitsListBean;

    @ManagedProperty(value = "#{fournisseursBean}")
    private FournisseursBean fournisseur;

    @ManagedProperty(value = "#{categoriesBean}")
    private CategorieBean categorieBean;

    @ManagedProperty(value = "#{commandesBean}")
    private CommandesBean commandeBean;

    @ManagedProperty(value = "#{entrepotsBean}")
    private EntrepotBean entrepotBean;

    private boolean comfirme;
    private Produits produit;

    private List<Produits> produitsFournisseurs;
    private List<Produits> produitsCategories;
    private List<Produits> produitsCommande;
    private List<Produits> produitsEntrepot;

    private String action;

    @PostConstruct
    public void init() {
	this.produit = new Produits();
	this.comfirme = false;
	this.produitsFournisseurs = new ArrayList<Produits>();

	// Recuperer le paramétre action dans la vue
	final Map<String, String> params = FacesContext.getCurrentInstance()
		.getExternalContext().getRequestParameterMap();
	this.setAction(params.get("action"));
    }

    @Override
    public void create() {
	if (produit != null) {
	    produit.setCategories(categorieBean.getCat());
	    if (fournisseur.getFournisseur().getNumFournisseur() != null) {

		// Persistance
		produit.setEntreprises(fournisseur.getFournisseur()
			.getEntreprisesByNumSiret());

		try {
		    produitsListBean.getProduitsService().setObject(produit);
		} catch (ObjectNullException e) {
		    e.printStackTrace();
		}

		// Vue
		fournisseur.getFournisseur().getEntreprisesByNumSiret().getProduitses().add(produit);

		
	    } else {
		// Persistance
		// Produit
		produit.setEntreprises(MyHttpSession.getUser().getEntreprises());

		try {
		    produitsListBean.getProduitsService().setObject(produit);

		} catch (ObjectNullException e) {
		    e.printStackTrace();
		}

		// Vue

		// Categorie
		for (Categories cat : categorieBean.getCategorieListBean()
			.getList()) {
		    cat.getProduitses().add(produit);
		}
		// Produits
		if (produit.isService()) {
		    produitsListBean.getListServices().add(produit);

		} else {
		    produitsListBean.getListProduits().add(produit);
		}
	    }
	}
    }

    @Override
    public void view(SelectEvent event) {
	this.produit = (Produits) event.getObject();

	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('produitDialog').show();"); //$NON-NLS-1$

    }

    @Override
    public void close() {
	this.produit = new Produits();

	// Faces dialog show
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('produitDialog').hide();"); //$NON-NLS-1$

    }

    @Override
    public void update() {

	// Update
	try {
	    produitsListBean.getProduitsService().update(produit);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Vue
	if (produitsListBean.getListProduits().contains(produit))
	    for (Produits pdt : produitsListBean.getListProduits()) {
		if (pdt.equals(produit)) {
		    produitsListBean.getListProduits().remove(pdt);
		    produitsListBean.getListProduits().add(produit);
		}
	    }
	else if (produitsListBean.getListServices().contains(produit)) {
	    for (Produits pdt : produitsListBean.getListServices()) {
		if (pdt.equals(produit)) {
		    produitsListBean.getListServices().remove(pdt);
		    produitsListBean.getListServices().add(produit);
		}
	    }

	}

	this.produit = new Produits();
	// Faces dialog hide
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('produitDialog').hide();"); //$NON-NLS-1$
    }

    @Override
    public void delete() {
	final RequestContext context = RequestContext.getCurrentInstance();
	boolean delete = false;

	// Persistance
	// Si il y a pas de devis lié au produit
	if (produit.getCategories().getDevisProduitses().isEmpty())
	    try {
		produitsListBean.getProduitsService().remove(produit);
		delete = true;
	    } catch (ObjectNullException e) {
		e.printStackTrace();
	    }
	else {
	    context.execute("PF('comfirmeDialog').show()");
	    if (comfirme) {
		try {
		    produitsListBean.getProduitsService().remove(produit);
		    delete = true;
		} catch (ObjectNullException e) {
		    e.printStackTrace();
		}

		// Vue
		if (delete) {
		    if (produitsListBean.getListProduits().contains(produit)) {
			produitsListBean.getListProduits().remove(produit);
		    } else
			produitsListBean.getListServices().remove(produit);
		}
	    }
	}

	context.execute("PF('produitDialog').hide()"); //$NON-NLS-1$
    }

    @Override
    public Produits searchByNom(final String nom) {

	for (Produits pdt : produitsListBean.getListProduits()) {
	    if (pdt.getNom().startsWith(nom))
		return pdt;
	}

	return null;
    }

    @Override
    public Produits searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public void listener() {
	produitsFournisseurs = new LinkedList<Produits>();
	Set<Produits> set =  fournisseur.getFournisseur().getEntreprisesByNumSiret().getProduitses();
	for (Produits pdt : set) {
		produitsFournisseurs.add(pdt);
		System.out.println(" Categories " + pdt.getNom());
	}

    }

    public void listenerCat() {
	produitsCategories = new LinkedList<Produits>();
	for (Produits pdt : categorieBean.getCat().getProduitses()) {
	    produitsCategories.add(pdt);
	}
    }

    public void listenerCmd() {
	setProduitsCommande(new LinkedList<Produits>());
	for (Contient cat : commandeBean.getCommande().getContients()) {
	    produitsCommande.add(cat.getProduits());
	}

    }

    public void listenerEntrepot() {
	setProduitsEntrepot(new ArrayList<Produits>());

	for (Produits pdt : entrepotBean.getEntrepot().getProduitses()) {
	    produitsEntrepot.add(pdt);
	}

    }

    public boolean isComfirme() {
	return comfirme;
    }

    public void setComfirme(boolean comfirme) {
	this.comfirme = comfirme;
    }

    public ProduitsListBean getProduitsListBean() {
	return produitsListBean;
    }

    public void setProduitsListBean(ProduitsListBean produitsListBean) {
	this.produitsListBean = produitsListBean;
    }

    public FournisseursBean getFournisseur() {
	return fournisseur;
    }

    public void setFournisseur(FournisseursBean fournisseur) {
	this.fournisseur = fournisseur;
    }

    public CategorieBean getCategorieBean() {
	return categorieBean;
    }

    public void setCategorieBean(CategorieBean categorieBean) {
	this.categorieBean = categorieBean;
    }

    public CommandesBean getCommandeBean() {
	return commandeBean;
    }

    public void setCommandeBean(CommandesBean commandeBean) {
	this.commandeBean = commandeBean;
    }

    public EntrepotBean getEntrepotBean() {
	return entrepotBean;
    }

    public void setEntrepotBean(EntrepotBean entrepotBean) {
	this.entrepotBean = entrepotBean;
    }

    public Produits getProduit() {
	return produit;
    }

    public void setProduit(Produits produit) {
	this.produit = produit;
    }

    public List<Produits> getProduitsFournisseurs() {
	return produitsFournisseurs;
    }

    public void setProduitsFournisseurs(List<Produits> produitsFournisseurs) {
	this.produitsFournisseurs = produitsFournisseurs;
    }

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }

    public List<Produits> getProduitsCategories() {
	return produitsCategories;
    }

    public void setProduitsCategories(List<Produits> produitsCategories) {
	this.produitsCategories = produitsCategories;
    }

    public List<Produits> getProduitsCommande() {
	return produitsCommande;
    }

    public void setProduitsCommande(List<Produits> produitsCommande) {
	this.produitsCommande = produitsCommande;
    }

    public List<Produits> getProduitsEntrepot() {
	return produitsEntrepot;
    }

    public void setProduitsEntrepot(List<Produits> produitsEntrepot) {
	this.produitsEntrepot = produitsEntrepot;
    }
}
