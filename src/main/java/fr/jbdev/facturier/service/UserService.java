package fr.jbdev.facturier.service;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.service.generic.BaseService;

public interface UserService extends BaseService<Utilisateurs> {

    Utilisateurs findUserByMail(String email) throws ObjectNullException;

    // void addUtilisateurs(Utilisateurs user) throws ObjectNullException;

}