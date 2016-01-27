package fr.jbdev.facturier.service;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Entrepots;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestEntrepotsService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("entrepotsService")
    private EntrepotsService entrepotsService;

    @Test
    public void testAdd() {
	Utilisateurs user;
	try {

	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    // Produits enregistrer avant l'entrepot ( à faire dans la partie
	    // métier )
	    Produits pdt = new Produits();
	    pdt.setNumProduits(1);

	    // Créer entrepot
	    Entrepots entrepot = new Entrepots();

	    entrepotsService.setObject(entrepot);

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testList() {
	Utilisateurs user;
	try {

	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    Set<Entrepots> entrepots = user.getEntreprises().getEntrepotses();

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}
