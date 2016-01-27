package fr.jbdev.facturier.controller.achats;

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

import fr.jbdev.domaine.Achats;
import fr.jbdev.domaine.Commandes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.controller.tva.TvaBean;
import fr.jbdev.facturier.service.AchatsService;

/**
 * Bean de création de la liste d'achats
 * 
 * @author jonathan Bochard
 *
 */
@SessionScoped
@ManagedBean(name = "achatsListBean", eager = true)
public class AchatsListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{achatsService}")
    private AchatsService achatsService;

    @ManagedProperty(value = "#{tvaBean}")
    private TvaBean tvaBean;

    private Utilisateurs user;
    private List<Achats> list;

    @PostConstruct
    public void init() {

	list = new ArrayList<Achats>();

	user = MyHttpSession.getUser();

	Set<Commandes> cmds = user.getEntreprises().getCommandeses();
	for (Commandes cmd : cmds) {
	    if(!cmd.getAchatses().isEmpty()) {
	   	list.addAll(cmd.getAchatses());
	    }
	}

	Collections.sort(list, new Comparator<Achats>() {

	    @Override
	    public int compare(Achats o1, Achats o2) {
		if (o1.getDateAchats().compareTo(o2.getDateAchats()) > 0)
		    return 1;
		else
		    return 0;
	    }

	});
    }

    public AchatsService getAchatsService() {
	return achatsService;
    }

    public void setAchatsService(AchatsService achatsService) {
	this.achatsService = achatsService;
    }

    public TvaBean getTvaBean() {
	return tvaBean;
    }

    public void setTvaBean(TvaBean tvaBean) {
	this.tvaBean = tvaBean;
    }

    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }

   

    public List<Achats> getList() {
	return list;
    }

    public void setList(List<Achats> list) {
	this.list = list;
    }

 

}
