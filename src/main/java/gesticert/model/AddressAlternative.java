package gesticert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entité AdresseAlternative
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "adresse_alternative")
public class AddressAlternative implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adresse_alternative")
	private Integer idAddressAlternative;

	@Column(name = "lien_adresse_alternative", unique = true)
	@NotNull
	private String linkAddressAlternative;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnoreProperties("application")
	@ManyToOne
	@JoinColumn(name = "id_certificat")
	private Certificate certificate;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public AddressAlternative() {
	}

	public AddressAlternative(Integer idAddressAlternative, @NotNull String linkAddressAlternative,
			Certificate certificate) {
		this.idAddressAlternative = idAddressAlternative;
		this.linkAddressAlternative = linkAddressAlternative;
		this.certificate = certificate;
	}

//	public AddressAlternative(@NotNull String linkAddressAlternative, Certificate certificate) {
//		this(null, linkAddressAlternative, certificate);
//	}

	public AddressAlternative(@NotNull String linkAddressAlternative) {
		this(null, linkAddressAlternative, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdAddressAlternative() {
		return idAddressAlternative;
	}

	public void setIdAddressAlternative(Integer idAddressAlternative) {
		this.idAddressAlternative = idAddressAlternative;
	}

	public String getLinkAddressAlternative() {
		return linkAddressAlternative;
	}

	public void setLinkAddressAlternative(String linkAddressAlternative) {
		this.linkAddressAlternative = linkAddressAlternative;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	/**
	 * Méthode toString
	 * 
	 * @return Le nom du profil concerné
	 */
	@Override
	public String toString() {
		return "Lien addresse alternative : " + linkAddressAlternative;
	}

}
