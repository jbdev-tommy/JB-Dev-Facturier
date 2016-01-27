package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Categories;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.CategoriesService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("categoriesService")
public class CategorieServiceImpl extends BaseServiceImpl<Categories> implements
	CategoriesService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public CategorieServiceImpl(final BaseDao<Categories, Integer> dao) {
	this.setDao(dao);
    }

}
