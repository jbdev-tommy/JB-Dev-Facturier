package fr.jbdev.facturier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.dao.UserDao;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.messages.ResourceMessage;
import fr.jbdev.facturier.service.UserService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("userService")
@PreAuthorize("permitAll")
public class UserServiceImpl extends BaseServiceImpl<Utilisateurs> implements
	UserService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(final BaseDao<Utilisateurs, Integer> dao) {
	this.setDao(dao);
    }

    @Override
    public Utilisateurs findUserByMail(String email) throws ObjectNullException {
	if (email != null)
	    return userDao.findByMail(email);
	else
	    throw new ObjectNullException(ResourceMessage.BUNDLE_NAME_EXCEPTION);
    }

}
