package fr.jbdev.facturier.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import fr.jbdev.facturier.messages.Messages;
import fr.jbdev.facturier.messages.ResourceMessage;
import fr.jbdev.facturier.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Override
    public boolean sendMailTo(final String toMail, final String messages) {

	final String username = Messages
		.getString(
			"MailSenderServiceImpl.mail.username", ResourceMessage.BUNDLE_NAME_PROPERTIES); //$NON-NLS-1$
	final String password = Messages
		.getString(
			"MailSenderServiceImpl.mail.password", ResourceMessage.BUNDLE_NAME_PROPERTIES); //$NON-NLS-1$

	final Properties props = new Properties();
	props.put("mail.smtp.auth", "true"); //$NON-NLS-1$ //$NON-NLS-2$
	props.put("mail.smtp.starttls.enable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
	props.put(
		"mail.smtp.host", Messages.getString("MailSenderServiceImpl.mail.host", ResourceMessage.BUNDLE_NAME_PROPERTIES)); //$NON-NLS-1$ //$NON-NLS-2$
	props.put(
		"mail.smtp.port", Messages.getString("MailSenderServiceImpl.mail.port", ResourceMessage.BUNDLE_NAME_PROPERTIES)); //$NON-NLS-1$ //$NON-NLS-2$

	final Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		    }
		});

	try {

	    final Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(username));
	    message.setRecipients(Message.RecipientType.TO,
		    InternetAddress.parse(toMail));
	    message.setSubject(Messages
		    .getString(
			    "MailSenderServiceImpl.mail.subject", ResourceMessage.BUNDLE_NAME_PROPERTIES)); //$NON-NLS-1$
	    message.setText(Messages
		    .getString(
			    "MailSenderServiceImpl.mail.title", ResourceMessage.BUNDLE_NAME_PROPERTIES) + messages); //$NON-NLS-1$

	    Transport.send(message);
	    return true;

	} catch (final MessagingException e) {
	    throw new RuntimeException(e);
	}
    }
}
