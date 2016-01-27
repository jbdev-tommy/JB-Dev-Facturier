package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.AdressesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("adressesService")
public class AdressesServiceImpl extends BaseServiceImpl<Adresses> implements
	AdressesService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public AdressesServiceImpl(final BaseDao<Adresses, Integer> dao) {
	this.setDao(dao);
    }

}
