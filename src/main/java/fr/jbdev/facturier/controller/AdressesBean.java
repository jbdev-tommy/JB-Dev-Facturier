package fr.jbdev.facturier.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "adressesBean", eager = false)
public class AdressesBean {

    @ManagedProperty(value = "#{adressesListBean}")
    private AdressesListBean adressesListBean;

    @SuppressWarnings("unused")
    private Adresses adresse;

    @PostConstruct
    public void init() {
	adresse = new Adresses();
    }

    /**
     * Renvoie si l'adresse existe si non la créé et la renvoie
     * 
     * @return adresse
     */
    public Adresses getAdresse() {
	for (Adresses adresse : adressesListBean.getAdresses()) {
	    if (adresse.equals(adresse)) {
		return adresse;
	    } else {

		// Persistance
		try {
		    adressesListBean.getAdresseService().setObject(adresse);
		} catch (ObjectNullException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return adresse;
	    }
	}
	return null;
    }

    public AdressesListBean getAdressesListBean() {
	return adressesListBean;
    }

    public void setAdressesListBean(AdressesListBean adressesListBean) {
	this.adressesListBean = adressesListBean;
    }

    public void setAdresse(Adresses adresse) {
	this.adresse = adresse;
    }

}
