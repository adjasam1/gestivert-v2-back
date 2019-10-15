package gesticert.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entité Certificat
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "certificat")
public class Certificate implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_certificat")
	private Integer idCertificate;

	@Column(name = "nom_certificat")
	@Size(min = 16, max = 21)
	@NotNull
	private String nameCertificate;

	@Column(name = "adresse_principale_certificat")
	private String linkAddressPrincipal;

	@Column(name = "lien_installation_certificat")
	private String linkInstallation;

	@Column(name = "mdp_certificat")
	private String passwordCertificate;

	@Column(name = "date_emission_certificat")
	@Type(type = "date")
	private Date dateIssue;

	@Column(name = "fin_validite_certificat")
	@Type(type = "date")
	private Date dateEndValidity;

	@Column(name = "date_demande_certificat")
	@Type(type = "date")
	private Date dateDemand;

	@Column(name = "date_realisation_souhaitee_certificat")
	@Type(type = "date")
	private Date dateCreationDesired;

	@Column(name = "date_transmission_certificat")
	@Type(type = "date")
	private Date dateTransmission;

	@Column(name = "email_referent_certificat")
	private String eMailReferent;

	@Column(name = "description_contexte_certificat")
	private String descriptionContext;

	@Column(name = "remarque_racine_certificat")
	private String remarkRoot;

	/**
	 * Relation avec les entités Application, Environnement, Plateforme, Racine,
	 * Serveur et AdresseAlternative Relation avec les entités Utilisateur,
	 * StatutDemande et TypeDemande
	 * 
	 * @see Application
	 * @see Environment
	 * @see Platform
	 * @see Root
	 * @see Server
	 * @see AddressAlternative
	 * @see User
	 * @see StatusDemand
	 * @see TypeDemand
	 */

	@ManyToOne
	@JoinColumn(name = "id_application")
	private Application application;

	@ManyToOne
	@JoinColumn(name = "id_environnement")
	private Environment environment;

	@ManyToOne
	@JoinColumn(name = "id_plateforme")
	private Platform platform;

	@ManyToOne // (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_racine")
	private Root root;

	@JsonIgnoreProperties("certificate")
	@ManyToMany
	@JoinTable(
			name = "tl_certificat_serveur", 
			joinColumns = @JoinColumn(name = "id_certificat"), 
			inverseJoinColumns = @JoinColumn(name = "id_serveur"), 
			foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), 
			inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private List<Server> servers;

	@JsonIgnoreProperties("certificate")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
	private List<AddressAlternative> addressAlternatives;

	@OneToOne
	@JoinColumn(name = "id_demandeur", referencedColumnName = "id_utilisateur")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_statut_demande")
	private StatusDemand statusDemand;

	@ManyToOne
	@JoinColumn(name = "id_type_demande")
	private TypeDemand typeDemand;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Certificate() {
	}

	public Certificate(Integer idCertificate, @Size(min = 16, max = 21) @NotNull String nameCertificate,
			String linkAddressPrincipal, String linkInstallation, String passwordCertificate, Date dateIssue,
			Date dateEndValidity, Date dateDemand, Date dateCreationDesired, Date dateTransmission,
			String eMailReferent, String descriptionContext, String remarkRoot, Application application,
			Environment environment, Platform platform, Root root, List<Server> servers,
			List<AddressAlternative> addressAlternatives, User user, StatusDemand statusDemand, TypeDemand typeDemand) {
		this.idCertificate = idCertificate;
		this.nameCertificate = nameCertificate;
		this.linkAddressPrincipal = linkAddressPrincipal;
		this.linkInstallation = linkInstallation;
		this.passwordCertificate = passwordCertificate;
		this.dateIssue = dateIssue;
		this.dateEndValidity = dateEndValidity;
		this.dateDemand = dateDemand;
		this.dateCreationDesired = dateCreationDesired;
		this.dateTransmission = dateTransmission;
		this.eMailReferent = eMailReferent;
		this.descriptionContext = descriptionContext;
		this.remarkRoot = remarkRoot;
		this.application = application;
		this.environment = environment;
		this.platform = platform;
		this.root = root;
		this.servers = servers;
		this.addressAlternatives = addressAlternatives;
		this.user = user;
		this.statusDemand = statusDemand;
		this.typeDemand = typeDemand;
	}

	public Certificate(@Size(min = 16, max = 21) @NotNull String nameCertificate, String linkAddressPrincipal,
			String linkInstallation, String passwordCertificate, Application application, Environment environment,
			Platform platform, Root root) {
		this(null, nameCertificate, linkAddressPrincipal, linkInstallation, passwordCertificate, null, null, null, null,
				null, null, null, null, application, environment, platform, root, null, null, null, null, null);
	}

	public Certificate(@Size(min = 16, max = 21) @NotNull String nameCertificate, String linkAddressPrincipal,
			String linkInstallation, String passwordCertificate, Date dateIssue, Application application,
			Environment environment, Platform platform, Root root, List<Server> servers) {
		this(null, nameCertificate, linkAddressPrincipal, linkInstallation, passwordCertificate, dateIssue, null, null, 
				null, null, null, null, null, application, environment, platform, root, servers, null, null, null, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdCertificate() {
		return idCertificate;
	}

	public void setIdCertificate(Integer idCertificate) {
		this.idCertificate = idCertificate;
	}

	public String getNameCertificate() {
		return nameCertificate;
	}

	public void setNameCertificate(String nameCertificate) {
		this.nameCertificate = nameCertificate;
	}

	public String getLinkAddressPrincipal() {
		return linkAddressPrincipal;
	}

	public void setLinkAddressPrincipal(String linkAddressPrincipal) {
		this.linkAddressPrincipal = linkAddressPrincipal;
	}

	public String getLinkInstallation() {
		return linkInstallation;
	}

	public void setLinkInstallation(String linkInstallation) {
		this.linkInstallation = linkInstallation;
	}

	public String getPasswordCertificate() {
		return passwordCertificate;
	}

	public void setPasswordCertificate(String passwordCertificate) {
		this.passwordCertificate = passwordCertificate;
	}

	public Date getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Date getDateDemand() {
		return dateDemand;
	}

	public void setDateDemand(Date dateDemand) {
		this.dateDemand = dateDemand;
	}

	public Date getDateCreationDesired() {
		return dateCreationDesired;
	}

	public void setDateCreationDesired(Date dateCreationDesired) {
		this.dateCreationDesired = dateCreationDesired;
	}

	public Date getDateTransmission() {
		return dateTransmission;
	}

	public void setDateTransmission(Date dateTransmission) {
		this.dateTransmission = dateTransmission;
	}

	public String geteMailReferent() {
		return eMailReferent;
	}

	public void seteMailReferent(String eMailReferent) {
		this.eMailReferent = eMailReferent;
	}

	public String getDescriptionContext() {
		return descriptionContext;
	}

	public void setDescriptionContext(String descriptionContext) {
		this.descriptionContext = descriptionContext;
	}

	public String getRemarkRoot() {
		return remarkRoot;
	}

	public void setRemarkRoot(String remarkRoot) {
		this.remarkRoot = remarkRoot;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<AddressAlternative> getAddressAlternatives() {
		return addressAlternatives;
	}

	public void setAddressAlternatives(List<AddressAlternative> addressAlternatives) {
		this.addressAlternatives = addressAlternatives;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public StatusDemand getStatusDemand() {
		return statusDemand;
	}

	public void setStatusDemand(StatusDemand statusDemand) {
		this.statusDemand = statusDemand;
	}

	public TypeDemand getTypeDemand() {
		return typeDemand;
	}

	public void setTypeDemand(TypeDemand typeDemand) {
		this.typeDemand = typeDemand;
	}

}
