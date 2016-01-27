package fr.jbdev.facturier.controller.ventes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Factures;
import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.controller.facture.FactureListBean;
import fr.jbdev.facturier.service.VentesService;

@SessionScoped
@ManagedBean(name = "venteListBean", eager = true)
public class VenteListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{ventesService}")
    private VentesService ventesService;

    @ManagedProperty(value = "#{factureListBean}")
    private FactureListBean factureListBean;

    private List<Vente> list;

    private double sommeTtc;

    @PostConstruct
    public void init() {
	list = new ArrayList<Vente>();

	Set<Factures> facts = factureListBean.getList();
	for (Factures fact : facts) {
	    if (!fact.getVentes().isEmpty()) {
		list.addAll(fact.getVentes());
	    }
	}

	
	Collections.sort(list, new Comparator<Vente>() {

	    @Override
	    public int compare(Vente o1, Vente o2) {
		if (o1.getDateVente().compareTo(o2.getDateVente()) > 0)
		    return 1;
		else
		    return 0;
	    }

	});
    }

    public VentesService getVentesService() {
	return ventesService;
    }

    public void setVentesService(VentesService ventesService) {
	this.ventesService = ventesService;
    }

    public FactureListBean getFactureListBean() {
	return factureListBean;
    }

    public void setFactureListBean(FactureListBean factureListBean) {
	this.factureListBean = factureListBean;
    }

    public List<Vente> getList() {
	return list;
    }

    public void setList(List<Vente> list) {
	this.list = list;
    }

    public double getSommeTtc() {
	return sommeTtc;
    }

    public void setSommeTtc(double sommeTtc) {
	this.sommeTtc = sommeTtc;
    }

}
