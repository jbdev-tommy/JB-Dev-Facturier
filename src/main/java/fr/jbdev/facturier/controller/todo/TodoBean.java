package fr.jbdev.facturier.controller.todo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import fr.jbdev.domaine.Todo;
import fr.jbdev.facturier.controller.GeneralBean;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.excepetions.ObjectNullException;

@ViewScoped
@ManagedBean(name = "todoBean", eager = false)
public class TodoBean implements GeneralBean<Todo>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{todoListBean}")
    private TodoListBean todoListBean;

    private Todo todo;
    private Set<Todo> listDay;

    @PostConstruct
    public void init() {
	todo = new Todo();
	listDay = new HashSet<Todo>();
	trieTodo();
    }

    private void trieTodo() {
	for (Todo todo : todoListBean.getList()) {
	    if (todo.getDateFinTodo().equals(new Date())) {
		listDay.add(todo);
	    }
	}
    }

    @Override
    public void create() {
	todo.setUtilisateurs(MyHttpSession.getUser());
	try {
	    // Persistance
	    todoListBean.getTodoService().setObject(todo);

	    // Vue
	    todoListBean.getList().add(todo);
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void view(SelectEvent event) {
	todo = (Todo) event.getObject();

	// TODO dialog
    }

    @Override
    public void update() {
	try {
	    todoListBean.getTodoService().update(todo);
	    // Vue
	    if (todoListBean.getList().contains(todo)) {
		for (Todo todo : todoListBean.getList()) {
		    if (todo.equals(this.todo)) {
			todoListBean.getList().remove(todo);
			todoListBean.getList().add(todo);
			break;
		    }
		}
	    }

	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void delete() {
	try {
	    todoListBean.getTodoService().remove(todo);
	    // Vue
	    if (todoListBean.getList().contains(todo)) {
		for (Todo todo : todoListBean.getList()) {
		    if (todo.equals(this.todo)) {
			todoListBean.getList().remove(todo);
			break;
		    }
		}
	    }
	} catch (ObjectNullException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    public void unCheck() {
	todo.setDo_(false);

	// Persistance
	try {
	    todoListBean.getTodoService().update(todo);
	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}

	// Vue
	if (todoListBean.getList().contains(todo)) {
	    for (Todo todo : todoListBean.getList()) {
		if (todo.equals(todo)) {
		    todoListBean.getList().remove(this.todo);
		    todoListBean.getList().add(todo);
		}
	    }
	}
    }

    @Override
    public Todo searchByNom(String nom) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Todo searchByMail(String mail) {
	// TODO Auto-generated method stub
	return null;
    }

    public TodoListBean getTodoListBean() {
	return todoListBean;
    }

    public void setTodoListBean(TodoListBean todoListBean) {
	this.todoListBean = todoListBean;
    }

    public Todo getTodo() {
	return todo;
    }

    public void setTodo(Todo todo) {
	this.todo = todo;
    }

    public Set<Todo> getListDay() {
	return listDay;
    }

    public void setListDay(Set<Todo> listDay) {
	this.listDay = listDay;
    }

    @Override
    public void close() {
	todo = new Todo();

    }

}
