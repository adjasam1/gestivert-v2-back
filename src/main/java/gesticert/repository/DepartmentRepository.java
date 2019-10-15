package gesticert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gesticert.model.Department;

/**
 * Référentiel qui étend JpaRepository avec les opérations de lecture et
 * d'écriture logiques pour l'entité Service
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
