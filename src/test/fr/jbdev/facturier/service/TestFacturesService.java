package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Factures;
import fr.jbdev.domaine.Produits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestFacturesService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("facturesService")
    private FacturesService facturesService;

    @Test
    public void add() {
	try {
	    // Clients
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");

	    // Devis
	    Devis devis = user.getEntreprises().getDevises().iterator().next();

	    // Facture
	    Factures facture = new Factures();
	    facture.setDevis(devis);
	    facture.setNumero("1");
	    facture.setDateFacture(new Date()); // Date du jour system

	    // Persistance
	    facturesService.setObject(facture);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Test
    public void list() {
	try {
	    // Clients
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");

	    // List Factures
	    Set<Devis> listDevis = user.getEntreprises().getDevises();

	    // List Map.Entry pour JSF
	    List<Map.Entry<Factures, Double>> list = new ArrayList<Map.Entry<Factures, Double>>(); // A
												   // voir
												   // si
												   // double
												   // ou
												   // string
												   // pour
												   // le
												   // JSF
	    Map<Factures, Double> map = new HashMap<Factures, Double>();

	    for (Devis devis : listDevis) {
		Set<Factures> factures = devis.getFactureses();

		for (Factures facture : factures) {
		    Set<DevisProduits> produits = facture.getDevis()
			    .getDevisProduitses();
		    double somme = 0;
		    for (DevisProduits pdt : produits) {
			for (Produits pdts : pdt.getCategories()
				.getProduitses())
			    somme = pdts.getPrixHt() * pdt.getQuantite();
		    }
		    map.put(facture, somme);
		}
	    }
	    list.addAll(map.entrySet());

	    // Test
	    if (list.isEmpty())
		assertTrue(false);
	    else
		assertTrue(true);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
