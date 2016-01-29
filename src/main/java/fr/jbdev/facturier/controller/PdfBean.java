package fr.jbdev.facturier.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.UserService;

@SessionScoped
@ManagedBean(name = "pdfBean", eager = false)
public class PdfBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    // Evite les doublons
    private Set<String> list;

    private String path;
    private String model;

    @PostConstruct
    public void init() {
	ClassLoader classLoader = getClass().getClassLoader();
	model = MyHttpSession.getUser().getEntreprises().getModelPdf();

	// Initialisation de la liste des models ( prochaine version mise en
	// place du serveur JasperReport)
	list = new HashSet<String>();
	path = classLoader.getResource("jasperReport").getPath();

	for (String fichier : new File(classLoader.getResource("jasperReport")
		.getPath()).list()) {
	    if (fichier.endsWith("jrxml"))
		list.add(fichier.substring(0, fichier.indexOf(".")));
	}

    }

    @SuppressWarnings("rawtypes")
    public void selected() {
       System.out.println(" Model :" + model);
	// Persistance
	// Utilistaire pour la persistance
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();
	Utilisateurs user = MyHttpSession.getUser();

	Entreprises entreprise = user.getEntreprises();
	entreprise.setModelPdf(model);

	map.put(Entreprises.class, entreprise);

	// On regroupe
	list.addAll(map.entrySet());

	// Persistance
	try {
	    userService.update(list, user);
	} catch (ObjectNullException e) {
	    // TODO Message
	    e.printStackTrace();
	}

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Set<String> getList() {
	return list;
    }

    public void setList(Set<String> list) {
	this.list = list;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

}
