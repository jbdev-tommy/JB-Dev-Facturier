package fr.jbdev.facturier.controller.produits;

import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Produits;

@FacesConverter("produitsConverter")
public class produitsConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final ProduitsListBean service = (ProduitsListBean) context
			.getExternalContext().getSessionMap()
			.get("produitsListBean"); //$NON-NLS-1$

		// List Produits + Services
		Set<Produits> list = new HashSet<Produits>();
		list.addAll(service.getListProduits());
		list.addAll(service.getListServices());

		for (Produits pdt : list) {
		    if (pdt.getNom().equals(value))
			return pdt;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("produitsConverter.4"), //$NON-NLS-1$
			Messages.getString("produitsConverter.5"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((Produits) value).getNom());
	else
	    return null;
    }

}
