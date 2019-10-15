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
 * Entité StatutDemande
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "statut_demande")
public class StatusDemand implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_statut_demande")
	private Integer idStatusDemand;

	@Column(name = "statut_demande", unique = true)
	@Size(max = 50)
	@NotNull
	private String stateDemand;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "statusDemand")
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public StatusDemand() {
	}

	public StatusDemand(Integer idStatusDemand, @Size(max = 50) @NotNull String stateDemand,
			List<Certificate> certificates) {
		this.idStatusDemand = idStatusDemand;
		this.stateDemand = stateDemand;
		this.certificates = certificates;
	}

	public StatusDemand(@Size(max = 50) @NotNull String stateDemand) {
		this(null, stateDemand, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdStatusDemand() {
		return idStatusDemand;
	}

	public void setIdStatusDemand(Integer idStatusDemand) {
		this.idStatusDemand = idStatusDemand;
	}

	public String getStateDemand() {
		return stateDemand;
	}

	public void setStateDemand(String stateDemand) {
		this.stateDemand = stateDemand;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
