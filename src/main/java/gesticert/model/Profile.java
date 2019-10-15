package gesticert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entité Profil
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "profil")
public class Profile implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profil")
	private Integer idProfile;

	@Column(name = "type_profil", unique = true)
	@Size(max = 50)
	@NotNull
	private String typeProfile;

	/**
	 * Relation avec l'entité Utilisateur
	 * 
	 * @see User
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "profile", orphanRemoval = true)
	private List<User> users;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Profile() {
	}

	public Profile(Integer idProfile, @Size(max = 50) @NotNull String typeProfile, List<User> users) {
		this.idProfile = idProfile;
		this.typeProfile = typeProfile;
		this.users = users;
	}

	public Profile(@Size(max = 50) @NotNull String typeProfile) {
		this(null, typeProfile, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}

	public String getTypeProfile() {
		return typeProfile;
	}

	public void setTypeProfile(String typeProfile) {
		this.typeProfile = typeProfile;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Méthode toString
	 * 
	 * @return Le nom du profil concerné
	 */
	@Override
	public String toString() {
		return "Type profil : " + typeProfile;
	}

}
