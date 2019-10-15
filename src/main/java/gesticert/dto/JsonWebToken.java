package gesticert.dto;

/**
 * DTO (Data Transfer Object) spécifique retournant le token en tant qu'objet Json via la réponse REST
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public class JsonWebToken {
	
	private final String token;

    public JsonWebToken(String token) {
        this.token = token;
    }

    public String getToken() {
		return token;
	}

}
