package gesticert.exception;

/**
 * Exception spécifique qui est levée lorsque le jeton JWT a un format non
 * valide
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public class InvalidJWTException extends Exception {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

}
