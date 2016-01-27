package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.dao.ProduitsDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class ProduitsDaoImpl extends BaseDaoImpl<Produits, Integer> implements
	ProduitsDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
