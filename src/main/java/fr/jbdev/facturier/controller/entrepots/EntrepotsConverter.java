package fr.jbdev.facturier.controller.entrepots;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Entrepots;

@FacesConverter("entrepotsConverter")
public class EntrepotsConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final EntrepotsListBean service = (EntrepotsListBean) context
			.getExternalContext().getSessionMap()
			.get("entrepotsListBean"); //$NON-NLS-1$

		for (Entrepots ent : service.getList()) {
		    if (ent.getNumero().equals(value))
			return ent;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "test", "test"));

	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((Entrepots) value).getNumero());
	else
	    return null;
    }

}
