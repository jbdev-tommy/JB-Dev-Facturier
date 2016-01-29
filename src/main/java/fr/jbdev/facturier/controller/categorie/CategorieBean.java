package fr.jbdev.facturier.controller.categorie;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Categories;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "categoriesBean", eager = false)
public class CategorieBean implements GeneralBean<Categories> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{categorieListBean}")
    private CategorieListBean categorieListBean;

    private Categories cat;
    private boolean confirme;
    private boolean view;
    
    @PostConstruct
    public void init() {
	view = false;
	
	//Créer une categorie a la premiérer connection
	if(!categorieListBean.getList().isEmpty())
	    view =true;
	
	this.cat = new Categories();
	this.confirme = false;
    }

   

    @Override
    public void delete() {
	//RequestContext context = RequestContext.getCurrentInstance();
	boolean delete = false;

	try {
	    if (cat.getProduitses().isEmpty()) {
		categorieListBean.getCategorieService().remove(cat);
		delete = true;
	    } else {
		//context.execute("PF('comfirmeDialog').show()");
		if (confirme) {
		    categorieListBean.getCategorieService().remove(cat);
		    delete = true;
		}
	    }

	    // Vue
	    if (delete) {
		categorieListBean.getList().remove(cat);
	    }

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void create() {
	try {
	    cat.setEntreprises(MyHttpSession.getUser().getEntreprises());
	    categorieListBean.getCategorieService().setObject(cat);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Vue
	categorieListBean.getList().add(cat);

	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('catOverlay').hide()"); //$NON-NLS-1$

    }

    @Override
    public void view(SelectEvent event) {
	cat = (Categories) event.getObject();
    }

    @Override
    public void close() {
	cat = new Categories();
    }

    @Override
    public void update() {
	// Persistance
	categorieListBean.getCategorieService();

	// Vue
	if (categorieListBean.getList().contains(cat)) {
	    for (Categories cat : categorieListBean.getList()) {
		categorieListBean.getList().remove(cat);
		categorieListBean.getList().add(cat);
	    }
	}

    }

    @Override
    public Categories searchByNom(String nom) {
	for (Categories cat : categorieListBean.getList()) {
	    if (cat.getNom().startsWith(nom)) {
		return cat;
	    }
	}
	return null;
    }

   

    @Override
    public Categories searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public CategorieListBean getCategorieListBean() {
	return categorieListBean;
    }

    public void setCategorieListBean(CategorieListBean categorieListBean) {
	this.categorieListBean = categorieListBean;
    }

     public boolean isConfirme() {
	return confirme;
    }

    public void setConfirme(boolean confirme) {
	this.confirme = confirme;
    }

    public Categories getCat() {
	return cat;
    }

    public void setCat(Categories cat) {
	this.cat = cat;
    }
    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}
