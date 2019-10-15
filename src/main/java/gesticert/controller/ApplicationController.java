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

import gesticert.model.Application;
import gesticert.service.ApplicationService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Application
 * 
 * @see ApplicationService
 * 
 * @author Samuel Sabot
 * @version 2.0
 *
 */
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	/**
	 * injection des dependances
	 */
	private ApplicationService applicationServ;

	/**
	 * constructeur
	 * 
	 * @param applicationServ
	 */
	public ApplicationController(ApplicationService applicationServ) {
		this.applicationServ = applicationServ;
	}

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT,
	 * DELETE) avec url
	 * 
	 * @param idApplication
	 * @param application
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllApplications() {
		List<Application> listApplications = null;

		try {
			listApplications = applicationServ.getAllApplications();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listApplications == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}

	@GetMapping("/id={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getApplicationById(@PathVariable Integer idApplication) {
		Optional<Application> listApplications = null;

		try {
			listApplications = applicationServ.getApplicationById(idApplication);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listApplications == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postApplication(@RequestBody Application application) {
		Application newApplication = null;

		String codeCCX = application.getCodeCCX();
		if ((codeCCX == null) || codeCCX.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le code CCX de l'application doit être renseigné");
		}

		try {
			newApplication = applicationServ.createApplication(application);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newApplication);
	}

	@PutMapping("/modification={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putApplication(@RequestBody Application application, @PathVariable Integer idApplication) {
		Application modifyApplication = null;
		getApplicationById(idApplication);

		String codeCCX = application.getCodeCCX();
		if ((codeCCX == null) || codeCCX.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le code CCX de l'application doit être renseigné");
		}

		try {
			modifyApplication = applicationServ.updateApplication(application, idApplication);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyApplication);
	}

	@DeleteMapping("/suppression={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteApplication(@PathVariable Integer idApplication) {
		try {
			applicationServ.deleteApplication(idApplication);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
