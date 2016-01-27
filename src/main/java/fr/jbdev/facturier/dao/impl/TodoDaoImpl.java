package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Todo;
import fr.jbdev.facturier.dao.TodoDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class TodoDaoImpl extends BaseDaoImpl<Todo, Integer> implements TodoDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
