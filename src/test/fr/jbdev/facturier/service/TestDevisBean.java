package fr.jbdev.facturier.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestDevisBean {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Test
    public void jasperReport() throws ObjectNullException, IOException {
	Utilisateurs user = userService
		.findUserByMail("bochard.jonathan@jbdev.fr");
	Set<Devis> devisList = user.getEntreprises().getDevises();
	Devis devis = devisList.iterator().next();

	// Logo utilisateur byte[] to BufferedImage
	InputStream stream = new ByteArrayInputStream(user.getEntreprises()
		.getLogo());
	BufferedImage img = null;

	try {
	    img = ImageIO.read(stream);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Paramétre map
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("devis", devis);
	model.put("entreprise", user.getEntreprises());
	model.put("personne", devis.getClients().getPersonnes());
	model.put("logo", img);

	// DataSet PDF
	List<DevisProduits> list = new ArrayList<DevisProduits>(
		devis.getDevisProduitses());
	System.out.println(" List vide ? " + list.isEmpty());
	System.out.println(" Map vide ? " + model.isEmpty());
	System.out.println(" List produits vide ? "
		+ list.get(0).getCategories().getProduitses().isEmpty());
	try {
	    // Jasper Report
	    ClassLoader classLoader = getClass().getClassLoader();
	    JasperReport report = (JasperReport) JRLoader
		    .loadObject(classLoader
			    .getResource("jasperReport/Simple_Blue_Table_Based.jasper"));

	    JasperPrint jasperPrint = JasperFillManager.fillReport(report,
		    model, new JRBeanCollectionDataSource(list));
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

	    File file = File.createTempFile("report-", ".pdf");
	    FileOutputStream fileOuputStream = new FileOutputStream(file);
	    fileOuputStream.write(JasperExportManager
		    .exportReportToPdf(jasperPrint));
	    fileOuputStream.close();

	} catch (FileNotFoundException | JRException e) {

	    e.printStackTrace();
	}

    }
}
