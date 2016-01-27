package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Commandes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestCommandeService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("commandesService")
    private CommandesService commandeService;

    @Test
    public void add() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // Commande
	    Commandes cmd = new Commandes();
	    cmd.setEntreprises(user.getEntreprises());
	    cmd.setDateCommande(new Date());

	    commandeService.setObject(cmd);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void list() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // List
	    Set<Commandes> list = new HashSet<Commandes>();
	    list = user.getEntreprises().getCommandeses();
	    if (list.isEmpty())
		assertTrue(false);
	    else
		assertTrue(true);

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void update() {
	// Peut on updater une commande ?
    }
}
