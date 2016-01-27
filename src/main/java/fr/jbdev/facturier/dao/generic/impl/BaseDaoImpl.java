/*******************************************************************************
 * Copyright (c) 2015 Jonathan Bochard.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Jonathan - initial API and implementation
 ******************************************************************************/
package fr.jbdev.facturier.dao.generic.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.jbdev.facturier.dao.generic.BaseDao;

@Repository
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDaoImpl<E, K extends Serializable> implements BaseDao<E, K>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
	return sessionFactory.getCurrentSession();
    }

    @Override
    public void clearSession() {
	currentSession().flush();
	currentSession().clear();
    }

    /**
     * Fonctions de persistance avec une liste. Pour plus de clareté je met un
     * argument suplementaire afin de préciser l'entité principal a persister
     * Les exception sont gérer par le service
     * 
     * @param objs
     *            listes des objet à persister avant l'objet principale
     * @param entity
     *            objet principal
     * 
     */

    @Override
    public void add(List<Map.Entry<Class, Object>> objs, final E entity) {
	for (Map.Entry<Class, Object> obj : objs) {
	    currentSession().save(obj.getKey().cast(obj.getValue()));
	}
	currentSession().save(entity);
	clearSession();
    }

    @Override
    public void add(final E entity) {
	currentSession().save(entity);
	clearSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.jbdev.dao.BaseDao#update(E)
     */
    @Override
    public void update(List<Map.Entry<Class, Object>> objs, final E entity) {
	for (Map.Entry<Class, Object> obj : objs) {
	    currentSession().update(obj.getKey().cast(obj.getValue()));
	}
	currentSession().update(entity);
	clearSession();
    }

    @Override
    public void update(final E entity) {
	currentSession().update(entity);
	clearSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.jbdev.dao.BaseDao#remove(E)
     */
    @Override
    public void remove(final E entity) {
	currentSession().delete(entity);
	clearSession();
    }

    @Override
    public E find(final Class clazz, final int id) {

	return (E) currentSession().get(clazz, id);
    }

    @Override
    public List<E> findAllBy(final Class clazz, final String champ,
	    final String valeur) {

	List<E> list = new ArrayList<E>();
	list = currentSession()
		.createQuery(
			"from " + clazz.getCanonicalName() + " where " + champ //$NON-NLS-1$ //$NON-NLS-2$
				+ " = :valeur").setParameter("valeur", valeur) //$NON-NLS-1$ //$NON-NLS-2$
		.list();
	return list;
    }

    @Override
    public List<E> findAll(final Class clazz) {

	List<E> list = new ArrayList<E>();
	list = currentSession().createQuery("from " + clazz.getCanonicalName()) //$NON-NLS-1$
		.list();
	return list;
    }

}
