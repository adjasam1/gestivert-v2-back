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
 * Entité Racine
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "racine")
public class Root implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_racine")
	private Integer idRoot;

	@Column(name = "nom_racine", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameRoot;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "root")
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Root() {
	}

	public Root(Integer idRoot, @Size(max = 50) @NotNull String nameRoot, List<Certificate> certificates) {
		this.idRoot = idRoot;
		this.nameRoot = nameRoot;
		this.certificates = certificates;
	}

	public Root(@Size(max = 50) @NotNull String nameRoot) {
		this(null, nameRoot, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdRoot() {
		return idRoot;
	}

	public void setIdRoot(Integer idRoot) {
		this.idRoot = idRoot;
	}

	public String getNameRoot() {
		return nameRoot;
	}

	public void setNameRoot(String nameRoot) {
		this.nameRoot = nameRoot;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
