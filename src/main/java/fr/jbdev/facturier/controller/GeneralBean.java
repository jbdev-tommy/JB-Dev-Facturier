package fr.jbdev.facturier.controller;

import java.io.Serializable;

import org.primefaces.event.SelectEvent;

public interface GeneralBean<E> extends Serializable {

    public void create();

    public void view(SelectEvent event);

    public void update();

    public void delete();

    public E searchByNom(String nom);

    public E searchByMail(String mail);

    public void close();

}