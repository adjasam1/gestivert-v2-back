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

import gesticert.model.Root;
import gesticert.service.RootService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Racine
 * 
 * @see RootService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@RestController
@RequestMapping("/api/racine")
public class RootController {

	/**
	 * Injection des dependances de RootService
	 */
	private RootService rootServ;

	/**
	 * Constructeur
	 * 
	 * @param rootServ
	 */
	public RootController(RootService rootServ) {
		this.rootServ = rootServ;
	}

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT,
	 * DELETE) avec url
	 * 
	 * @param idRoot
	 * @param root
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllRoots() {
		List<Root> listRoots = null;

		try {
			listRoots = rootServ.getAllRoots();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listRoots == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listRoots);
	}

	@GetMapping("/id={idRoot}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getRootById(@PathVariable Integer idRoot) {
		Optional<Root> listRoots = null;

		try {
			listRoots = rootServ.getRootById(idRoot);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listRoots == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listRoots);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postRoot(@RequestBody Root root) {
		Root newRoot = null;

		String nameRoot = root.getNameRoot();
		if ((nameRoot == null) || nameRoot.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la racine doit être renseigné");
		}

		try {
			newRoot = rootServ.createRoot(root);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newRoot);
	}

	@PutMapping("/modification={idRoot}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putEnvironment(@RequestBody Root root, @PathVariable Integer idRoot) {
		Root modifyRoot = null;

		String nameRoot = root.getNameRoot();
		if ((nameRoot == null) || (nameRoot.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la racine doit être renseigné");
		}

		try {
			modifyRoot = rootServ.updateRoot(root, idRoot);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyRoot);
	}

	@DeleteMapping("/suppression={idRoot}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idRoot) {
		try {
			rootServ.deleteRoot(idRoot);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
