package fr.jbdev.facturier.controller.formesJuridique;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.FormesJuridiques;

@FacesConverter("formesConverter")
public class FormesConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final FormesJuridiqueListBean service = (FormesJuridiqueListBean) context
			.getExternalContext().getApplicationMap()
			.get("formesJuridiqueListBean"); //$NON-NLS-1$

		for (FormesJuridiques forme : service.getList()) {

		    if (forme.getStatuJuridique().equals(value))
			return forme;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Help", "ReHelp"));

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
