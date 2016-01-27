package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Categories;
import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Entrepots;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Tva;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestDevisService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("devisService")
    private DevisService devisService;

    @Test
    public void add() {
	try {
	    // Clients
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");
	    Clients client = user.getEntreprises().getClientses().iterator()
		    .next();
	    Map<Categories, List<Produits>> map = new HashMap<Categories, List<Produits>>();

	    // Produits selectionné par l'utilisateur
	    Set<Entrepots> entrepots = user.getEntreprises().getEntrepotses();
	    List<Produits> pdts = new ArrayList<Produits>();

	    // Test de la liste de produit
	    if (pdts.isEmpty())
		assertTrue(false);

	    // Affiche la liste des produits
	    System.out
		    .println(" --------------------- List des produits par categories -------------------------------");
	    for (Categories cat : map.keySet()) {
		System.out.println("* * * Categorie : " + cat.getNom());
		for (Produits pdt : map.get(cat)) {
		    System.out.println("* * * * * * Produit nom : "
			    + pdt.getNom());
		}
	    }

	    // Taxes par produits selectionner par l'utilisateur
	    Tva tva = new Tva();
	    tva.setNumTaxe(1);

	    // List produit devis quantite + taxe
	    Set<DevisProduits> devisProduits = new HashSet<DevisProduits>();
	    for (Produits pdt : pdts) {
		DevisProduits tmp = new DevisProduits();
		// tmp.setProduits(pdt);
		tmp.setQuantite(10);
		tmp.setRemise(0);
		tmp.setTva(tva);
		devisProduits.add(tmp);
	    }

	    // Devis
	    Devis devis = new Devis();
	    devis.setClients(client);
	    devis.setDateDevis(new Date());
	    devis.setDateFinDevis(new Date());
	    devis.setEntreprises(user.getEntreprises());
	    devis.setNumero("1");
	    devis.setRemiseGlobal((float) 0);
	    devis.setDevisProduitses(devisProduits);

	    // Persistance
	    devisService.setObject(devis);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Test
    public void list() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    Set<Devis> devis = user.getEntreprises().getDevises();
	    if (!devis.isEmpty()) {
		for (Devis devi : devis) {
		    Set<DevisProduits> list = devi.getDevisProduitses();
		    System.out.println(" Devis num : " + devi.getNumero());
		    System.out.println(" Devis date : " + devi.getDateDevis());

		}
	    }

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Test
    public void remove() {
	try {

	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");

	    Set<Devis> devis = user.getEntreprises().getDevises();
	    if (!devis.isEmpty()) {
		for (Devis devi : devis) {
		    if (devi.getFactureses().isEmpty())
			devisService.remove(devi);
		}
	    }

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Test
    public void update() {
	// Un devis est il réellement updatable ?
    }
}
