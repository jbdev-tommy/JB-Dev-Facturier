package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Adresses;
import fr.jbdev.facturier.dao.AdressesDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class AdressesDaoImpl extends BaseDaoImpl<Adresses, Integer> implements
	AdressesDao{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
