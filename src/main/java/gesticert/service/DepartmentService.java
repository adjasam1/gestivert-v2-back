package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Department;

/**
 * Service pour l'entité Service
 * 
 * @see Department
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface DepartmentService {

	/**
	 * Recherche de tous les services
	 * 
	 * @return liste de tous les services
	 */
	public List<Department> getAllDepartments();

	/**
	 * Recherche d'un service par son id
	 * 
	 * @param idDepartment
	 * @return un service
	 */
	public Optional<Department> getDepartmentById(Integer idDepartment);

	/**
	 * Création d'un service
	 * 
	 * @param department
	 * @return Ajout d'un service
	 */
	public Department createDepartment(Department department);

	/**
	 * Modification d'un service
	 * 
	 * @param department
	 * @param idDepartment
	 * @return modification d'un service
	 */
	public Department updateDepartment(Department department, Integer idDepartment);

	/**
	 * Suppression d'un service
	 * 
	 * @param idDepartment
	 */
	public void deleteDepartment(Integer idDepartment);

}
