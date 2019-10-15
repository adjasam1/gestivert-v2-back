package gesticert.service;

import javax.mail.MessagingException;

import gesticert.model.Certificate;

/**
 * Service pour l'envoi de mail
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface MailService {

	/**
	 * Configuration et contenu du message dans le mail envoyé
	 * 
	 * @param certificate
	 * @return contenu du mail envoyé
	 * @throws MessagingException
	 */
	public Object sendHtmlEmail(Certificate certificate) throws MessagingException;

}
