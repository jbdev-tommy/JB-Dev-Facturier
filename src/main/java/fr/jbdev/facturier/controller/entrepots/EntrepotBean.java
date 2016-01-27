package fr.jbdev.facturier.controller.entrepots;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Contient;
import fr.jbdev.domaine.Entrepots;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.commande.CommandesBean;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "entrepotsBean")
public class EntrepotBean implements GeneralBean<Entrepots> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{entrepotsListBean}")
    private EntrepotsListBean entrepotListBean;

    @ManagedProperty(value = "#{commandesBean}")
    private CommandesBean commandesBean;

    private Entrepots entrepot;
    private boolean view;
    
    @PostConstruct
    public void init() {
	view = false;
	
	//Créer un entrepot à la premir connexion
	if(!entrepotListBean.getList().isEmpty())
	    view = true;
	
	setEntrepot(new Entrepots());
    }

  
    @Override
    public void create() {

	if (entrepot != null) {
	    entrepot.setEntreprises(MyHttpSession.getUser().getEntreprises());

	    try {
		// Persistance
		entrepotListBean.getEntrepotService().setObject(entrepot);

		// Vue
		entrepotListBean.getList().add(entrepot);
		entrepot = new Entrepots();

	    } catch (ObjectNullException e) {
		e.printStackTrace();
	    }
	} else
	    try {
		throw new ObjectNullException("Objet nul");
	    } catch (ObjectNullException e) {
		e.printStackTrace();
	    }
    }

    @Override
    public void view(SelectEvent event) {
	this.entrepot = (Entrepots) event.getObject();
    }

    @Override
    public void update() {
	try {
	    // Persistance
	    entrepotListBean.getEntrepotService().update(entrepot);
	    // vue
		if (entrepotListBean.getList().contains(entrepot)) {
		    entrepotListBean.getList().remove(entrepot);
		    entrepotListBean.getList().add(entrepot);
		}
	  
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void delete() {
	// TODO Auto-generated method stub

    }

    @Override
    public Entrepots searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Entrepots searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public void onProduitDrop(DragDropEvent ddEvent) {
	Contient cmd = (Contient) ddEvent.getData();
	Produits pdt = cmd.getProduits();
	float stock = pdt.getStock();
	pdt.setStock(stock + cmd.getQuantite());
	entrepot.getProduitses().add(pdt);

    }

    @Override
    public void close() {
	this.entrepot = new Entrepots();

    }

    public Entrepots getEntrepot() {
	return entrepot;
    }

    public void setEntrepot(Entrepots entrepot) {
	this.entrepot = entrepot;
    }

    public EntrepotsListBean getEntrepotListBean() {
	return entrepotListBean;
    }

    public void setEntrepotListBean(EntrepotsListBean entrepotListBean) {
	this.entrepotListBean = entrepotListBean;
    }

    public CommandesBean getCommandesBean() {
	return commandesBean;
    }

    public void setCommandesBean(CommandesBean commandesBean) {
	this.commandesBean = commandesBean;
    }
    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

}
