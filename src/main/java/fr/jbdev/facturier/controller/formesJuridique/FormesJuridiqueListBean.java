package fr.jbdev.facturier.controller.formesJuridique;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.FormesJuridiquesService;

@ManagedBean(name = "formesJuridiqueListBean", eager = true)
@ApplicationScoped
public class FormesJuridiqueListBean implements Serializable {

    /**
     * Par defaut
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{formesJuridiquesService}")
    private FormesJuridiquesService formesJuridiqueService;

    private List<FormesJuridiques> list;

    @PostConstruct
    public void init() {
	try {
	    setList(formesJuridiqueService.getAllObject(FormesJuridiques.class));
	} catch (ObjectNullException e) {
	    // TODO ajouter log
	    e.printStackTrace();
	}
    }

    public FormesJuridiquesService getFormesJuridiqueService() {
	return formesJuridiqueService;
    }

    public void setFormesJuridiqueService(
	    FormesJuridiquesService formesJuridiqueService) {
	this.formesJuridiqueService = formesJuridiqueService;
    }

    public List<FormesJuridiques> getList() {
	return list;
    }

    public void setList(List<FormesJuridiques> list) {
	this.list = list;
    }

}
