package gesticert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gesticert.model.AddressAlternative;
import gesticert.model.Certificate;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité AdresseAlternative
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface AddressAlternativeRepository extends JpaRepository<AddressAlternative, Integer> {

	/**
	 * Modification de la méthode findById du CrudRepository
	 * 
	 * @param certificate
	 * @return liste des adresses alternatives d'un certificat
	 */
	public Iterable<AddressAlternative> findByCertificate(Certificate certificate);

}
