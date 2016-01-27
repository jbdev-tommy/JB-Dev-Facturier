package fr.jbdev.facturier.controller.fournisseurs;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.controller.MyHttpSession;

@ManagedBean(name = "fournisseursListBean", eager = true)
@SessionScoped
public class FournisseursListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Set<Fournisseurs> list;

    @PostConstruct
    public void init() {
	Utilisateurs user = MyHttpSession.getUser();
	setList(user.getEntreprises().getFournisseursesForNumSiretEntreprises());
    }

    public Set<Fournisseurs> getList() {
	return list;
    }

    public void setList(Set<Fournisseurs> list) {
	this.list = list;
    }

}
