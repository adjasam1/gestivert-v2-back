package gesticert.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gesticert.model.Department;
import gesticert.service.DepartmentService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Service
 * 
 * @see DepartmentService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/service")
public class DepartmentController {

	/**
	 * Injection des dependances
	 */
	private DepartmentService departmentServ;

	/**
	 * Constructeur
	 * 
	 * @param departmentServ
	 */
	public DepartmentController(DepartmentService departmentServ) {
		this.departmentServ = departmentServ;
	}

	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idDepartment
	 * @param department
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllDepartments() {
		List<Department> listDepartments = null;

		try {
			listDepartments = departmentServ.getAllDepartments();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listDepartments == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listDepartments);
	}

	@GetMapping("/id={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getDepartmentById(@PathVariable Integer idDepartment) {
		Optional<Department> listDepartment = null;

		try {
			listDepartment = departmentServ.getDepartmentById(idDepartment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listDepartment == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listDepartment);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postDepartment(@RequestBody Department department) {
		Department newDepartment = null;

		String nameDepartment = department.getNameDepartment();
		if ((nameDepartment == null) || (nameDepartment.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du service doit être renseigné");
		}

		try {
			newDepartment = departmentServ.createDepartment(department);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
	}

	@PutMapping("/modification={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putDepartment(@RequestBody Department department, @PathVariable Integer idDepartment) {
		Department modifyDepartment = null;
		getDepartmentById(idDepartment);

		String nameDepartment = department.getNameDepartment();
		if ((nameDepartment == null) || (nameDepartment.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du service doit être renseigné");
		}

		try {
			modifyDepartment = departmentServ.updateDepartment(department, idDepartment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyDepartment);
	}

	@DeleteMapping("/suppression={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer idDepartment) {
		try {
			departmentServ.deleteDepartment(idDepartment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}

}
