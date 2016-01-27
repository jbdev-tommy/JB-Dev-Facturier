package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Tva;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class testTvaService {

    @Autowired
    @Qualifier("tvaService")
    private TvaService tvaService;

    @Test
    public void list() {
	try {
	    List<Tva> tvas = tvaService.getAllObject(Tva.class);
	    if (tvas.isEmpty())
		assertTrue(false);
	    else
		assertTrue(true);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
