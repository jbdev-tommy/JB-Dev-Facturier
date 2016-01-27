package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Clients;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.ClientsService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("clientsService")
public class ClientsServiceImpl extends BaseServiceImpl<Clients> implements
	ClientsService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public ClientsServiceImpl(final BaseDao<Clients, Integer> dao) {
	this.setDao(dao);
    }

}
