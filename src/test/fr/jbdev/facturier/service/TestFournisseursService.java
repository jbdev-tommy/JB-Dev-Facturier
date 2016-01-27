package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import fr.jbdev.domaine.Adresses;
import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestFournisseursService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("fournisseursService")
    private FournisseursService fournisseursService;

    // @Test
    public void testAdd() {
	// Trouver utilisateur pour lié à son fournisseur
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr"); // Remplacé
								  // par
								  // HttpSession
								  // dans la jsf

	    List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	    Map<Class, Object> map = new HashMap<Class, Object>();

	    // Créer une adresse
	    Adresses adresse = new Adresses();
	    adresse.setNumAdresse("1");

	    // Forme juridique
	    FormesJuridiques forme = new FormesJuridiques();
	    forme.setNumForme(1);

	    // Créer entreprise
	    Entreprises entreprise = new Entreprises();
	    entreprise.setNom("Digicube");
	    entreprise.setAdresses(adresse);
	    entreprise.setCodeApe("454J");
	    entreprise.setDateDeCreation(new Date());
	    entreprise.setFormesJuridiques(forme);
	    entreprise.setIdentifiantTva("FR53386904334");
	    entreprise.setNumSiret("53386904600036");
	    entreprise.setSolgan("Hebergement");
	    map.put(Entreprises.class, entreprise);

	    // Fournisseurs
	    Fournisseurs fournisseur = new Fournisseurs();
	    fournisseur.setEntreprisesByNumSiret(entreprise);
	    fournisseur.setEmail("email");
	    fournisseur.setEntreprisesByNumSiretEntreprises(user
		    .getEntreprises()); // Client du fournisseur
	    fournisseur.setDescription("Rien");

	    list.addAll(map.entrySet());

	    // Persistance
	    fournisseursService.setObject(list, fournisseur);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Test
    public void testlist() {
	// Trouver utilisateur pour lié à son fournisseur
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr"); // Remplacé
								  // par
								  // HttpSession
								  // dans la jsf
	    Set<Fournisseurs> fournisseurs = user.getEntreprises()
		    .getFournisseursesForNumSiretEntreprises(); // Fournisseurs
								// de
								// l'utilisateur
								// ( Entreprise)

	    assertTrue(!fournisseurs.isEmpty());
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    // @Test
    public void testDelete() {
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr"); // Remplacé
								  // par
								  // HttpSession
								  // dans la jsf
	    Set<Fournisseurs> fournisseurs = user.getEntreprises()
		    .getFournisseursesForNumSiretEntreprises(); // Fournisseurs
								// de
								// l'utilisateur
								// ( Entreprise)

	    if (!fournisseurs.isEmpty()) {
		fournisseursService.remove(fournisseurs.iterator().next());
	    }
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
