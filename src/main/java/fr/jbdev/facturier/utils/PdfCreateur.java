package fr.jbdev.facturier.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Factures;
import fr.jbdev.domaine.Utilisateurs;

public class PdfCreateur {

    private Utilisateurs user;
    private Devis devis;
    private Factures facture;

    public PdfCreateur(Utilisateurs user, Devis devis) {
	this.devis = devis;
	this.user = user;
    }

    public PdfCreateur(Utilisateurs user, Factures facture) {
	this.facture = facture;
	this.user = user;
    }

    public byte[] create() {

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
	List<DevisProduits> list ;
	if (devis != null) {
	    model.put("devis", devis);
	    model.put("personne", devis.getClients().getPersonnes());
	    // DataSet PDF
	    list = new ArrayList<DevisProduits>(
			devis.getDevisProduitses());
	}else {
	    model.put("facture", facture);
	    model.put("personne", facture.getDevis().getClients().getPersonnes());
	 // DataSet PDF
	    list = new ArrayList<DevisProduits>(
			facture.getDevis().getDevisProduitses());
	}
	model.put("entreprise", user.getEntreprises());
	
	model.put("logo", img);

	
	try {
	    // Jasper Report
	    ClassLoader classLoader = getClass().getClassLoader();
	    JasperReport report = (JasperReport) JRLoader
		    .loadObject(classLoader
			    .getResource("jasperReport/Simple_Blue_Table_Based.jasper"));
	    JasperPrint jasperPrint = JasperFillManager.fillReport(report,
		    model, new JRBeanCollectionDataSource(list));
	    return JasperExportManager.exportReportToPdf(jasperPrint);

	} catch (JRException e) {

	    e.printStackTrace();
	}
	return null;
    }
}
