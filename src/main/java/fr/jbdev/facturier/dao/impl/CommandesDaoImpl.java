package fr.jbdev.facturier.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Commandes;
import fr.jbdev.domaine.Contient;
import fr.jbdev.domaine.Produits;
import fr.jbdev.facturier.dao.CommandesDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository
@Transactional
public class CommandesDaoImpl extends BaseDaoImpl<Commandes, Integer> implements
	CommandesDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void add(Commandes cmd) {
	currentSession().save(cmd);
	Set<Contient> ct = cmd.getContients();
	for (Contient contient : ct) {
	    contient.setCommandes(cmd);
	    System.out.println(" HasCode : " + contient.hashCode());
	    currentSession().save(contient);
	    currentSession().flush();
	    currentSession().clear();
	}
	currentSession().flush();
	currentSession().clear();
    }
  
}
