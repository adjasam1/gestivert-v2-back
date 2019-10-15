package gesticert.model;

import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumération Rôle contenant une liste de sous-objets
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Table(name = "rôle")
public enum Role implements GrantedAuthority {

	/**
	 * Roles possible pour les utilisateurs
	 */
	ROLE_ADMIN, ROLE_DEV;

	/**
	 * Accesseur
	 * 
	 * @return le nom
	 */
	@Override
	public String getAuthority() {
		return name();
	}

}
