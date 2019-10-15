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
 * Entité Environnement
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "environnement")
public class Environment implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = " id_environnement")
	private Integer idEnvironment;

	@Column(name = "nom_environnement", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameEnvironment;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "environment")
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Environment() {
	}

	public Environment(Integer idEnvironment, @Size(max = 50) @NotNull String nameEnvironment,
			List<Certificate> certificates) {
		this.idEnvironment = idEnvironment;
		this.nameEnvironment = nameEnvironment;
		this.certificates = certificates;
	}

	public Environment(@Size(max = 50) @NotNull String nameEnvironment) {
		this(null, nameEnvironment, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdEnvironment() {
		return idEnvironment;
	}

	public void setIdEnvironment(Integer idEnvironment) {
		this.idEnvironment = idEnvironment;
	}

	public String getNameEnvironment() {
		return nameEnvironment;
	}

	public void setNameEnvironment(String nameEnvironment) {
		this.nameEnvironment = nameEnvironment;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
