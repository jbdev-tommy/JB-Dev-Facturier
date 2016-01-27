package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Tva;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.TvaService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("tvaService")
public class TvaServiceImpl extends BaseServiceImpl<Tva> implements TvaService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public TvaServiceImpl(final BaseDao<Tva, Integer> dao) {
	this.setDao(dao);
    }
}
