package fr.jbdev.facturier.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.dao.UserDao;
import fr.jbdev.facturier.dao.generic.impl.BaseDaoImpl;

@Repository("userDao")
@Transactional
public class UsersDoaImpl extends BaseDaoImpl<Utilisateurs, Integer> implements
	UserDao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Utilisateurs findByMail(String mail) {
	return (Utilisateurs) currentSession()
		.createQuery("from Utilisateurs as u where u.email = :value")
		.setParameter("value", mail).uniqueResult();
    }

}