package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.facturier.dao.FournisseursDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class FournisseursDaoImpl extends BaseDaoImpl<Fournisseurs, Integer>
	implements FournisseursDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
