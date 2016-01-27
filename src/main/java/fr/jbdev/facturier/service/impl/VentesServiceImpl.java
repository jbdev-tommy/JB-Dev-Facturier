package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.VentesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("ventesService")
public class VentesServiceImpl extends BaseServiceImpl<Vente> implements
	VentesService {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public VentesServiceImpl(final BaseDao<Vente, Integer> dao) {
	this.setDao(dao);
    }

}
