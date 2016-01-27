package fr.jbdev.facturier.controller.devis;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Categories;
import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.clients.ClientsBean;
import fr.jbdev.facturier.controller.produits.ProduitsBean;
import fr.jbdev.facturier.controller.tva.TvaBean;
import fr.jbdev.facturier.controller.user.UserDetail;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.utils.PdfCreateur;

/**
 * Partie développer trés tard la nuit
 * 
 * @author tommy
 *
 */
@ViewScoped
@ManagedBean(name = "devisBean", eager = false)
public class DevisBean implements GeneralBean<Devis>, Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{userDetail}")
    private UserDetail user;

    @ManagedProperty(value = "#{devisListBean}")
    private DevisListBean devisListBean;

    @ManagedProperty(value = "#{produitsBean}")
    private ProduitsBean produitBean;

    @ManagedProperty(value = "#{clientsBean}")
    private ClientsBean clientBean;

    @ManagedProperty(value = "#{tvaBean}")
    private TvaBean tvaBean;

    private Devis devis;
    private Categories categorie;
    private Set<DevisProduits> listProduits;
    private String mentions;
    private String numero;

    private boolean confirme;
    private List<Devis> devisNonFacture;

    private double solde;
    private double soldeTtc;

    // Allimente devis produits
    private double quantite;
    private float remise;
    private double sommeHt;
    private double sommeTtc;

    @PostConstruct
    public void init() {
	devisNonFacture = new ArrayList<Devis>();
	listProduits = new HashSet<DevisProduits>();
	devis = new Devis();
	sommeHt = 0;
	sommeTtc = 0;

	// List des devis non facture
	for (Devis devis : devisListBean.getList()) {
	    if (devis.getFactureses().isEmpty()) {
		devisNonFacture.add(devis);
		for (DevisProduits pdt : devis.getDevisProduitses()) {
		    for (Produits pdts : pdt.getCategories().getProduitses())
			solde += pdt.getQuantite() * pdts.getPrixHt()
				* (1 + (pdt.getRemise() / 100));
		}
	    }
	}
    }

    public void onProduitDrop(DragDropEvent ddEvent) {
	categorie = (Categories) ddEvent.getData();

	// View le dialog
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('quantiteDialog').show();");
    }

    public void addProduits() {

	// Composer la liste de produits
	DevisProduits devisProduits = new DevisProduits();
	devisProduits.setDevis(devis); //Bizard bizard
	devisProduits.setCategories(categorie);
	devisProduits.setQuantite(quantite);
	devisProduits.setRemise(remise);
	devisProduits.setTva(tvaBean.getTva());

	// Ajouter à liste
	listProduits.add(devisProduits);

	// Sommes
	for (Produits pdt : categorie.getProduitses()) {
	    sommeHt += devisProduits.getQuantite() * pdt.getPrixHt()
		    * (1 + (remise / 100));
	    sommeTtc += devisProduits.getQuantite() * pdt.getPrixHt()
		    * (1 + (remise / 100))
		    * (1 + (devisProduits.getTva().getTaux() / 100));
	}

	// Reinit
	remise = 0;
	quantite = 0;

	// Close le dialog
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('quantiteDialog').hide();");
    }

    @Override
    public void create() {
	if (listProduits.isEmpty()) {

	  } else {
	    // Persistance
	    devis.setDevisProduitses(listProduits);
	    devis.setClients(clientBean.getClient());
	    devis.setDateDevis(new Date());
	    devis.setEntreprises(MyHttpSession.getUser().getEntreprises());
	    devis.setMentions(mentions);
	    devis.setNumero(numero);
	    devis.setRemiseGlobal(remise);

	    try {
		devisListBean.getDevisService().setObject(devis);
	    } catch (ObjectNullException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    // Vue
	    devisListBean.getList().add(devis);

	}
    }

    public void view(/* SelectEvent event */) {
	for (DevisProduits pdt : devis.getDevisProduitses()) {
	    for (Produits pdts : pdt.getCategories().getProduitses())
		solde += pdt.getQuantite()
			* pdts.getPrixHt()
			* (1 + (pdt.getRemise() != 0 ? pdt.getRemise() / 100
				: 0));
	    soldeTtc += solde * (1 + (pdt.getTva().getTaux() / 100));
	}

	PdfCreateur pdf = new PdfCreateur(MyHttpSession.getUser(), devis);
	// Streaming pdf
	final HttpSession session = MyHttpSession.getSession();
	session.setAttribute("reportBytes", pdf.create());
	final FacesContext ctx = FacesContext.getCurrentInstance();

	try {
	    ctx.getExternalContext().redirect("/facturier/report.pdf"); //$NON-NLS-1$
	} catch (final IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void close() {
	devis = new Devis();
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete() {
	final RequestContext context = RequestContext.getCurrentInstance();
	boolean delete = false;
	if (devis.getFactureses().isEmpty()) {
	    try {
		devisListBean.getDevisService().remove(devis);
		delete = true;
	    } catch (ObjectNullException e) {
		e.printStackTrace();
	    }
	} else {
	    context.execute("PF('comfirmeDialog').show()");

	    if (confirme) {
		try {
		    devisListBean.getDevisService().remove(devis);
		    delete = true;
		} catch (ObjectNullException e) {
		    e.printStackTrace();
		}
	    }
	}

	// Vue
	if (delete) {
	    devisListBean.getList().remove(devis);
	}
    }

    @Override
    public Devis searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Devis searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public UserDetail getUser() {
	return user;
    }

    public void setUser(UserDetail user) {
	this.user = user;
    }

    public DevisListBean getDevisListBean() {
	return devisListBean;
    }

    public void setDevisListBean(DevisListBean devisListBean) {
	this.devisListBean = devisListBean;
    }

    public ProduitsBean getProduitBean() {
	return produitBean;
    }

    public void setProduitBean(ProduitsBean produitBean) {
	this.produitBean = produitBean;
    }

    public TvaBean getTvaBean() {
	return tvaBean;
    }

    public void setTvaBean(TvaBean tvaBean) {
	this.tvaBean = tvaBean;
    }

    public Devis getDevis() {
	return devis;
    }

    public void setDevis(Devis devis) {
	this.devis = devis;
    }

    public Set<DevisProduits> getListProduits() {
	return listProduits;
    }

    public void setListProduits(Set<DevisProduits> listProduits) {
	this.listProduits = listProduits;
    }

    public boolean isConfirme() {
	return confirme;
    }

    public void setConfirme(boolean confirme) {
	this.confirme = confirme;
    }

    public double getQuantite() {
	return quantite;
    }

    public void setQuantite(double quantite) {
	this.quantite = quantite;
    }

    public float getRemise() {
	return remise;
    }

    public void setRemise(float remise) {
	this.remise = remise;
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

    public List<Devis> getDevisNonFacture() {
	return devisNonFacture;
    }

    public void setDevisNonFacture(List<Devis> devisNonFacture) {
	this.devisNonFacture = devisNonFacture;
    }

    public double getSolde() {
	return solde;
    }

    public void setSolde(double solde) {
	this.solde = solde;
    }

    public double getSoldeTtc() {
	return soldeTtc;
    }

    public void setSoldeTtc(double soldeTtc) {
	this.soldeTtc = soldeTtc;
    }

    public ClientsBean getClientBean() {
	return clientBean;
    }

    public void setClientBean(ClientsBean clientBean) {
	this.clientBean = clientBean;
    }

    public String getMentions() {
	return mentions;
    }

    public void setMentions(String mentions) {
	this.mentions = mentions;
    }

    public Categories getCategorie() {
	return categorie;
    }

    public void setCategorie(Categories categorie) {
	this.categorie = categorie;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    @Override
    public void view(SelectEvent event) {
	// TODO Auto-generated method stub

    }

}