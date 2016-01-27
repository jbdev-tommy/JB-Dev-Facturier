package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestFormeJuridiqueService {

    @Autowired
    @Qualifier("formesJuridiquesService")
    private FormesJuridiquesService formeJuridiqueService;

    @Test
    public void testList() {
	try {
	    List<FormesJuridiques> list = formeJuridiqueService
		    .getAllObject(FormesJuridiques.class);
	    assertTrue(!list.isEmpty());

	    System.out
		    .println(" ------------------------ Forme Juridique --------------------");
	    for (FormesJuridiques obj : list) {
		System.out.println(" Forme :  " + list);
	    }

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}
