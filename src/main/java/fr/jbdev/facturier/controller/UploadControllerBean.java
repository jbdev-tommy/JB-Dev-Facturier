package fr.jbdev.facturier.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author tommy Bean de gestion des uploads
 */
@SessionScoped
@ManagedBean(name = "myUploadBean", eager = false)
public class UploadControllerBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private boolean imageView;

    @ManagedProperty(value = "#{imgBean}")
    private DisplayImgBean imageBean;

    public void upload(final FileUploadEvent event) {
	file = event.getFile();

	if (file != null) {
	    imageBean.setByt(file.getContents());
	    imageView = true;
	} else {
	    imageView = false;
	}
    }

    public UploadedFile getFile() {
	return file;
    }

    public void setFile(final UploadedFile file) {
	this.file = file;
    }

    public boolean isImageView() {
	return imageView;
    }

    public void setImageView(final boolean imageView) {
	this.imageView = imageView;
    }

    public DisplayImgBean getImageBean() {
	return imageBean;
    }

    public void setImageBean(final DisplayImgBean imageBean) {
	this.imageBean = imageBean;
    }

}
