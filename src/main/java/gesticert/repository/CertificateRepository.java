package gesticert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gesticert.model.Certificate;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité Certificat
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

	/**
	 * Requete SQL pour trouver les certificats d'un utilisateur par son idRH
	 * 
	 * @param idRHUser
	 * @return liste des certificats d'un utilisateur
	 */
	@Query(value = "SELECT * FROM certificat c " + "JOIN application a ON c.id_application = a.id_application "
			+ "JOIN tl_utilisateur_application ua ON a.id_application = ua.id_application "
			+ "JOIN utilisateur u ON u.id_utilisateur = ua.id_utilisateur "
			+ "WHERE u.idrh_utilisateur = ?", nativeQuery = true)
	public List<Certificate> findByIdRHUser(String idRHUser);

}
