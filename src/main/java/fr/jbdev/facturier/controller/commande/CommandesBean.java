package fr.jbdev.facturier.controller.commande;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

import fr.jbdev.domaine.Commandes;
import fr.jbdev.domaine.Contient;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.fournisseurs.FournisseursBean;
import fr.jbdev.facturier.controller.tva.TvaBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "commandesBean", eager = false)
public class CommandesBean implements GeneralBean<Commandes> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{commandesListBean}")
    private CommandesListBean commandeListBean;

    @ManagedProperty(value = "#{fournisseursBean}")
    private FournisseursBean fournisseurBean;

//    @ManagedProperty(value = "#{achatsBean}")
//    private AchatsBean achatsBean;

    @ManagedProperty(value = "#{tvaBean}")
    private TvaBean tvaBean;

    private Commandes commande;
    private boolean confirme;
    private Set<Contient> contients;
    private List<Produits> produits;

    private Produits produit;
    private float quantite;

    private double sommeHt;
    private double sommeTtc;

    @PostConstruct
    public void init() {
	produits = new ArrayList<Produits>();
	contients = new HashSet<Contient>();
	commande = new Commandes();
	sommeHt = 0;
	sommeTtc = 0;
	// produits = new HashSet<Produits>();
    }

    @Override
    public void delete() {

	// Persistance
	try {
	    commandeListBean.getCommandeService().remove(commande);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Vue
	commandeListBean.getList().remove(commande);

    }

    public void onProduitDrop(DragDropEvent ddEvent) {
	this.produit = (Produits) ddEvent.getData();
	produits.add(produit);

	// Close le dialog
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('quantiteDialog').show();");
    }

    public void addProduit() {

	Contient contient = new Contient();
	contient.setProduits(produit);
	contient.setQuantite(quantite);
	contient.setTva(tvaBean.getTva());

	contients.add(contient);

	sommeHt += produit.getPrixHt() * quantite;
	sommeTtc += sommeHt * (1 + (tvaBean.getTva().getTaux() / 100));

	// Close le dialog
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('quantiteDialog').hide();");

	// Reinit
	quantite = 0;
	this.produit = new Produits();
    }

    @Override
    public void create() {

	try {
	    // Commande
	    commande.setFournisseurs(fournisseurBean.getFournisseur());
	    commande.setEntreprises(MyHttpSession.getUser().getEntreprises());
	    commande.setContients(contients);
	    commande.setSolde(sommeTtc);
	    commande.setTva(sommeTtc - sommeHt);
	    commande.setValide(false);

	    // Vue
	    commandeListBean.getList().add(commande);

	    // Persistance
	    commandeListBean.getCommandeService().setObject(commande);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    
    public void preProcessPDF(Object document) throws IOException,
    BadElementException, DocumentException {
    Document pdf = (Document) document;
    pdf.open();
    StringBuilder string = new StringBuilder();
    string.append(fournisseurBean.getFournisseur().getEntreprisesByNumSiret().getNom()).append("\n");
    string.append(fournisseurBean.getFournisseur().getEntreprisesByNumSiret().getAdresses().getNumero()).append("\n");
    string.append(fournisseurBean.getFournisseur().getEntreprisesByNumSiret().getAdresses().getVoie()).append("\n");
    string.append(fournisseurBean.getFournisseur().getEntreprisesByNumSiret().getAdresses().getVille()).append("\n");
    string.append(fournisseurBean.getFournisseur().getEntreprisesByNumSiret().getTelephone()).append("\n");
    
    pdf.add(Phrase.getInstance(string.toString()));

    }
    @Override
    public void view(SelectEvent event) {
	commande = (Commandes) event.getObject();

	// Open dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('commandeDialog').show()"); //$NON-NLS-1$

    }

    @Override
    public void update() {
	Commandes cmd = commande;
	cmd.setValide(true);
	// Persistance
	try {
	    commandeListBean.getCommandeService().update(cmd);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Vue
	if (commandeListBean.getList().contains(commande)) {
	    commandeListBean.getList().remove(commande);
	    commandeListBean.getList().add(cmd);
	}

    }

    @Override
    public void close() {

	// Close dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('commandeDialog').hide()"); //$NON-NLS-1$

	commande = new Commandes();
	contients = new HashSet<Contient>();

    }

    public CommandesListBean getCommandeListBean() {
	return commandeListBean;
    }

    public void setCommandeListBean(CommandesListBean commandeListBean) {
	this.commandeListBean = commandeListBean;
    }

    public FournisseursBean getFournisseurBean() {
	return fournisseurBean;
    }

    public void setFournisseurBean(FournisseursBean fournisseurBean) {
	this.fournisseurBean = fournisseurBean;
    }
//
//    public AchatsBean getAchatsBean() {
//	return achatsBean;
//    }
//
//    public void setAchatsBean(AchatsBean achatsBean) {
//	this.achatsBean = achatsBean;
//    }

    public TvaBean getTvaBean() {
	return tvaBean;
    }

    public void setTvaBean(TvaBean tvaBean) {
	this.tvaBean = tvaBean;
    }

    public Commandes getCommande() {
	return commande;
    }

    public void setCommande(Commandes commande) {
	this.commande = commande;
    }

    public boolean isConfirme() {
	return confirme;
    }

    public void setConfirme(boolean confirme) {
	this.confirme = confirme;
    }

    public Set<Contient> getContients() {
	return contients;
    }

    public void setContients(Set<Contient> contients) {
	this.contients = contients;
    }

    public Produits getProduit() {
	return produit;
    }

    public void setProduit(Produits produit) {
	this.produit = produit;
    }

    public float getQuantite() {
	return quantite;
    }

    public void setQuantite(float quantite) {
	this.quantite = quantite;
    }

    @Override
    public Commandes searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Commandes searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public double getSommeHt() {
	return sommeHt;
    }

    public void setSommeHt(double sommeHt) {
	this.sommeHt = sommeHt;
    }

    public double getSommeTtc() {
	return sommeTtc;
    }

    public void setSommeTtc(double sommeTtc) {
	this.sommeTtc = sommeTtc;
    }

    public List<Produits> getProduits() {
	return produits;
    }

    public void setProduits(List<Produits> produits) {
	this.produits = produits;
    }

}
