package fr.jbdev.facturier.controller.todo;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.jbdev.domaine.Todo;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.service.TodoService;

@SessionScoped
@ManagedBean(name = "todoListBean", eager = true)
public class TodoListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{todoService}")
    private TodoService todoService;

    private Set<Todo> list;

    @PostConstruct
    public void init() {
	setList(MyHttpSession.getUser().getTodos());
    }

    public TodoService getTodoService() {
	return todoService;
    }

    public void setTodoService(TodoService todoService) {
	this.todoService = todoService;
    }

    public Set<Todo> getList() {
	return list;
    }

    public void setList(Set<Todo> list) {
	this.list = list;
    }
}
