package fr.jbdev.facturier.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Adresses;

@FacesConverter("adressesConverter")
public class AdressesConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final AdressesListBean service = (AdressesListBean) context
			.getExternalContext().getApplicationMap()
			.get("adressesListBean"); //$NON-NLS-1$

		for (Adresses adresse : service.getAdresses()) {
		    String tmp1 = adresse.getNumero() + " " + adresse.getVoie()
			    + " " + adresse.getCodePostal() + " "
			    + adresse.getVille();
		    String tmp2 = adresse.getNumero() + "," + adresse.getVoie()
			    + " " + adresse.getCodePostal() + " "
			    + adresse.getVille();

		    if (tmp1.equals(value) || tmp2.equals(value))
			return adresse;
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
