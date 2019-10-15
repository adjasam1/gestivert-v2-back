package gesticert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entité Serveur
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Entity
@Table(name = "serveur")
public class Server implements Serializable {

	/**
	 * Identifiant unique utilisé lors de la déserialisation de l'objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attributs de l'entité
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_serveur")
	private Integer idServer;

	@Column(name = "nom_serveur", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameServer;

	/**
	 * Relation avec l'entité Certificat
	 * 
	 * @see Certificate
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "servers", fetch = FetchType.LAZY)
	private List<Certificate> certificates;

	/**
	 * Constructeurs dont un vide par défaut
	 */

	public Server() {
	}

	public Server(Integer idServer, @Size(max = 50) @NotNull String nameServer, List<Certificate> certificates) {
		this.idServer = idServer;
		this.nameServer = nameServer;
		this.certificates = certificates;
	}

	public Server(@Size(max = 50) @NotNull String nameServer) {
		this(null, nameServer, null);
	}

	/**
	 * Accesseurs et mutateurs
	 * 
	 * @return Valeur correspondant aux accesseurs
	 * @param Paramètre correspondant aux mutateurs
	 */

	public Integer getIdServer() {
		return idServer;
	}

	public void setIdServer(Integer idServer) {
		this.idServer = idServer;
	}

	public String getNameServer() {
		return nameServer;
	}

	public void setNameServer(String nameServer) {
		this.nameServer = nameServer;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	/**
	 * Méthode toString
	 * 
	 * @return Le nom du profil concerné
	 */
	@Override
	public String toString() {
		return "Nom serveur : " + nameServer;
	}

}
