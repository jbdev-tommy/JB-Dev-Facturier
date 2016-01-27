package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Categories;
import fr.jbdev.facturier.dao.CategoriesDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class CategoriesDaoImpl extends BaseDaoImpl<Categories, Integer>
	implements CategoriesDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
