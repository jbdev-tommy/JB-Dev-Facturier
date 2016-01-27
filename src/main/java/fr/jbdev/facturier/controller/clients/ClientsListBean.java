package fr.jbdev.facturier.controller.clients;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.ClientsService;

@ManagedBean(name = "clientsListBean", eager = true)
@SessionScoped
public class ClientsListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{clientsService}")
    private ClientsService clientService;

    private Set<Clients> list;

    @PostConstruct
    public void init() {

	Utilisateurs user = MyHttpSession.getUser();
	setList(user.getEntreprises().getClientses());

    }

    public Set<Clients> getList() {
	return list;
    }

    public void setList(Set<Clients> list) {
	this.list = list;
    }

    public ClientsService getClientService() {
	return clientService;
    }

    public void setClientService(ClientsService clientService) {
	this.clientService = clientService;
    }
}
