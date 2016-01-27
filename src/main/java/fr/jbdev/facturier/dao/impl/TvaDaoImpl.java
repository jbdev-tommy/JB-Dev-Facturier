package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Tva;
import fr.jbdev.facturier.dao.TvaDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class TvaDaoImpl extends BaseDaoImpl<Tva, Integer> implements TvaDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
