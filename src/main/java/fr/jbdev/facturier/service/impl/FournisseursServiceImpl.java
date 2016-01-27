package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Fournisseurs;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.FournisseursService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("fournisseursService")
public class FournisseursServiceImpl extends BaseServiceImpl<Fournisseurs>
	implements FournisseursService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public FournisseursServiceImpl(final BaseDao<Fournisseurs, Integer> dao) {
	this.setDao(dao);
    }

}
