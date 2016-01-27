package fr.jbdev.facturier.controller.commande;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Commandes;

@FacesConverter("commandesConverter")
public class CommandesConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final CommandesListBean service = (CommandesListBean) context
			.getExternalContext().getSessionMap()
			.get("commandesListBean"); //$NON-NLS-1$

		for (Commandes cmd : service.getList()) {
		    if (cmd.getNumCommade() == Integer.valueOf(value))
			return cmd;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Converssion erreur", //$NON-NLS-1$
			"Hello")); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((Commandes) value).getNumCommade());
	else
	    return null;
    }

}
