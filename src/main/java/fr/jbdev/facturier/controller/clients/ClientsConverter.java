package fr.jbdev.facturier.controller.clients;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Clients;

@FacesConverter("clientsConverter")
public class ClientsConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final ClientsListBean service = (ClientsListBean) context
			.getExternalContext().getSessionMap()
			.get("clientsListBean"); //$NON-NLS-1$

		for (Clients client : service.getList()) {

		    if (client.getPersonnes().getNom().equals(value))
			return client;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("ClientsConverter.4"), //$NON-NLS-1$
			Messages.getString("ClientsConverter.5"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((Clients) value).getPersonnes().getNom());
	else
	    return null;
    }

}
