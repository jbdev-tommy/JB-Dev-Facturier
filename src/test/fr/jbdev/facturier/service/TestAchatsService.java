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

import fr.jbdev.domaine.Achats;
import fr.jbdev.domaine.Commandes;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestAchatsService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("achatsService")
    private AchatsService achatsService;

    @Test
    public void add() {

	// Toujours créer une commande avant de créer un achat

	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // Commande
	    Set<Commandes> list = new HashSet<Commandes>();
	    list = user.getEntreprises().getCommandeses();
	    if (!list.isEmpty()) {
		for (Commandes cmd : list) {

		    // Achats
		    Achats achat = new Achats();
		    achat.setCommandes(cmd);
		    achat.setDateAchats(new Date());
		    achat.setAccomte(10); // Accompte sotie
		    achatsService.setObject(achat);
		}
	    }
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void list() {
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");
	    Set<Commandes> cmds = user.getEntreprises().getCommandeses();
	    Set<Achats> achats = new HashSet<Achats>();

	    for (Commandes cmd : cmds) {
		achats.addAll(cmd.getAchatses());
	    }
	    if (achats.isEmpty())
		assertTrue(false);
	    else
		assertTrue(true);

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}
