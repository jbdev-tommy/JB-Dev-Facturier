package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.dao.VenteDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class VentesDaoImpl extends BaseDaoImpl<Vente, Integer> implements
	VenteDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void add(Vente vente) {

	currentSession().save(vente);
	double current = vente.getFactures().getDevis().getClients().getSolde();
	vente.getFactures().getDevis().getClients()
		.setSolde(current - vente.getAccompte());

	currentSession().update(vente.getFactures().getDevis().getClients());
    }

}
