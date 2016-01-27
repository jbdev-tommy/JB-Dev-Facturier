package fr.jbdev.facturier.controller.achats;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Achats;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.commande.CommandesBean;
import fr.jbdev.facturier.controller.typePaiments.TypePaimentBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;

/**
 * @author tommy Bean de gestion des achats
 */
@ViewScoped
@ManagedBean(name = "achatsBean", eager = false)
public class AchatsBean implements GeneralBean<Achats>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{achatsListBean}")
    private AchatsListBean achatListBean;

    @ManagedProperty(value = "#{commandesBean}")
    private CommandesBean commandeBean;

    @ManagedProperty(value = "#{typePaimentBean}")
    private TypePaimentBean typePaimentBean;

    private Achats achat;

    private String sommeTtc;
    private String sommeHt;
    private String totalTva;

    @PostConstruct
    public void init() {
	// Transformer double en string

	
	double tmpSommeHt = 0;
	double tmpSommeTtc = 0;
	double tmpTotalTva = 0;
	
	for(Achats achat : achatListBean.getList()) {
	    tmpSommeHt += achat.getAccomte();
	    tmpTotalTva += achat.getTvaRembourssable();
	    tmpSommeTtc += tmpSommeHt + tmpTotalTva;
	}
	
	achat = new Achats();
	DecimalFormat df = new DecimalFormat("0.00");
	sommeHt = df.format(tmpSommeHt);
	sommeTtc = df.format(tmpSommeTtc);
	totalTva = df.format(tmpTotalTva);
    }

    @Override
    public void create() {
	try {
	    // Commande
	    achat.setCommandes(commandeBean.getCommande());
	    achat.setTypeDePaiment(typePaimentBean.getType());
	    achat.setTvaRembourssable((commandeBean.getCommande().getTva() * achat
		    .getAccomte()) / commandeBean.getCommande().getSolde()); // Tva
									     // payé
	    achat.setDateAchats(new Date());

	    double currentSolde = commandeBean.getCommande().getSolde();
	    commandeBean.getCommande().setSolde(
		    currentSolde - achat.getAccomte());

	    // Persistance
	    achatListBean.getAchatsService().setObject(achat);

	    // Vue
	    achatListBean.getList().add(achat);
	    commandeBean.update(); // et Persistance

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void view(SelectEvent event) {
	achat = (Achats) event.getObject();

	// Affiche le dialog
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('achatsDialog').show();"); //$NON-NLS-1$
    }

    @Override
    public void close() {
	this.achat = new Achats();
	// Ferme le dialog
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('achatsDialog').hide();"); //$NON-NLS-1$

    }

    @Override
    public void update() {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete() {
	// TODO Auto-generated method stub

    }

    public String getSommeTtc() {
	return sommeTtc;
    }

    public String getSommeHt() {
	return sommeHt;
    }

    @Override
    public Achats searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Achats searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public AchatsListBean getAchatListBean() {
	return achatListBean;
    }

    public void setAchatListBean(AchatsListBean achatListBean) {
	this.achatListBean = achatListBean;
    }

    public CommandesBean getCommandeBean() {
	return commandeBean;
    }

    public void setCommandeBean(CommandesBean commandeBean) {
	this.commandeBean = commandeBean;
    }

    public TypePaimentBean getTypePaimentBean() {
	return typePaimentBean;
    }

    public void setTypePaimentBean(TypePaimentBean typePaimentBean) {
	this.typePaimentBean = typePaimentBean;
    }

    public Achats getAchat() {
	return achat;
    }

    public void setAchat(Achats achat) {
	this.achat = achat;
    }

    public String getTotalTva() {
	return totalTva;
    }

    public void setTotalTva(String totalTva) {
	this.totalTva = totalTva;
    }

    public void setSommeTtc(String sommeTtc) {
	this.sommeTtc = sommeTtc;
    }

    public void setSommeHt(String sommeHt) {
	this.sommeHt = sommeHt;
    }

}
