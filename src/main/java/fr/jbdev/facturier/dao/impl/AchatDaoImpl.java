package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Achats;
import fr.jbdev.facturier.dao.AchatDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class AchatDaoImpl extends BaseDaoImpl<Achats, Integer> implements
	AchatDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
