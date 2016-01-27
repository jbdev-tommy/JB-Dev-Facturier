package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Clients;
import fr.jbdev.domaine.Factures;
import fr.jbdev.facturier.dao.FactureDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class FactureDaoImpl extends BaseDaoImpl<Factures, Integer> implements
	FactureDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void add(Factures facture) {
	currentSession().save(facture);
	Clients client = facture.getDevis().getClients();
	currentSession().update(client);
    }
}
