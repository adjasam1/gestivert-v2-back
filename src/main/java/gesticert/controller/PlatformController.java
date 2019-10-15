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

import gesticert.model.Platform;
import gesticert.service.PlatformService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Plateforme
 * 
 * @see PlatformService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/plateforme")
public class PlatformController {

	/**
	 * injection des dependances
	 */
	private PlatformService platformServ;

	/**
	 * Constructeur
	 * 
	 * @param platformServ
	 */
	public PlatformController(PlatformService platformServ) {
		this.platformServ = platformServ;
	}

	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idPlatform
	 * @param platform
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllPlatforms() {
		List<Platform> listPlatforms = null;

		try {
			listPlatforms = platformServ.getAllPlatforms();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listPlatforms == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listPlatforms);
	}

	@GetMapping("/id={idPlatform}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getPlatformById(@PathVariable Integer idPlatform) {
		Optional<Platform> listPlatforms = null;

		try {
			listPlatforms = platformServ.getPlatformById(idPlatform);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listPlatforms == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listPlatforms);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postPlatform(@RequestBody Platform platform) {
		Platform newPlatform = null;

		String namePlatform = platform.getNamePlatform();
		if ((namePlatform == null) || namePlatform.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la plateforme doit être renseigné");
		}

		try {
			newPlatform = platformServ.createPlatform(platform);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newPlatform);
	}

	@PutMapping("/modification={idPlatform}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putPlatform(@RequestBody Platform platform, @PathVariable Integer idPlatform) {
		Platform modifyPlatform = null;

		String namePlatform = platform.getNamePlatform();
		if ((namePlatform == null) || (namePlatform.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la plateforme doit être renseigné");
		}

		try {
			modifyPlatform = platformServ.updatePlatform(platform, idPlatform);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyPlatform);
	}

	@DeleteMapping("/suppression={idPlatform}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deletePlatform(@PathVariable Integer idPlatform) {
		try {
			platformServ.deletePlatform(idPlatform);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
