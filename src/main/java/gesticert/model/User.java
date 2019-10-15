package gesticert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entité Utilisateur
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "utilisateur")
public class User implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Integer idUser;

	@Column(name = "idRH_utilisateur", unique = true)
	@Size(min = 7, max = 7)
	@NotNull
	private String idRHUser;

	@Column(name = "mot_de_passe_utilisateur")
	@NotNull
	private String passwordUser;

	@Column(name = "nom_utilisateur")
	@Size(max = 50)
	@NotNull
	private String nameUser;

	@Column(name = "prénom_utilisateur")
	@Size(max = 50)
	@NotNull
	private String firstNameUser;

	@Column(name = "email_utilisateur", unique = true)
	@NotNull
	private String eMailUser;

	@Column(name = "téléphone_utilisateur")
	@Size(min = 10, max = 10)
	private String phoneUser;

	/**
	 * Relation avec les entités Service, Profil et Application
	 * 
	 * @see Department
	 * @see Profile
	 * @see Application
	 */

	@ManyToOne
	@JoinColumn(name = "id_service")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "id_profil")
	private Profile profile;

	@JsonIgnoreProperties("users")
	@ManyToMany
	@JoinTable(name = "tl_utilisateur_application", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_application"), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private List<Application> applications;

	/**
	 * Relation avec la collection de types simples Role Relation gérée uniquement
	 * par l'entité Utilisateur
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private List<Role> roleList;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public User() {
	}

	public User(Integer idUser, @Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser,
			@Size(max = 50) @NotNull String nameUser, @Size(max = 50) @NotNull String firstNameUser, String eMailUser,
			@Size(min = 10, max = 10) String phoneUser, Department department, Profile profile,
			List<Application> applications, List<Role> roleList) {
		this.idUser = idUser;
		this.idRHUser = idRHUser;
		this.passwordUser = passwordUser;
		this.nameUser = nameUser;
		this.firstNameUser = firstNameUser;
		this.eMailUser = eMailUser;
		this.phoneUser = phoneUser;
		this.department = department;
		this.profile = profile;
		this.applications = applications;
		this.roleList = roleList;
	}

	public User(@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser,
			@Size(max = 50) @NotNull String nameUser, @Size(max = 50) @NotNull String firstNameUser, String eMailUser,
			@Size(min = 10, max = 10) String phoneUser, Department department, Profile profile, List<Role> roleList) {
		this(null, idRHUser, passwordUser, nameUser, firstNameUser, eMailUser, phoneUser, department, profile, null,
				roleList);
	}

	public User(@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList) {
		this(null, idRHUser, passwordUser, null, null, null, null, null, null, null, roleList);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getIdRHUser() {
		return idRHUser;
	}

	public void setIdRHUser(String idRHUser) {
		this.idRHUser = idRHUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String geteMailUser() {
		return eMailUser;
	}

	public void seteMailUser(String eMailUser) {
		this.eMailUser = eMailUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
