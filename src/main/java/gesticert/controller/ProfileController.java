package gesticert.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import gesticert.model.Profile;
import gesticert.model.User;
import gesticert.service.ProfileService;
import gesticert.service.UserService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Profil
 * 
 * @see ProfileService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@RestController
@RequestMapping("/api/profil")
public class ProfileController {

	/**
	 * Injection des dépendances
	 */
	private ProfileService profileServ;

	@Autowired
	private UserService userServ;

	/**
	 * constructeur
	 * 
	 * @param profileServ
	 */
	public ProfileController(ProfileService profileServ) {
		this.profileServ = profileServ;
	}

	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idProfile
	 * @param profile
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllProfiles() {
		List<Profile> listProfiles = null;

		try {
			listProfiles = profileServ.getAllProfiles();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 : Erreur serveur");
		}

		if (listProfiles == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body(listProfiles);
	}

	@GetMapping("/id={idProfile}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getProfileById(@PathVariable Integer idProfile) {
		Optional<Profile> listProfiles = null;

		try {
			listProfiles = profileServ.getProfileById(idProfile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listProfiles == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listProfiles);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postProfile(@RequestBody Profile profile) {
		Profile newProfile = null;

		String typeProfile = profile.getTypeProfile();
		if ((typeProfile == null) || typeProfile.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type de profil non renseigné");
		}

		try {
			newProfile = profileServ.createProfile(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
	}

	@PutMapping("/modification={idProfile}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putProfile(@RequestBody Profile profile, @PathVariable Integer idProfile) {
		Profile modifyProfile = null;
		getProfileById(idProfile);

		String typeProfile = profile.getTypeProfile();
		if ((typeProfile == null) || (typeProfile.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}

		try {
			modifyProfile = profileServ.updateProfile(profile, idProfile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyProfile);
	}

	@DeleteMapping("/suppression={idProfile}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProfile(@PathVariable Integer idProfile) {
		List<User> users = null;
		users = userServ.getAllUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println("a " + users.get(i).getProfile().getTypeProfile());
			if (users.get(i).getProfile().getTypeProfile() == profileServ.getProfileById(idProfile).get()
					.getTypeProfile()) {
				System.out.println("bb " + users.get(i).getIdUser());
				System.out.println("cc " + users.get(i).getProfile());
				users.get(i).setProfile(null);
				System.out.println("dd " + users.get(i).getProfile());
				userServ.updateUser(users.get(i));
			}
		}
		try {
			profileServ.deleteProfile(idProfile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
