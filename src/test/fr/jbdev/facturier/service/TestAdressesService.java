package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestAdressesService {

    @Autowired
    @Qualifier("adressesService")
    private AdressesService adressesService;

    @Test
    public void listAdresses() {
	try {
	    List<Adresses> list = adressesService.getAllObject(Adresses.class);
	    assertTrue(!list.isEmpty());
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }
}
