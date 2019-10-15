package gesticert.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gesticert.model.Department;
import gesticert.model.Profile;
import gesticert.model.User;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité Utilisateur
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Vérification si un idRH correspond à un utilisateur
	 * 
	 * @param idRHUser
	 * @return vrai ou faux
	 */
	public boolean existsByIdRHUser(String idRHUser);

	/**
	 * Modification de la méthode findById du CrudRepository
	 * 
	 * @param idRHUser
	 * @return un utilisateur
	 */
	public Optional<User> findByIdRHUser(String idRHUser);

	/**
	 * Modification de la méthode findById du CrudRepository
	 * 
	 * @param profile
	 * @param sort
	 * @return liste des utilisateurs ayant le même profil
	 */
	public Iterable<User> findByProfile(Profile profile, Sort sort);

	/**
	 * Requête SQL pour supprimer un profil à tous les utilisateurs qui le possèdent
	 * 
	 * @param profile
	 * @return modification du profil des utilisateurs concernés
	 */
	@Modifying
	@Query(value = "UPDATE utilisateur u SET u.id_profil = null WHERE u.id_profil = ?", nativeQuery = true)
	public User removeProfileUser(Profile profile);

	@Modifying
	@Query(value = "UPDATE utilisateur u SET u.téléphone_utilisateur = '0123456780' WHERE u.nom_utilisateur = ?", nativeQuery = true)
	public User updatePhoneUser(String nameUser);

	/**
	 * Modification de la méthode findById du CrudRepository
	 * 
	 * @param department
	 * @param sort
	 * @return liste des utilisateurs d'un service
	 */
	public Iterable<User> findByDepartment(Department department, Sort sort);

}
