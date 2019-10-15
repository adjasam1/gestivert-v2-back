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
 * Entité Plateforme
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "plateforme")
public class Platform implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plateforme")
	private Integer idPlatform;

	@Column(name = "nom_plateforme", unique = true)
	@Size(max = 50)
	@NotNull
	private String namePlatform;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "platform")
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Platform() {
	}

	public Platform(Integer idPlatform, @Size(max = 50) @NotNull String namePlatform, List<Certificate> certificates) {
		this.idPlatform = idPlatform;
		this.namePlatform = namePlatform;
		this.certificates = certificates;
	}

	public Platform(@Size(max = 50) @NotNull String namePlatform) {
		this(null, namePlatform, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdPlatform() {
		return idPlatform;
	}

	public void setIdPlatform(Integer idPlatform) {
		this.idPlatform = idPlatform;
	}

	public String getNamePlatform() {
		return namePlatform;
	}

	public void setNamePlatform(String namePlatform) {
		this.namePlatform = namePlatform;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
