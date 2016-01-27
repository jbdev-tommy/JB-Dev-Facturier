package fr.jbdev.facturier.service;

import java.util.List;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.generic.BaseService;

public interface DevisService extends BaseService<Devis> {

    void setObject(Devis devis, List<DevisProduits> pdts)
	    throws ObjectNullException;

}
