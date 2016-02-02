package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Categories;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestCategoriesService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("categoriesService")
    private CategoriesService categoriesService;

   /* @Test
    public void testAdd() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // Categorie Add
	    Categories categorie = new Categories();
	    categorie.setDescription("Test Cat");
	    categorie.setNom("Categorie 1");

	    categoriesService.setObject(categorie);

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

    }*/

    @Test
    public void testList() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    Set<Categories> cats = user.getEntreprises().getCategorieses();
	    for(Categories cat : cats) {
		for(Produits pdt : cat.getProduitses()) {
		    System.out.println(" Produits : " + pdt.getNom());
		}
	    }
	    assertTrue(!cats.isEmpty());

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
/*
    // @Test
    public void testDelete() {
	Categories categorie = new Categories();
	categorie.setNumCategorie(11);

	try {
	    // Delete CASCADE supprime aussi les produits associé :-)
	    categoriesService.remove(categorie);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    */
}
