package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.TypeDePaiment;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.TypeDePaimentsService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("typeDePaimentsService")
public class TypeDePaimentServiceImpl extends BaseServiceImpl<TypeDePaiment>
	implements TypeDePaimentsService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public TypeDePaimentServiceImpl(final BaseDao<TypeDePaiment, Integer> dao) {
	this.setDao(dao);
    }
}
