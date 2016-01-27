package fr.jbdev.facturier.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.AdressesService;

/**
 * @author Jonathan Bochard
 * 
 *         Bean de gestion d'adresse ( postale )
 */
@ApplicationScoped
@ManagedBean(name = "adressesListBean", eager = true)
public class AdressesListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{adressesService}")
    private AdressesService adresseService;

    private List<Adresses> adresses;

    @PostConstruct
    public void init() {

	try {

	    adresses = adresseService.getAllObject(Adresses.class);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Liste de toute les voies de la base de donnée
     * 
     * @param query
     * @return String
     */
    public List<Adresses> adressesQuery(final String query) {
	final List<Adresses> adressesQuery = new ArrayList<Adresses>();

	for (Adresses adresse : adresses) {
	    String tmp1 = adresse.getNumero() + " " + adresse.getVoie() + " "
		    + adresse.getCodePostal() + " " + adresse.getVille();
	    String tmp2 = adresse.getNumero() + "," + adresse.getVoie() + " "
		    + adresse.getCodePostal() + " " + adresse.getVille();

	    if (tmp1.toLowerCase().startsWith(query)
		    || tmp2.toLowerCase().startsWith(query)) {
		adressesQuery.add(adresse);
	    }

	}
	return adressesQuery;
    }

    public AdressesService getAdresseService() {
	return adresseService;
    }

    public void setAdresseService(AdressesService adresseService) {
	this.adresseService = adresseService;
    }

    public List<Adresses> getAdresses() {
	return adresses;
    }

    public void setAdresses(List<Adresses> adresses) {
	this.adresses = adresses;
    }

}
