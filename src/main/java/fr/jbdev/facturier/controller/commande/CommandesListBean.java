package fr.jbdev.facturier.controller.commande;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Commandes;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.CommandesService;

@SessionScoped
@ManagedBean(name = "commandesListBean", eager = true)
public class CommandesListBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{commandesService}")
    private CommandesService commandeService;

    private Set<Commandes> list;

    @PostConstruct
    public void init() {
	list = new HashSet<Commandes>();
	Set<Commandes> commandes = MyHttpSession.getUser().getEntreprises()
		.getCommandeses();
	for (Commandes cmd : commandes) {
	    list.add(cmd);
	}
    }

    public CommandesService getCommandeService() {
	return commandeService;
    }

    public void setCommandeService(CommandesService commandeService) {
	this.commandeService = commandeService;
    }

    public Set<Commandes> getList() {
	return list;
    }

    public void setList(Set<Commandes> list) {
	this.list = list;
    }

}
