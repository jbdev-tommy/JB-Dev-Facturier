package fr.jbdev.facturier.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import fr.jbdev.domaine.Todo;
import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.notifier.message.ConsolMessage;
import fr.jbdev.facturier.notifier.message.MailSenderService;
import fr.jbdev.facturier.notifier.message.MyFacesMessages;
import fr.jbdev.facturier.utils.CryptUtil;

/**
 * @author Jonathan Bochard
 *
 */
@Aspect
public class NotifierAspect {

    // @AfterReturning(pointcut =
    // "execution(public Utilisateurs fr.jbdev.facturier.service.impl.UserServiceImpl.findUserByMail(..))",
    // returning = "user")
    // public void notificationsUtilisateur(final JoinPoint jp, final
    // Utilisateurs user) {
    // if (user != null) {
    //	    //new ConsolMessage(Messages.getString("NotifierAspect.17"), jp); //$NON-NLS-1$
    //	    //new MyFacesMessages(Messages.getString("NotifierAspect.17")); //$NON-NLS-1$
    // } else {
    //	    new ConsolMessage(Messages.getString("NotifierAspect.6"), jp); //$NON-NLS-1$
    //	    //new MyFacesMessages(Messages.getString("NotifierAspect.6")); //$NON-NLS-1$
    // }
    // }

    @After("execution(public void fr.jbdev.facturier.controller.*.*.create(..))")
    public void notificationCreateBean(final JoinPoint jp) {
	new ConsolMessage(Messages.getString("NotifierAspect.7"), jp); //$NON-NLS-1$
	new MyFacesMessages(Messages.getString("NotifierAspect.18"));
    }

    // /**
    // * @param joinPoint
    // * @param dupplicateObjectDb
    // */
    // @AfterThrowing(pointcut =
    // "execution(public boolean fr.jbdev.facturier.service.impl.UserServiceImpl.*(..))",
    // throwing = "dupplicateObjectDb")
    // public void notificationUtilisateurExist(final JoinPoint joinPoint,
    // final DupplicateObjectDb dupplicateObjectDb) {
    // new MyFacesMessages(dupplicateObjectDb.getMessage());
    // new ConsolMessage(dupplicateObjectDb.getMessage(), joinPoint);
    // }
    //
    /**
     * Notifie l'initialisation des beans JSF
     * 
     * @param jp
     */
    @After("execution(public void fr.jbdev.facturier.controller.*.*.init(..))")
    public void notificationInitBean(final JoinPoint jp) {
	new ConsolMessage(Messages.getString("NotifierAspect.7"), jp); //$NON-NLS-1$
    }

    /**
     * Idem que précédement
     * 
     * @param jp
     */
    @After("execution(public void fr.jbdev.facturier.controller.*.init(..))")
    public void notificationInitBisBean(final JoinPoint jp) {
	new ConsolMessage(Messages.getString("NotifierAspect.8"), jp); //$NON-NLS-1$
    }
    //
    // /**
    // * Notification de création d'un pdf
    // *
    // * @param jp
    // * @param bool
    // */
    // @AfterReturning(pointcut =
    // "execution(public * fr.jbdev.facturier.controller.devis.DevisBean.createPdf(..))",
    // returning = "byt")
    // public void notificationsCreatePdf(final JoinPoint jp, final byte[] byt)
    // {
    // if (byt != null) {
    //	    new ConsolMessage(Messages.getString("NotifierAspect.9"), jp); //$NON-NLS-1$
    //	    new MyFacesMessages(Messages.getString("NotifierAspect.10")); //$NON-NLS-1$
    // } else {
    //	    new MyFacesMessages(Messages.getString("NotifierAspect.11")); //$NON-NLS-1$
    // }
    // }
    //
    // /**
    // * Notification de tâches
    // *
    // * @param jp
    // * @param list
    // */
     @AfterReturning(pointcut =
     "execution(public * fr.jbdev.facturier.controller.todo.TodoBean.createCurrentTodoList(..))",
     returning = "list")
     public void notificationTacheDuJour(final JoinPoint jp,
     final List<Todo> list) {
     new MyFacesMessages(
    		Messages.getString("NotifierAspect.12") + list.size() //$NON-NLS-1$
    			+ Messages.getString("NotifierAspect.13")); //$NON-NLS-1$
     for (Todo todo : list) {
     new MyFacesMessages(
    		    Messages.getString("NotifierAspect.14") + todo.getTodo()); //$NON-NLS-1$
     }
     }
    
  
    // /**
    // * Notifie qu'un object est null
    // *
    // * @param joinPoint
    // * @param objectNullException
    // */
     @AfterThrowing(pointcut = "execution(public * *(..))", throwing =
     "objectNullException")
     public void notificationObjectNulExceptions(final JoinPoint joinPoint,
     final ObjectNullException objectNullException) {
     new ConsolMessage(objectNullException.getMessage().toString()
    		+ Messages.getString("NotifierAspect.0"), //$NON-NLS-1$
     joinPoint);
    	new MyFacesMessages(Messages.getString("NotifierAspect.1") //$NON-NLS-1$
     + objectNullException.getMessage().toString());
     }
    //
    // /**
    // * Envois de mail à la création d'un utilisateur
    // *
    // * @param user
    // */
     @AfterReturning(pointcut =
     "execution(public Utilisateurs fr.jbdev.facturier.controller.user.SignInBean.createUser(..))",
     returning = "user")
     public void sendMailExecution(final Utilisateurs user) {
     if (user != null) {
     String key = user.getEmail();
    	    String lien = Messages.getString("NotifierAspect.4") //$NON-NLS-1$
     + CryptUtil.encrypte(key)
    		    + Messages.getString("NotifierAspect.16"); //$NON-NLS-1$
     new MailSenderService(user.getEmail(),
    		    Messages.getString("NotifierAspect.2") //$NON-NLS-1$
    			    + Messages.getString("NotifierAspect.3") + lien); //$NON-NLS-1$
    	    new MyFacesMessages(Messages.getString("NotifierAspect.5")); //$NON-NLS-1$
     } else {
    	    new MyFacesMessages(Messages.getString("NotifierAspect.6")); //$NON-NLS-1$
     }
     }

}
