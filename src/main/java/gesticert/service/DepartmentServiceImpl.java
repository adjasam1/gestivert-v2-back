package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Department;
import gesticert.repository.DepartmentRepository;

/**
 * Implémentation de ServiceService contenant les méthodes CRUD pour l'entité
 * Service
 * 
 * @see DepartmentService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	/**
	 * Injection des dépendances
	 * 
	 * @see DepartmentRepository
	 */
	private DepartmentRepository departmentRepo;

	/**
	 * Constructeur
	 * 
	 * @param departmentRepo
	 */
	public DepartmentServiceImpl(DepartmentRepository departmentRepo) {
		this.departmentRepo = departmentRepo;
	}

	/**
	 * Recherche de tous les services
	 */
	@Override
	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}

	/**
	 * Recherche d'un service par son id
	 */
	@Override
	public Optional<Department> getDepartmentById(Integer idDepartment) {
		return departmentRepo.findById(idDepartment);
	}

	/**
	 * Création d'un service
	 */
	@Override
	public Department createDepartment(Department department) {
		return departmentRepo.saveAndFlush(department);
	}

	/**
	 * Modification d'un service
	 */
	@Override
	public Department updateDepartment(Department department, Integer idDepartment) {
		return departmentRepo.saveAndFlush(department);
	}

	/*
	 * Suppression d'un service
	 */
	@Override
	public void deleteDepartment(Integer idDepartment) {
		departmentRepo.deleteById(idDepartment);
	}

}
