package fr.jbdev.facturier.controller.facture;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Factures;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.clients.ClientsListBean;
import fr.jbdev.facturier.controller.devis.DevisBean;
import fr.jbdev.facturier.controller.user.UserDetail;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.utils.PdfCreateur;

@ViewScoped
@ManagedBean(name = "facturesBean", eager = false)
public class FactureBean implements GeneralBean<Factures>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{userDetail}")
    private UserDetail user;
    
    @ManagedProperty(value = "#{factureListBean}")
    private FactureListBean factureListBean;

    @ManagedProperty(value = "#{clientsListBean}")
    private ClientsListBean clientListBean;

    @ManagedProperty(value = "#{devisBean}")
    private DevisBean devisBean;

    private Factures facture;
    private double solde;
    private double soldeTtc;

    @PostConstruct
    public void init() {
	facture = new Factures();
	solde = 0;
    }

    @Override
    public void create() {
	// Calcul et set Object
	if (devisBean.getDevis() != null) {
	    facture.setDevis(devisBean.getDevis());
	    facture.setDateFacture(new Date());
	    facture.setNumero(devisBean.getDevis().getNumero());

	    for (DevisProduits pdt : facture.getDevis().getDevisProduitses()) {
		for (Produits pdts : pdt.getCategories().getProduitses())
		    solde += pdt.getQuantite() * pdts.getPrixHt()
			    * (1 + (pdt.getRemise() / 100));
		soldeTtc += solde * (1 + (pdt.getTva().getTaux() / 100));
	    }
	    Clients client = facture.getDevis().getClients();
	    double currentSolde = client.getSolde() != null ? client.getSolde() : 0;
	    facture.getDevis().getClients().setSolde(soldeTtc + currentSolde);

	    // Persistance
	    try {
		factureListBean.getFacturesService().setObject(facture);
	    } catch (ObjectNullException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    // Vue
	    factureListBean.getList().add(facture);

	    // Close dialog ( l'anglais c'est court )
	    final RequestContext context = RequestContext.getCurrentInstance();
	    context.execute("PF('devisToFactureDialog').close()"); //$NON-NLS-1$

	} else {
	    // TODO Message;
	}
    }

    @Override
    public void view(SelectEvent event) {
	facture = (Factures) event.getObject();

	// Open dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('factureDialog').show()"); //$NON-NLS-1$

	for (DevisProduits pdt : facture.getDevis().getDevisProduitses()) {
	    for (Produits pdts : pdt.getCategories().getProduitses())
		solde += pdt.getQuantite() * pdts.getPrixHt()
			* (1 + (pdt.getRemise() / 100));
	    soldeTtc += solde * (1 + (pdt.getTva().getTaux() / 100));
	}

    }
    
   
    public void viewDevis() {

	// Open dialog ( l'anglais c'est court )
	final RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('devisToFactureDialog').show()"); //$NON-NLS-1$

    }

    public void apercu() {

	facture.setDevis(devisBean.getDevis());
	facture.setDateFacture(new Date());
	facture.setNumero(devisBean.getDevis().getNumero());

	PdfCreateur pdf = new PdfCreateur(MyHttpSession.getUser(), facture);
	pdf.setModel(user.getEntreprise().getModelPdf());
	
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

    public void pdf() {

	PdfCreateur pdf = new PdfCreateur(MyHttpSession.getUser(), facture);
	pdf.setModel(user.getEntreprise().getModelPdf());
	
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
	facture = new Factures();
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public FactureListBean getFactureListBean() {
	return factureListBean;
    }

    public void setFactureListBean(FactureListBean factureListBean) {
	this.factureListBean = factureListBean;
    }

    public DevisBean getDevisBean() {
	return devisBean;
    }

    public void setDevisBean(DevisBean devisBean) {
	this.devisBean = devisBean;
    }

    public Factures getFacture() {
	return facture;
    }

    public void setFacture(Factures facture) {
	this.facture = facture;
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
    public Factures searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Factures searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
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

    public ClientsListBean getClientListBean() {
	return clientListBean;
    }

    public void setClientListBean(ClientsListBean clientListBean) {
	this.clientListBean = clientListBean;
    }

}
