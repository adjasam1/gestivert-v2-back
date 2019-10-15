package gesticert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gesticert.model.Environment;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité Environnement
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Integer> {

}
