package fr.jbdev.facturier.utils;

import java.util.List;
import java.util.Set;

public class CollectionsUtil<K> {

    /**
     * Recherche une valeur ( value ) dans une list ( list ) et retourne sont
     * index si trouvé sinon -1
     * 
     * @param list
     * @param value
     *            ( valeur à trouver )
     * @return id or -1
     */
    public int rechercheDico(final List<K> list, final K value) {

	boolean trouve = false;
	int id = 0;
	int ifin = list.size();
	int im = 0;

	while (!trouve && ((ifin - id) > 1)) {
	    im = (id + ifin) / 2;
	    trouve = list.get(im).equals(value);
	    System.out.println(" * Value : "
		    + (list.get(im).hashCode() > value.hashCode()));
	    if (list.get(im).hashCode() > value.hashCode()) { // list.get(im)
		// est
		// supérieure a
		// value
		ifin = im;
	    } else {
		id = im;
	    }
	}

	if (list.get(id).equals(value))
	    return id;
	else
	    return -1;

    }

    public String searchSomeThing(final Set<String> set, final String start) {

	for (String client : set) {

	    if (client.startsWith(start)
		    || client.startsWith(start.toUpperCase())
		    || client.startsWith(start.toLowerCase()))

		return client;

	}

	return null;
    }
}
