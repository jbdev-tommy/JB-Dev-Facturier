package fr.jbdev.facturier.controller.tva;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Tva;

@FacesConverter("tvaConverter")
public class TvaConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final TvaListBean service = (TvaListBean) context
			.getExternalContext().getApplicationMap()
			.get("tvaListBean"); //$NON-NLS-1$

		System.out
			.println("************** Activite Converter *****************"); //$NON-NLS-1$
		System.out.println(" Value = " + value); //$NON-NLS-1$
		System.out
			.println("***********************************************"); //$NON-NLS-1$
		for (int i = 0; i < service.getList().size(); ++i) {
		    if (service.getList().get(i).getNom().equals(value))
			return service.getList().get(i);
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("TvaConverter.4"), //$NON-NLS-1$
			Messages.getString("TvaConverter.5"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((Tva) value).getNom());
	else
	    return null;
    }

}
