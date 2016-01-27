package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.facturier.dao.DevisDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class DevisDaoImpl extends BaseDaoImpl<Devis, Integer> implements
	DevisDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void add(Devis devis) {
	currentSession().save(devis);
	for (DevisProduits devisPdt : devis.getDevisProduitses()) {
	    currentSession().save(devisPdt);
	}
    }
}
