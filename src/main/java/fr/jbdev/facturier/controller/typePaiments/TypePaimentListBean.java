package fr.jbdev.facturier.controller.typePaiments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.TypeDePaimentsService;

@ApplicationScoped
@ManagedBean(name = "typePaimentListBean", eager = true)
public class TypePaimentListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<TypeDePaiment> list;

    @ManagedProperty(value = "#{typeDePaimentsService}")
    private TypeDePaimentsService typeDePaimentService;

    @PostConstruct
    public void init() {
	try {
	    list = new ArrayList<TypeDePaiment>();
	    list = typeDePaimentService.getAllObject(TypeDePaiment.class);
	} catch (final ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    public List<TypeDePaiment> getList() {
	return list;
    }

    public void setList(final List<TypeDePaiment> list) {
	this.list = list;
    }

    public TypeDePaimentsService getTypeDePaimentService() {
	return typeDePaimentService;
    }

    public void setTypeDePaimentService(
	    final TypeDePaimentsService typeDePaimentService) {
	this.typeDePaimentService = typeDePaimentService;
    }

}
