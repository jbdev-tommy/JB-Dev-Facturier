package fr.jbdev.facturier.controller.ventes;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.facture.FactureBean;
import fr.jbdev.facturier.controller.typePaiments.TypePaimentBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "ventesBean", eager = false)
public class VentesBean implements GeneralBean<Vente>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{venteListBean}")
    private VenteListBean venteListBean;

    @ManagedProperty(value = "#{facturesBean}")
    private FactureBean factureBean;

    @ManagedProperty(value = "#{typePaimentBean}")
    private TypePaimentBean typeDePaimentBean;

    private Vente vente;
    private double sommeTtc;
    private double sommeHt;
    private double tva;
    
    @PostConstruct
    public void init() {
	vente = new Vente();
	
	// Calcul global des sommes HT et TTC puis solde de la TVA cumulé
	for (Vente vente : venteListBean.getList()) {
	    sommeTtc += vente.getAccompte();
	    sommeHt += sommeTtc - vente.getTvaCumule();
	    tva += vente.getTvaCumule();
	}

    }

    @Override
    public void create() {
	if (vente != null) {
	    TypeDePaiment type = typeDePaimentBean.getType();
	    vente.setFactures(factureBean.getFacture());
	    vente.setTypeDePaiment(type);
	    vente.setDateVente(new Date()); // Date du jour
	    double sommeTtc = 0;
	    double sommeHt = 0;
	    
	    for(DevisProduits devPdt : factureBean.getFacture().getDevis().getDevisProduitses()) {
		for(Produits pdt : devPdt.getCategories().getProduitses()) {
		    
		    sommeTtc += (pdt.getPrixHt()*devPdt.getQuantite()*(1+(devPdt.getTva().getTaux()/100))) - (1+(devPdt.getRemise()/100));
		    sommeHt +=  (pdt.getPrixHt()*devPdt.getQuantite()) - (1+(devPdt.getRemise()/100));
		}
	    }
	    
	    //Calcul de la TVA sur accompte TVA = (SommeTtc * accompte ) / sommeTtc - accompteTtc
	    double ht = (sommeHt * vente.getAccompte()) / sommeTtc;
	    double tva = vente.getAccompte() - ht;
	    
	    vente.setTvaCumule(tva);
	    
	    try {
		venteListBean.getVentesService().setObject(vente);
	    } catch (ObjectNullException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    // Vue
	    venteListBean.getList().add(vente);
	} else {
	    // TODO Message
	}
	// Close dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('factureDialog').hide()"); //$NON-NLS-1$
    }

    public VenteListBean getVenteListBean() {
	return venteListBean;
    }

    public void setVenteListBean(VenteListBean venteListBean) {
	this.venteListBean = venteListBean;
    }

    public FactureBean getFactureBean() {
	return factureBean;
    }

    public void setFactureBean(FactureBean factureBean) {
	this.factureBean = factureBean;
    }

    public TypePaimentBean getTypeDePaimentBean() {
	return typeDePaimentBean;
    }

    public void setTypeDePaimentBean(TypePaimentBean typeDePaimentBean) {
	this.typeDePaimentBean = typeDePaimentBean;
    }

    public Vente getVente() {
	return vente;
    }

    public void setVente(Vente vente) {
	this.vente = vente;
    }

    @Override
    public void view(SelectEvent event) {
	vente = (Vente) event.getObject();
    }

    @Override
    public void close() {
	vente = new Vente();
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete() {
	// TODO Auto-generated method stub

    }

    @Override
    public Vente searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Vente searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public double getSommeTtc() {
	return sommeTtc;
    }

    public void setSommeTtc(double sommeTtc) {
	this.sommeTtc = sommeTtc;
    }

    public double getSommeHt() {
	return sommeHt;
    }

    public void setSommeHt(double sommeHt) {
	this.sommeHt = sommeHt;
    }

    public double getTva() {
	return tva;
    }

    public void setTva(double tva) {
	this.tva = tva;
    }

}
