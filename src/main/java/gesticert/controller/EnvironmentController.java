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

import gesticert.model.Environment;
import gesticert.service.EnvironmentService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Environnement
 * 
 * @see EnvironmentService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@RestController
@RequestMapping("/api/environnement")
public class EnvironmentController {
	
	/**
	 * Injection des dépendances
	 */
	private EnvironmentService environmentServ;

	/**
	 * Constructeur
	 * 
	 * @param environmentServ
	 */
	public EnvironmentController(EnvironmentService environmentServ) {
		this.environmentServ = environmentServ;
	}
	
	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idEnvironment
	 * @param environment
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllEnvironments()
	{
		List<Environment> listEnvironments = null;
		
		try
		{
			listEnvironments = environmentServ.getAllEnvironments();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listEnvironments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listEnvironments);
	}
	
	@GetMapping("/id={idEnvironment}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getEnvironmentById(@PathVariable Integer idEnvironment)
	{
		Optional<Environment> listEnvironments = null;
		
		try
		{
			listEnvironments = environmentServ.getEnvironmentById(idEnvironment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listEnvironments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listEnvironments);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postEnvironment(@RequestBody Environment environment)
	{
		Environment newEnvironment = null;
		
		String nameEnvironment = environment.getNameEnvironment();
		if ((nameEnvironment == null) || nameEnvironment.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}
		
		try
		{
			newEnvironment = environmentServ.createEnvironment(environment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newEnvironment);
	}
	
	@PutMapping("/modification={idEnvironment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putEnvironment(@RequestBody Environment environment, @PathVariable Integer idEnvironment)
	{
		Environment modifyEnvironment = null;
		getEnvironmentById(idEnvironment);
		
		String nameEnvironment = environment.getNameEnvironment();
		if ((nameEnvironment == null) || (nameEnvironment.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}
		
		try
		{
			modifyEnvironment = environmentServ.updateEnvironment(environment, idEnvironment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyEnvironment);
	}
	
	@DeleteMapping("/suppression={idEnvironment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idEnvironment)
	{
		try
		{
			environmentServ.deleteEnvironment(idEnvironment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
