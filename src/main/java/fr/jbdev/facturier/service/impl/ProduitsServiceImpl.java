package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.dao.ProduitsDao;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.ProduitsService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("produitsService")
public class ProduitsServiceImpl extends BaseServiceImpl<Produits> implements
	ProduitsService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    ProduitsDao produitDao;

    @Autowired
    public ProduitsServiceImpl(final BaseDao<Produits, Integer> dao) {
	this.setDao(dao);
    }

}
