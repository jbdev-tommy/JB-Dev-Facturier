package fr.jbdev.facturier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Devis;
import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.facturier.dao.DevisDao;
import fr.jbdev.facturier.dao.generic.BaseDao;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.messages.ResourceMessage;
import fr.jbdev.facturier.service.DevisService;
import fr.jbdev.facturier.service.generic.BaseServiceImpl;

@Service("devisService")
public class DevisServiceImpl extends BaseServiceImpl<Devis> implements
	DevisService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private DevisDao devisDao;

    @Autowired
    public DevisServiceImpl(final BaseDao<Devis, Integer> dao) {
	this.setDao(dao);
    }

    @Override
    public void setObject(Devis devis, List<DevisProduits> pdts)
	    throws ObjectNullException {
	if (devis != null && !pdts.isEmpty()) {
	    devisDao.add(devis);
	} else

	    throw new ObjectNullException(ResourceMessage.BUNDLE_NAME_EXCEPTION);
    }

}
