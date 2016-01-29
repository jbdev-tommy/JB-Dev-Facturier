package fr.jbdev.facturier.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class PdfBeanTest {

    @Test
    public void getFilesJasper() {
	ClassLoader classLoader = getClass().getClassLoader();
	String[] list;
		list = new File(classLoader.getResource("jasperReport").getPath()).list();
		System.out.println("Path : " + classLoader.getResource("jasperReport").getPath());
		
		for(String test : list) {
		    if(test.endsWith("jasper"))
		    	System.out.println(" Fichier : " + test.substring(0, test.indexOf(".")));
		}
		
		System.out.println(" Path : " + ClassLoader.getSystemResource("jasperReport/reportBleu.jasper").getFile());
		System.out.println(" Path : " + ClassLoader.getSystemResource("jasperReport/images/logoPerso.png").getFile());
	assertTrue(list.length > 0);
    }
}
