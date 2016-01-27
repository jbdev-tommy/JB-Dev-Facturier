package fr.jbdev.facturier.controller.tva;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Tva;

@SessionScoped
@ManagedBean(name = "tvaBean", eager = false)
public class TvaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{tvaListBean}")
    private TvaListBean listTaxe;

    private Tva tva;

    @PostConstruct
    public void init() {
	setTva(listTaxe.getList().get(0));
    }

    public Tva getTva() {
	return tva;
    }

    public void setTva(Tva tva) {
	this.tva = tva;
    }

    public TvaListBean getListTaxe() {
	return listTaxe;
    }

    public void setListTaxe(TvaListBean listTaxe) {
	this.listTaxe = listTaxe;
    }

}
