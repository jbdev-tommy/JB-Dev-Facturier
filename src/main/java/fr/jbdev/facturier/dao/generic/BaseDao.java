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
package fr.jbdev.facturier.dao.generic;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Interface générique "Data Access Object"
 * 
 */
@SuppressWarnings({ "rawtypes" })
public interface BaseDao<E, K> {

    public void remove(E entity);

    public E find(Class clazz, int id);

    public List<E> findAll(Class clazz);

    public List<E> findAllBy(Class clazz, String champ, String valeur);

    public void clearSession();

    void add(List<Map.Entry<Class, Object>> objs, E entity);

    void update(List<Entry<Class, Object>> objs, E entity);

    void update(E entity);

    void add(E entity);

}
