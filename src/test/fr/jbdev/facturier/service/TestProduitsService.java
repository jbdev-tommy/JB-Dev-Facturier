package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Categories;
import fr.jbdev.domaine.Entrepots;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestProduitsService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("produitsService")
    private ProduitsService produitsService;

    @Autowired
    @Qualifier("entrepotsService")
    private EntrepotsService entrepotsService;

    @Test
    public void testAdd() {
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	// Get User
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr"); // Remplacé
								  // par
								  // HttpSession
								  // dans la jsf

	    // Get Categoire
	    Categories cat = new Categories();
	    cat.setNumCategorie(1);

	    // Nouveau Produits
	    Produits pdt = new Produits();
	    pdt.setEntreprises(user.getEntreprises());

	    pdt.setDescription("Rien");
	    pdt.setNom("Services");
	    pdt.setPrixHt(10);
	    pdt.setService(false); // inutilise

	    // persitance
	    produitsService.setObject(pdt);

	    // Nouveau Service
	    pdt = new Produits();
	    pdt.setDescription("Rien deux");
	    pdt.setNom("Services Entreprise");
	    pdt.setPrixHt(10);
	    pdt.setEntreprises(user.getEntreprises());

	    produitsService.setObject(pdt);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Test
    public void testList() {
	// Get User
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr"); // Remplacé
								  // par
								  // HttpSession
								  // dans la jsf

	    // Produits stocké
	    Set<Entrepots> entrepots = user.getEntreprises().getEntrepotses();

	    // Services
	    Set<Produits> produits = user.getEntreprises().getProduitses();
	    assertTrue(!produits.isEmpty());
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
