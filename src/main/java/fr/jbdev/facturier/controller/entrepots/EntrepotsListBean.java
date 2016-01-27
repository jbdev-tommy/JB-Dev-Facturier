package fr.jbdev.facturier.controller.entrepots;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Entrepots;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.EntrepotsService;

@SessionScoped
@ManagedBean(name = "entrepotsListBean", eager = true)
public class EntrepotsListBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{entrepotsService}")
    private EntrepotsService entrepotService;

    private Set<Entrepots> list;

    @PostConstruct
    public void init() {
	setList(MyHttpSession.getUser().getEntreprises().getEntrepotses());
    }

    public EntrepotsService getEntrepotService() {
	return entrepotService;
    }

    public void setEntrepotService(EntrepotsService entrepotService) {
	this.entrepotService = entrepotService;
    }

    public Set<Entrepots> getList() {
	return list;
    }

    public void setList(Set<Entrepots> list) {
	this.list = list;
    }
}
