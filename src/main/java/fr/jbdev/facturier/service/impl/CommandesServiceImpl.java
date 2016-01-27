package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Commandes;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.CommandesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("commandesService")
public class CommandesServiceImpl extends BaseServiceImpl<Commandes> implements
	CommandesService {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public CommandesServiceImpl(final BaseDao<Commandes, Integer> dao) {
	this.setDao(dao);
    }

}
