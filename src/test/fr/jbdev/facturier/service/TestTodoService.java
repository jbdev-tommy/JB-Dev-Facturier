package fr.jbdev.facturier.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jbdev.domaine.Todo;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-context.xml")
public class TestTodoService {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("todoService")
    private TodoService todoService;

    @Test
    public void add() {
	// Utilisateur
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // Todo
	    Todo todo = new Todo();
	    todo.setDateDebutTodo(new Date());
	    todo.setDateFinTodo(new Date());
	    todo.setDo_(true);
	    todo.setTempAvantRappel(new Date());
	    todo.setTodo("Cool");
	    todo.setUtilisateurs(user);

	    todoService.setObject(todo);

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Test
    public void list() {
	// Utilisateurs
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // List todo
	    Set<Todo> list = user.getTodos();
	    assertTrue(!list.isEmpty());
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Test
    public void update() {
	// Utilisateurs
	Utilisateurs user;
	try {
	    user = userService.findUserByMail("bochard.jonathan@jbdev.fr");

	    // List todo
	    Set<Todo> list = user.getTodos();
	    if (!list.isEmpty()) {
		for (Todo todo : list) {
		    todo.setTodo("Updated");
		    todoService.update(todo);
		}
	    }

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
