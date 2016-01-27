package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.facturier.dao.TypePaimentDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class TypePaimentDaoImpl extends BaseDaoImpl<TypeDePaiment, Integer>
	implements TypePaimentDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
