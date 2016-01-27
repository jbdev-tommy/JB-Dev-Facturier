package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.facturier.dao.FormeJuridiqueDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class FormeJuridiqueDaoImp extends
	BaseDaoImpl<FormesJuridiques, Integer> implements FormeJuridiqueDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
