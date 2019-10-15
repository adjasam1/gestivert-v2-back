package gesticert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
 * Entité Service
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "service")
public class Department implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_service")
	private Integer idDepartment;

	@Column(name = "nom_service", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameDepartment;

	/**
	 * Relation avec l'entité Utilisateur
	 * 
	 * @see User
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<User> users = new ArrayList<>();

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Department() {
	}

	public Department(Integer idDepartment, @Size(max = 50) @NotNull String nameDepartment, List<User> users) {
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
		this.users = users;
	}

	public Department(@Size(max = 50) @NotNull String nameDepartment) {
		this(null, nameDepartment, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
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
	 * @return Le nom du service concerné
	 */
	@Override
	public String toString() {
		return "Nom service : " + nameDepartment;
	}

}
