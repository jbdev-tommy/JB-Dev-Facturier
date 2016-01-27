package fr.jbdev.facturier.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import fr.jbdev.domaine.Exercicecomptable;
import fr.jbdev.facturier.controller.MyHttpSession;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.service.ExerciceService;

@SessionScoped
@ManagedBean(name = "exerciceBean", eager = false)
public class ExerciceBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{exerciceService}")
    private ExerciceService exerciceService;

    private Exercicecomptable exercice;
    private List<Exercicecomptable> exercices;
    private boolean view;

    @PostConstruct
    public void init() {
	setView(false);
	exercices = new ArrayList<Exercicecomptable>();
	exercice = new Exercicecomptable();

	exercices.addAll(MyHttpSession.getUser().getEntreprises()
		.getExercicecomptables());
	if (exercices.isEmpty()) {
	    setView(true);
	} else {
	    Date current = new Date();
	    for (Exercicecomptable tmp : exercices) {
		if (current.compareTo(tmp.getDateDebut()) > 0
			&& current.compareTo(tmp.getDateFin()) < 0) {
		    exercice = tmp;
		}
	    }
	}

    }

    public Exercicecomptable getExercice() {
	return exercice;
    }

    public void setExercice(Exercicecomptable exercice) {
	this.exercice = exercice;
    }

    public ExerciceService getExerciceService() {
	return exerciceService;
    }

    public void setExerciceService(ExerciceService exerciceService) {
	this.exerciceService = exerciceService;
    }

    public List<Exercicecomptable> getExercices() {
	return exercices;
    }

    public void setExercices(List<Exercicecomptable> exercices) {
	this.exercices = exercices;
    }

    public void view() {
	RequestContext.getCurrentInstance().execute(
		"PF('exerciceDialog').show()"); //$NON-NLS-1$
    }

    public void create() {
	try {
	    exercice.setEntreprises(MyHttpSession.getUser().getEntreprises());
	    exerciceService.setObject(exercice);

	    MyHttpSession.getUser().getEntreprises().getExercicecomptables()
		    .add(exercice);
	    setView(false);
	    new MyFacesMessages(" Enjoy bro !");

	} catch (ObjectNullException e) {
	    e.printStackTrace();
	}
    }

    public boolean isView() {
	return view;
    }

    public void setView(boolean view) {
	this.view = view;
    }

}
