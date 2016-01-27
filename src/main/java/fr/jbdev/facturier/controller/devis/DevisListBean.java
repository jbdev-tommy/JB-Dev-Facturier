package fr.jbdev.facturier.controller.devis;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Devis;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.DevisService;

/**
 * 
 * Cette classe doit-étre optimisé
 * 
 * @author tommy
 *
 */
@SessionScoped
@ManagedBean(name = "devisListBean", eager = true)
public class DevisListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{devisService}")
    private DevisService devisService;

    private Set<Devis> list;

    @PostConstruct
    public void init() {
	setList(MyHttpSession.getUser().getEntreprises().getDevises());

    }

    public DevisService getDevisService() {
	return devisService;
    }

    public void setDevisService(DevisService devisService) {
	this.devisService = devisService;
    }

    public Set<Devis> getList() {
	return list;
    }

    public void setList(Set<Devis> list) {
	this.list = list;
    }

}
