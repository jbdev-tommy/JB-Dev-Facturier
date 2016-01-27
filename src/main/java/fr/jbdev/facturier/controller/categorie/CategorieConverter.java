package fr.jbdev.facturier.controller.categorie;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.jbdev.domaine.Categories;

@FacesConverter("categorieConverter")
public class CategorieConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context,
	    final UIComponent component, final String value) {
	if (value != null && value.trim().length() > 0) {
	    try {
		final CategorieListBean service = (CategorieListBean) context
			.getExternalContext().getSessionMap()
			.get("categorieListBean"); //$NON-NLS-1$
		
		for (Categories cat : service.getList()) {
		    if (cat.getNom().equals(value)) {
			return cat;
		    }
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
	    return String.valueOf(((Categories) value).getNom());
	else
	    return null;
    }

}
