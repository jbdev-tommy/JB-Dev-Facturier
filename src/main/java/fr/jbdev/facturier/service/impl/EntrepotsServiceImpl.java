package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Entrepots;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.EntrepotsService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("entrepotsService")
public class EntrepotsServiceImpl extends BaseServiceImpl<Entrepots> implements
	EntrepotsService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public EntrepotsServiceImpl(final BaseDao<Entrepots, Integer> dao) {
	this.setDao(dao);
    }
}
