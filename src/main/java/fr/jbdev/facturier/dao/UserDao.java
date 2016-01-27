package fr.jbdev.facturier.dao;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.dao.generic.BaseDao;

public interface UserDao extends BaseDao<Utilisateurs, Integer> {

    Utilisateurs findByMail(String mail);

}