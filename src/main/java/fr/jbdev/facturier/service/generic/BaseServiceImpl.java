package fr.jbdev.facturier.service.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.messages.ResourceMessage;

@Service
public class BaseServiceImpl<E> implements BaseService<E> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BaseDao<E, Integer> dao;

    private E model;

    @SuppressWarnings("rawtypes")
    @Override
    public E getObject(final Class clazz, final int id) {
	try {
	    setModel(dao.find(clazz, id));
	    if (getModel() != null)
		return getModel();
	} catch (final NullPointerException e) {
	    e.getMessage();
	}
	return getModel();
    }

    @SuppressWarnings({ "rawtypes"})
    @Override
    public List<E> getAllObjectBy(final Class clazz, final String champ,
	    final String valeur) throws ObjectNullException {
	try {
	    return new ArrayList<E>(dao.findAllBy(clazz, champ, valeur));
	} catch (final NullPointerException e) {
	    return null;
	}
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public List<E> getAllObject(final Class clazz) throws ObjectNullException {

	try {
	    return new ArrayList<E>(dao.findAll(clazz));
	} catch (final NullPointerException e) {
	    return null;
	}

    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setObject(List<Map.Entry<Class, Object>> objs, final E model)
	    throws ObjectNullException {

	setModel(model);
	if (getModel() != null) {
	    dao.add(objs, getModel());
	} else
	    throw new ObjectNullException(" Persistance probleme");

    }

    @Override
    public void setObject(final E model) throws ObjectNullException {

	setModel(model);
	if (getModel() != null) {
	    dao.add(getModel());
	} else
	    throw new ObjectNullException(" Persistance probleme");

    }

    @SuppressWarnings("rawtypes")
    @Override
    public void update(List<Map.Entry<Class, Object>> objs, final E model)
	    throws ObjectNullException {

	setModel(model);
	if (getModel() != null) {
	    dao.update(objs, model);
	} else
	    throw new ObjectNullException(ResourceMessage.BUNDLE_NAME_EXCEPTION);

    }

    @Override
    public void update(final E model) throws ObjectNullException {

	setModel(model);
	if (getModel() != null) {
	    dao.update(model);
	} else
	    throw new ObjectNullException(ResourceMessage.BUNDLE_NAME_EXCEPTION);

    }

    @Override
    public void remove(final E model) throws ObjectNullException {
	setModel(model);
	if (getModel() != null) {
	    dao.remove(model);
	} else
	    throw new ObjectNullException(ResourceMessage.BUNDLE_NAME_EXCEPTION);
    }

    @Override
    public void clearSession() {
	dao.clearSession();
    }

    public E getModel() {
	return model;
    }

    public void setModel(final E model) {
	this.model = model;
    }

    public BaseDao<E, Integer> getDao() {
	return dao;
    }

    public void setDao(final BaseDao<E, Integer> dao) {
	this.dao = dao;
    }

}
