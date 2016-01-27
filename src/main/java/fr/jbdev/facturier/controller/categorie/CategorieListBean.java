package fr.jbdev.facturier.controller.categorie;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Categories;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.CategoriesService;

@SessionScoped
@ManagedBean(name = "categorieListBean", eager = true)
public class CategorieListBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{categoriesService}")
    private CategoriesService categorieService;

    private Set<Categories> list;

    @PostConstruct
    public void init() {
	list = MyHttpSession.getUser().getEntreprises().getCategorieses();
    }

    public CategoriesService getCategorieService() {
	return categorieService;
    }

    public void setCategorieService(CategoriesService categorieService) {
	this.categorieService = categorieService;
    }

    public Set<Categories> getList() {
	return list;
    }

    public void setList(Set<Categories> list) {
	this.list = list;
    }

}
