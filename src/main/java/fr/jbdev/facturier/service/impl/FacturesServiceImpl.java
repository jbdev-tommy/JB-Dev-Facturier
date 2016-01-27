package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Factures;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.FacturesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("facturesService")
public class FacturesServiceImpl extends BaseServiceImpl<Factures> implements
	FacturesService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public FacturesServiceImpl(final BaseDao<Factures, Integer> dao) {
	this.setDao(dao);
    }

}
