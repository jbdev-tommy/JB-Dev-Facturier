package fr.jbdev.facturier.service.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.jbdev.facturier.excepetions.ObjectNullException;

@SuppressWarnings("rawtypes")
public interface BaseService<E> extends Serializable {
    public E getObject(Class clazz, int id);

    public List<E> getAllObject(Class clazz) throws ObjectNullException;

    public void remove(E model) throws ObjectNullException;

    public List<E> getAllObjectBy(Class clazz, String champ, String valeur)
	    throws ObjectNullException;

    public void clearSession();

    public void setObject(List<Map.Entry<Class, Object>> objs, E model)
	    throws ObjectNullException;

    public void update(List<Entry<Class, Object>> objs, E model)
	    throws ObjectNullException;

    void update(E model) throws ObjectNullException;

    void setObject(E model) throws ObjectNullException;

}
