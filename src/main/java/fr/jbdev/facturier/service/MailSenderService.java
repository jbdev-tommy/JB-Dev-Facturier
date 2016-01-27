package fr.jbdev.facturier.service;

public interface MailSenderService {
    public boolean sendMailTo(String toMail, String messages);
}
