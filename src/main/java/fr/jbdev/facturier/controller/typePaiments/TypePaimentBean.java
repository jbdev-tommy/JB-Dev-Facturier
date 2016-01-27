package fr.jbdev.facturier.controller.typePaiments;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.jbdev.domaine.TypeDePaiment;

@ViewScoped
@ManagedBean(name = "typePaimentBean", eager = false)
public class TypePaimentBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{typePaimentListBean}")
    private TypePaimentListBean listBean;

    private TypeDePaiment type;

    @PostConstruct
    public void init() {
	type = new TypeDePaiment();
    }

    public TypePaimentListBean getListBean() {
	return listBean;
    }

    public void setListBean(TypePaimentListBean listBean) {
	this.listBean = listBean;
    }

    public TypeDePaiment getType() {
	return type;
    }

    public void setType(TypeDePaiment type) {
	this.type = type;
    }

}
