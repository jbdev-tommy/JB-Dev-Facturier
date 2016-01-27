package fr.jbdev.facturier.controller.fournisseurs;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Fournisseurs;

@FacesConverter("fournisseursConverter")
public class FournisseursConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final FournisseursListBean service = (FournisseursListBean) context
			.getExternalContext().getSessionMap()
			.get("fournisseursListBean"); //$NON-NLS-1$

		for (Fournisseurs four : service.getList()) {

		    if (four.getEmail().equals(value))
			return four;
		}

		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("FournisseursConverter.3"), //$NON-NLS-1$
			Messages.getString("FournisseursConverter.4"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {

	if (value != null)
	    return String.valueOf(((Fournisseurs) value).getEmail());
	else
	    return null;
    }

}
