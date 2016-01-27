package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestTypeDePaiments {

    @Autowired
    @Qualifier("typeDePaimentsService")
    private TypeDePaimentsService typeDePaimentService;

    @Test
    public void list() {
	try {
	    assertTrue(!typeDePaimentService.getAllObject(TypeDePaiment.class)
		    .isEmpty());
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
