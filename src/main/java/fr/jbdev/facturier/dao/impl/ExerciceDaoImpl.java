package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Exercicecomptable;
import fr.jbdev.domaine.ExercicecomptableId;
import fr.jbdev.facturier.dao.ExerciceDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class ExerciceDaoImpl extends BaseDaoImpl<Exercicecomptable, Integer>
	implements ExerciceDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void add(Exercicecomptable exercice) {
	ExercicecomptableId id = new ExercicecomptableId();
	id.setNumSiret(exercice.getEntreprises().getNumSiret());

	exercice.setId(id);

	currentSession().save(exercice);
    }
}
