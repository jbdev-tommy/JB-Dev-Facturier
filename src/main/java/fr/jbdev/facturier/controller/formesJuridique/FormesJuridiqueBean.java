/**
 * 
 */
package fr.jbdev.facturier.controller.formesJuridique;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import fr.jbdev.domaine.FormesJuridiques;

/**
 * @author tommy
 *
 */
@ManagedBean(name = "formesJuridiqueBean")
@ViewScoped
public class FormesJuridiqueBean {

    @ManagedProperty(value = "#{formesJuridiqueListBean}")
    private FormesJuridiqueListBean formesJuridiqueListBean;

    private FormesJuridiques forme;

    @PostConstruct
    public void init() {
	forme = new FormesJuridiques();
    }

    public FormesJuridiqueListBean getFormesJuridiqueListBean() {
	return formesJuridiqueListBean;
    }

    public void setFormesJuridiqueListBean(
	    FormesJuridiqueListBean formesJuridiqueListBean) {
	this.formesJuridiqueListBean = formesJuridiqueListBean;
    }

    public FormesJuridiques getForme() {
	return forme;
    }

    public void setForme(FormesJuridiques forme) {
	this.forme = forme;
    }

}
