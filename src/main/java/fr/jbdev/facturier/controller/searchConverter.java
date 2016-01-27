package fr.jbdev.facturier.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("searchConverter")
public class searchConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final SearchBean service = (SearchBean) context
			.getExternalContext().getSessionMap().get("searchBean"); //$NON-NLS-1$

		for (int i = 0; i < service.getNom().size(); ++i) {
		    if (service.getNom().get(i).equals(value))
			return service.getNom().get(i);
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("SearchConverter.4"), //$NON-NLS-1$
			Messages.getString("SearchConverter.5"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(value);
	else
	    return null;
    }

}
