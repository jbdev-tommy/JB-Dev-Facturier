package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Clients;
import fr.jbdev.facturier.dao.ClientsDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class ClientsDaoImpl extends BaseDaoImpl<Clients, Integer> implements
	ClientsDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
