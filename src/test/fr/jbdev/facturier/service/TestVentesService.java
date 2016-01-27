package fr.jbdev.facturier.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Factures;
import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestVentesService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("ventesService")
    private VentesService venteService;

    @Test
    public void add() {
	// Facture
	Factures fact = new Factures();
	fact.setNumFacture(1);

	// Type de paiment
	TypeDePaiment typePaiment = new TypeDePaiment();
	typePaiment.setNumTypeDePaiment(1);

	// Ventes
	Vente vente = new Vente();
	vente.setAccompte(10);
	vente.setFactures(fact);
	vente.setTypeDePaiment(typePaiment);

	try {
	    venteService.setObject(vente);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}
