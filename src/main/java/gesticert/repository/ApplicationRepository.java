package gesticert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gesticert.model.Application;
import gesticert.model.User;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité Application
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
	/**
	 * Modification de la méthode findById du CrudRepository
	 * 
	 * @param user
	 * @return liste des applications d'un utilisateur
	 */
	public Iterable<Application> findByUsers(List<User> users);

}
