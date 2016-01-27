package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Achats;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.AchatsService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("achatsService")
public class AchatsServiceImpl extends BaseServiceImpl<Achats> implements
	AchatsService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public AchatsServiceImpl(final BaseDao<Achats, Integer> dao) {
	this.setDao(dao);
    }

}
