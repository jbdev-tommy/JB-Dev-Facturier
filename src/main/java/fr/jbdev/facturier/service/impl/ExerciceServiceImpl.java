package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Exercicecomptable;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.ExerciceService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("exerciceService")
public class ExerciceServiceImpl extends BaseServiceImpl<Exercicecomptable>
	implements ExerciceService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public ExerciceServiceImpl(final BaseDao<Exercicecomptable, Integer> dao) {
	this.setDao(dao);
    }
}
