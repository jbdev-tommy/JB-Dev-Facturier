package fr.jbdev.facturier.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author tommy Bean de gestion des images en stream
 */

@SessionScoped
@ManagedBean(name = "imgBean", eager = false)
public class DisplayImgBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private byte[] byt;

    @PostConstruct
    public void init() {
	byt = null;
    }

    /**
     * Retourne le medai à "streamer" contenue dans la variable byt
     * 
     * @return
     */
    public StreamedContent getImage() {

	final RequestContext context = RequestContext.getCurrentInstance();
	try {

	    if (context.isAjaxRequest() && byt != null)
		return new DefaultStreamedContent(new ByteArrayInputStream(byt));
	    else if (byt == null)
		return new DefaultStreamedContent(new ByteArrayInputStream(
			MyHttpSession.getUser().getEntreprises().getLogo()));
	    else
		return new DefaultStreamedContent(new ByteArrayInputStream(byt));
	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public byte[] getByt() {
	return byt;
    }

    public void setByt(final byte[] byt) {
	this.byt = byt;
    }

}
