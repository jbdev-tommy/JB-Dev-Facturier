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
import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Personnes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestClientService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("clientsService")
    private ClientsService clientService;

    @Test
    public void testAdd() {
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    // Créer une adresse
	    Adresses adresse = new Adresses();
	    adresse.setNumAdresse("1");

	    // Créer personne
	    Personnes personne = new Personnes();
	    personne.setAdresses(adresse);
	    personne.setDateNaissance(new Date());
	    personne.setNom("Bochard");
	    personne.setPrenom("Jonathan");
	    map.put(Personnes.class, personne);

	    Clients client = new Clients();
	    client.setEmail("email@client");
	    client.setEntreprises(user.getEntreprises());
	    client.setPersonnes(personne);
	    client.setSolde((double) 0);

	    list.addAll(map.entrySet());
	    clientService.setObject(list, client);

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void update() {
	List<Entry<Class, Object>> list = new ArrayList<Entry<Class, Object>>();
	Map<Class, Object> map = new HashMap<Class, Object>();

	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    Clients client = user.getEntreprises().getClientses().iterator()
		    .next();

	    // Créer une adresse
	    Adresses adresse = new Adresses();
	    adresse.setNumAdresse("1");

	    // Créer personne
	    Personnes personne = client.getPersonnes();
	    personne.setAdresses(adresse);
	    personne.setDateNaissance(new Date());
	    personne.setNom("Bochard");
	    personne.setPrenom("Jonathan");
	    map.put(Personnes.class, personne);

	    client.setEmail("email@client2");
	    client.setEntreprises(user.getEntreprises());
	    client.setPersonnes(personne);

	    list.addAll(map.entrySet());

	    clientService.update(list, client);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void list() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    Set<Clients> list = user.getEntreprises().getClientses();
	    assertTrue(!list.isEmpty());

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void delete() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    Set<Clients> list = user.getEntreprises().getClientses();
	    if (!list.isEmpty()) {
		Clients client = list.iterator().next();
		if (client.getDevises().isEmpty())
		    clientService.remove(client);
	    }
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}