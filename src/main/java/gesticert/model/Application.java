package gesticert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entité Application
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "application")
public class Application implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_application")
	private Integer idApplication;

	@Column(name = "ccx_application", unique = true)
	@Size(min = 3, max = 3)
	@NotNull
	private String codeCCX;

	@Column(name = "nom_application")
	@Size(max = 50)
	@NotNull
	private String nameApplication;

	@Column(name = "nom_client_application")
	@Size(max = 50)
	private String nameClient;

	@Column(name = "prénom_client_application")
	@Size(max = 50)
	private String firstNameClient;

	@Column(name = "direction_client_application")
	@Size(max = 50)
	private String managementClient;

	@Column(name = "téléphone_client_application")
	@Size(min = 10, max = 10)
	private String phoneClient;

	@Column(name = "email_client_application")
	private String eMailClient;

	@Column(name = "commentaire")
	private String comment;

	/**
	 * Relation avec les entités Utilisateur et Certificat
	 * 
	 * @see User
	 * @see Certificate
	 */

	@JsonIgnoreProperties("applications")
	@ManyToMany()
	@JoinTable(name = "tl_utilisateur_application", joinColumns = @JoinColumn(name = "id_application"), inverseJoinColumns = @JoinColumn(name = "id_utilisateur"), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private List<User> users;

	@JsonIgnore
	@OneToMany(mappedBy = "application", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Application() {
	}

	public Application(Integer idApplication, @Size(min = 3, max = 3) @NotNull String codeCCX,
			@Size(max = 50) @NotNull String nameApplication, @Size(max = 50) String nameClient,
			@Size(max = 50) String firstNameClient, @Size(max = 50) String managementClient,
			@Size(min = 10, max = 10) String phoneClient, String eMailClient, String comment, List<User> users,
			List<Certificate> certificates) {
		this.idApplication = idApplication;
		this.codeCCX = codeCCX;
		this.nameApplication = nameApplication;
		this.nameClient = nameClient;
		this.firstNameClient = firstNameClient;
		this.managementClient = managementClient;
		this.phoneClient = phoneClient;
		this.eMailClient = eMailClient;
		this.comment = comment;
		this.users = users;
		this.certificates = certificates;
	}

	public Application(@Size(min = 3, max = 3) @NotNull String codeCCX, @Size(max = 50) @NotNull String nameApplication,
			@Size(max = 50) String nameClient, @Size(max = 50) String firstNameClient,
			@Size(max = 50) String managementClient, @Size(min = 10, max = 10) String phoneClient, String eMailClient,
			String comment) {
		this(null, codeCCX, nameApplication, nameClient, firstNameClient, managementClient, phoneClient, eMailClient,
				comment, null, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdApplication() {
		return idApplication;
	}

	public void setIdApplication(Integer idApplication) {
		this.idApplication = idApplication;
	}

	public String getCodeCCX() {
		return codeCCX;
	}

	public void setCodeCCX(String codeCCX) {
		this.codeCCX = codeCCX;
	}

	public String getNameApplication() {
		return nameApplication;
	}

	public void setNameApplication(String nameApplication) {
		this.nameApplication = nameApplication;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getFirstNameClient() {
		return firstNameClient;
	}

	public void setFirstNameClient(String firstNameClient) {
		this.firstNameClient = firstNameClient;
	}

	public String getManagementClient() {
		return managementClient;
	}

	public void setManagementClient(String managementClient) {
		this.managementClient = managementClient;
	}

	public String getPhoneClient() {
		return phoneClient;
	}

	public void setPhoneClient(String phoneClient) {
		this.phoneClient = phoneClient;
	}

	public String geteMailClient() {
		return eMailClient;
	}

	public void seteMailClient(String eMailClient) {
		this.eMailClient = eMailClient;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
