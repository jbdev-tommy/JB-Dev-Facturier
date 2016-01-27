package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Entrepots;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.dao.EntrepotsDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class EntrepotsDaoImpl extends BaseDaoImpl<Entrepots, Integer> implements
	EntrepotsDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void update(Entrepots entrepot) {
	currentSession().update(entrepot);
	for(Produits pdt : entrepot.getProduitses()) {
	    pdt.setEntrepots(entrepot);
	    currentSession().update(pdt);
	}
    }
}
