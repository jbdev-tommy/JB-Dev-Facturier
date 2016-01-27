package fr.jbdev.facturier.controller.facture;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.Factures;
import fr.jbdev.facturier.controller.devis.DevisListBean;
import fr.jbdev.facturier.service.FacturesService;

@SessionScoped
@ManagedBean(name = "factureListBean", eager = true)
public class FactureListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{facturesService}")
    private FacturesService facturesService;

    @ManagedProperty(value = "#{devisListBean}")
    private DevisListBean devisListBean;

    private Set<Factures> list;

    @PostConstruct
    public void init() {
	list = new HashSet<Factures>();

	// On va charcher les factures par devis facturé
	Set<Devis> devis = devisListBean.getList();
	for (Devis dev : devis) {
	    if (!dev.getFactureses().isEmpty()) {
		for (Factures fact : dev.getFactureses()) {
		    list.add(fact);
		}
	    }
	}

    }

    public FacturesService getFacturesService() {
	return facturesService;
    }

    public void setFacturesService(FacturesService facturesService) {
	this.facturesService = facturesService;
    }

    public DevisListBean getDevisListBean() {
	return devisListBean;
    }

    public void setDevisListBean(DevisListBean devisListBean) {
	this.devisListBean = devisListBean;
    }

    public Set<Factures> getList() {
	return list;
    }

    public void setList(Set<Factures> list) {
	this.list = list;
    }

}
