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
 * Entité TypeDemande
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "type_demande")
public class TypeDemand implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_type_demande")
	private Integer idTypeDemand;

	@Column(name = "type_demande", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameTypeDemand;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "typeDemand")
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public TypeDemand() {
	}

	public TypeDemand(Integer idTypeDemand, @Size(max = 50) @NotNull String nameTypeDemand,
			List<Certificate> certificates) {
		this.idTypeDemand = idTypeDemand;
		this.nameTypeDemand = nameTypeDemand;
		this.certificates = certificates;
	}

	public TypeDemand(@Size(max = 50) @NotNull String nameTypeDemand) {
		this(null, nameTypeDemand, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdTypeDemand() {
		return idTypeDemand;
	}

	public void setIdTypeDemand(Integer idTypeDemand) {
		this.idTypeDemand = idTypeDemand;
	}

	public String getNameTypeDemand() {
		return nameTypeDemand;
	}

	public void setNameTypeDemand(String nameTypeDemand) {
		this.nameTypeDemand = nameTypeDemand;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
