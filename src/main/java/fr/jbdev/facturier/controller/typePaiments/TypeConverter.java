package fr.jbdev.facturier.controller.typePaiments;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.TypeDePaiment;

@FacesConverter("typeConverter")
public class TypeConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final TypePaimentListBean service = (TypePaimentListBean) context
			.getExternalContext().getApplicationMap()
			.get("typePaimentListBean"); //$NON-NLS-1$

		for (TypeDePaiment type : service.getList()) {
		    if (type.getNom().equals(value))
			return type;
		}
		return null;

	    } catch (final NumberFormatException e) {
		throw new ConverterException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			Messages.getString("TypeConverter.4"), //$NON-NLS-1$
			Messages.getString("TypeConverter.5"))); //$NON-NLS-1$
	    }
	} else
	    return null;
    }

    @Override
    public String getAsString(final FacesContext context,
	    final UIComponent component, final Object value) {
	if (value != null)
	    return String.valueOf(((TypeDePaiment) value).getNom());
	else
	    return null;
    }

}
