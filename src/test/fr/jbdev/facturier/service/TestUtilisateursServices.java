package fr.jbdev.facturier.service;

import static org.junit.Assert.assertFalse;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.Entreprises;
import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.domaine.Personnes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestUtilisateursServices {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("myUserDetailsService")
    private MyUserDetailService userDetailService;

    @Test
    public void testAdd() throws ObjectNullException {
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
	entreprise.setNom("JB-Dev");
	entreprise.setAdresses(adresse);
	entreprise.setCodeApe("454J");
	entreprise.setDateDeCreation(new Date());
	entreprise.setFormesJuridiques(forme);
	entreprise.setIdentifiantTva("FR53386904333");
	entreprise.setNumSiret("53386904600036");
	entreprise.setSolgan("Art et décorations");
	map.put(Entreprises.class, entreprise);

	// Créer personne
	Personnes personne = new Personnes();
	personne.setAdresses(adresse);
	personne.setDateNaissance(new Date());
	personne.setNom("Bochard");
	personne.setPrenom("Jonathan");
	map.put(Personnes.class, personne);

	// Créer un utilisateur
	Utilisateurs user = new Utilisateurs();
	user.setEmail("bochard.jonathan@jbdev.fr");
	user.setRole("ROLE_USER");
	user.setEnabled(true);
	user.setEntreprises(entreprise);
	user.setPassWord("Bonjour777");
	user.setPersonnes(personne);

	list.addAll(map.entrySet());

	if (userService.findUserByMail("bochard.jonathan@jbdev.fr") == null)
	    userService.setObject(list, user);

    }

    @Test
    public void testUpdate() {
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	try {
	    Utilisateurs userTestUpdate = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");

	    if (userTestUpdate != null) {

		// Créer une adresse
		Adresses adresse = new Adresses();
		adresse.setNumAdresse("1");

		// Forme juridique
		FormesJuridiques forme = new FormesJuridiques();
		forme.setNumForme(1);

		// Créer entreprise
		Entreprises entreprise = userTestUpdate.getEntreprises();
		entreprise.setNom("JB-Dev");
		entreprise.setAdresses(adresse);
		entreprise.setCodeApe("454J");
		entreprise.setDateDeCreation(new Date());
		entreprise.setFormesJuridiques(forme);
		entreprise.setIdentifiantTva("FR53386904333");
		entreprise.setNumSiret("53386904600036");
		entreprise.setSolgan("Art et décorations");
		map.put(Entreprises.class, entreprise);

		// Créer personne
		Personnes personne = userTestUpdate.getPersonnes();
		personne.setAdresses(adresse);
		personne.setDateNaissance(new Date());
		personne.setNom("Bochard");
		personne.setPrenom("Jonathan");
		map.put(Personnes.class, personne);

	    } else
		assertFalse(true);

	    userService.update(list, userTestUpdate);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void testFind() {
	try {
	    Utilisateurs user = userService
		    .findUserByMail("bochard.jonathan@jbdev.fr");
	    user.getEntreprises().getAdresses().getVille();
	    user.getPersonnes().getNom();
	    user.getPersonnes().getClientses();
	    Set<Devis> devisSet = user.getEntreprises().getDevises();
	    user.getEntreprises().getEntrepotses();
	    user.getEntreprises().getFournisseursesForNumSiret();

	    if (!devisSet.isEmpty())
		devisSet.iterator().next().getDevisProduitses();
	    user.getTodos();

	    assertTrue(user != null);
	} catch (ObjectNullException e) {
	    ;

	    e.printStackTrace();
	}
    }

    @Test
    public void testMyUserDetailService() {
	UserDetails detail = userDetailService
		.loadUserByUsername("bochard.jonathan@jbdev.fr");
	assertTrue(!detail.getAuthorities().isEmpty());
    }

    @Test
    public void testObject() {
	Utilisateurs user = new Utilisateurs();
	Object obj = user;
	System.out
		.println("--------------------------- Test Class name Object ------------------------");
	System.out.println(" Object Class : "
		+ obj.getClass().getCanonicalName());

	System.out.println(" Object Runtime : " + obj.getClass());
    }
}
