package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Todo;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.service.TodoService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("todoService")
public class TodoServiceImp extends BaseServiceImpl<Todo> implements
	TodoService {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public TodoServiceImp(final BaseDao<Todo, Integer> dao) {
	this.setDao(dao);
    }

}
