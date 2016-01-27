package fr.jbdev.facturier.utils;

import java.util.List;

public class AlgorithmsTest<K> {

    public Object findRecursive(final List<K> object, final int i,
	    final int value) {

	if (object.get(i).hashCode() > value)
	    return object.get(i);
	else
	    return null;

    }
}
