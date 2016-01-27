package fr.jbdev.facturier.controller.tva;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.jbdev.domaine.Tva;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.TvaService;

@ApplicationScoped
@ManagedBean(name = "tvaListBean", eager = true)
public class TvaListBean {

    private List<Tva> list;

    @ManagedProperty(value = "#{tvaService}")
    private TvaService taxeService;

    @PostConstruct
    public void init() {
	try {
	    list = taxeService.getAllObject(Tva.class);
	} catch (final ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    public List<Tva> getList() {
	return list;
    }

    public void setList(List<Tva> list) {
	this.list = list;
    }

    public TvaService getTaxeService() {
	return taxeService;
    }

    public void setTaxeService(TvaService taxeService) {
	this.taxeService = taxeService;
    }

}
