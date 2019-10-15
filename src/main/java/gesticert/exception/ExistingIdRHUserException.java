package gesticert.exception;

/**
 * Exception spécifique qui est levée si un utilisateur existe déjà dans la base
 * de données
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public class ExistingIdRHUserException extends Exception {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

}