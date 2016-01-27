package fr.jbdev.facturier.notifier.message;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.jbdev.facturier.messages.Messages;
import fr.jbdev.facturier.messages.ResourceMessage;

public class MailSenderService extends StringMessage implements
	fr.jbdev.facturier.notifier.message.Message {

    public MailSenderService(final String email, final String message) {
	super(message);
	sendMailTo(email, message);
    }

    private boolean sendMailTo(final String toMail, final String messages) {

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
	    // message.setText(messages);
	    message.setContent(messages, "text/html");

	    Transport.send(message);
	    return true;

	} catch (final MessagingException e) {
	    throw new RuntimeException(e);
	}
    }
}
