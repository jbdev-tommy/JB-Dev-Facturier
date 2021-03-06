package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.FormesJuridiques;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.FormesJuridiquesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("formesJuridiquesService")
public class FormesJuridiquesServiceImpl extends
	BaseServiceImpl<FormesJuridiques> implements FormesJuridiquesService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public FormesJuridiquesServiceImpl(
	    final BaseDao<FormesJuridiques, Integer> dao) {
	this.setDao(dao);
    }

}
