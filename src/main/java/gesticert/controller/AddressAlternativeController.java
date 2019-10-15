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

import gesticert.model.AddressAlternative;
import gesticert.model.Certificate;
import gesticert.service.AddressAlternativeService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité
 * AdresseAlternative
 * 
 * @see AddressAlternativeService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/adressealternative")
public class AddressAlternativeController {

	/**
	 * Injection des dépendances
	 */
	private AddressAlternativeService addressAlternativeServ;

	/**
	 * Constructeur
	 * 
	 * @param addressAlternativeServ
	 */
	public AddressAlternativeController(AddressAlternativeService addressAlternativeServ) {
		this.addressAlternativeServ = addressAlternativeServ;
	}

	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idAddressAlternative
	 * @param certificate
	 * @param addressAlternative
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllAddressAlternatives() {

		List<AddressAlternative> listAddressAlternative = null;

		try {
			listAddressAlternative = addressAlternativeServ.getAllAddressAlternatives();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 : Erreur serveur");
		}

		if (listAddressAlternative == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternative);
	}

	@GetMapping("/{idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAddressAlternativeById(@PathVariable Integer idAddressAlternative) {

		Optional<AddressAlternative> listAddressAlternatives = null;

		try {
			listAddressAlternatives = addressAlternativeServ.getAddressAlternativeById(idAddressAlternative);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listAddressAlternatives == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternatives);
	}

	@GetMapping("/certificat={certificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAddressAlternativeByCertificate(@PathVariable Certificate certificate) {

		Iterable<AddressAlternative> listAddressAlternative = null;

		try {
			listAddressAlternative = addressAlternativeServ.getAddressAlternativeByCertificate(certificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 : Erreur serveur");
		}

		if (listAddressAlternative == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternative);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postAddressAlternative(@RequestBody AddressAlternative addressAlternative) {

		AddressAlternative newAddressAlternative = null;

		String linkAddressAlternative = addressAlternative.getLinkAddressAlternative();
		if (linkAddressAlternative == null || linkAddressAlternative.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("400 : Le lien de l'adresse alternative doit être renseigné");
		}

		Integer idCertificat = addressAlternative.getCertificate().getIdCertificate();
		if (idCertificat == null || idCertificat == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("400 : L'identifiant du certificat de l'adresse alternative doit être renseigné");
		}

		try {
			newAddressAlternative = addressAlternativeServ.createAddressAlternative(addressAlternative);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("500 : Erreur serveur" + e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newAddressAlternative);
	}

	@PutMapping("/modification={idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putAddressAlternative(@RequestBody AddressAlternative addressAlternative,
			@PathVariable Integer idAddressAlternative) {

		AddressAlternative modifyAddressAlternative = null;

		String linkAddressAlternative = addressAlternative.getLinkAddressAlternative();
		if (linkAddressAlternative == null || linkAddressAlternative.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("400 : Le lien de l'adresse alternative doit être renseigné");
		}

		Integer idCertificat = addressAlternative.getCertificate().getIdCertificate();
		if (idCertificat == null || idCertificat == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("400 : L'identifiant du certificat de l'adresse alternative doit être renseigné");
		}

		try {
			modifyAddressAlternative = addressAlternativeServ.updateAddressAlternative(addressAlternative);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyAddressAlternative);
	}

	@DeleteMapping("/suppression={idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idAddressAlternative) {

		try {
			addressAlternativeServ.deleteAddressAlternative(idAddressAlternative);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body("200 : Ok");
	}

}
