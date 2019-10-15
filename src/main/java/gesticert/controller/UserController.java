package gesticert.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gesticert.dto.JsonWebToken;
import gesticert.exception.InvalidCredentialsException;
import gesticert.model.Department;
import gesticert.model.Profile;
import gesticert.model.User;
import gesticert.service.UserService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Utilisateur
 * 
 * @see UserService
 * @see EntityManager
 * @see BCryptPasswordEncoder
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/utilisateur")
public class UserController {

	/**
	 * Injection des dépendances
	 */

	private UserService userServ;

	@Autowired
	private UserDetailsService userDetailsServ;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	/**
	 * Constructeur
	 * 
	 * @param userServ
	 */
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}

	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param user
	 * @param idUser
	 * @param idRHUser
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@PostMapping("/sign-in")
	public ResponseEntity<JsonWebToken> signIn(@RequestBody User user) {
		try {
			JsonWebToken token = new JsonWebToken(userServ.signin(user.getIdRHUser(), user.getPasswordUser()));
			return ResponseEntity.ok(token);
		} catch (InvalidCredentialsException ex) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/idrh2={idRHUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getByIdRH(String idRHUser) {
		UserDetails getUser = null;

		try {
			getUser = userDetailsServ.loadUserByUsername(idRHUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (getUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour cet identifiant RH");
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUser);
	}

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllUsers() {
		List<User> listUsers = null;

		try {
			listUsers = userServ.getAllUsers();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 : Erreur serveur");
		}

		if (listUsers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}

	@GetMapping("/idrh={idRHUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByIdRH(@PathVariable String idRHUser) {
		Optional<User> getUser = null;

		try {
			getUser = userServ.getUserByIdRH(idRHUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (getUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour cet identifiant RH");
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUser);
	}

	@GetMapping("/profil={profile}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByProfile(@PathVariable Profile profile) {
		Iterable<User> getUser = null;

		try {
			getUser = userServ.getUserByProfile(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (getUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur avec ce profil");
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUser);
	}

	@PutMapping("/retrait/profil={profile}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> removeProfile(@PathVariable Profile profile) {
		User modifyUser = null;

		try {
			modifyUser = userServ.removeProfile(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
	}

	@PutMapping("/modification/tel/nom={nameUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putPhoneByName(@PathVariable String nameUser) {
		User modifyUser = null;

		try {
			modifyUser = userServ.updatePhoneUser(nameUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
	}

	@GetMapping("/service={department}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByDepartment(@PathVariable Department department) {
		Iterable<User> getUser = null;

		try {
			getUser = userServ.getUserByDepartment(department);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (getUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour ce service");
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUser);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postUser(@RequestBody User user) {
		User newUser = null;

		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de l'utilisateur doit être renseigné");
		}

		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}

		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}

		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}

		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le mot de passe de l'utilisateur doit être renseigné");
		}

		try {
			newUser = null;
			user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			newUser = userServ.createUser(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@PutMapping("/modification={idUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putUser(@RequestBody User user, @PathVariable Integer idUser) {
		User modifyUser = null;

		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom de l'utilisateur doit être renseigné");
		}

		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}

		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}

		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}

		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le mot de passe de l'utilisateur doit être renseigné");
		}

		try {
			modifyUser = userServ.updateUser(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
	}

	@DeleteMapping("/suppression={idUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Integer idUser) {
		try {
			userServ.deleteUser(idUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 : Inexistant");
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}

}
