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

import gesticert.model.StatusDemand;
import gesticert.service.StatusDemandService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité StatutDemande
 * 
 * @see StatusDemandService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/statutdemande")
public class StatusDemandController {
	
	/**
	 * injection des dependances de StatusDemandService
	 */
	private StatusDemandService statusDemandServ;

	/**
	 * Constructeur
	 * 
	 * @param statusDemandServ
	 */
	public StatusDemandController(StatusDemandService statusDemandServ) {
		this.statusDemandServ = statusDemandServ;
	}
	
	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idStatusDemand
	 * @param statusDemand
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllStatusDemands()
	{
		List<StatusDemand> listStatusDemands = null;
		
		try
		{
			listStatusDemands = statusDemandServ.getAllStatusDemand();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listStatusDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listStatusDemands);
	}
	
	@GetMapping("/id={idStatusDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getStatusDemandById(@PathVariable Integer idStatusDemand)
	{
		Optional<StatusDemand> listStatusDemands = null;
		
		try
		{
			listStatusDemands = statusDemandServ.getStatusDemandById(idStatusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listStatusDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listStatusDemands);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postStatusDemand(@RequestBody StatusDemand statusDemand)
	{
		StatusDemand newStatusDemand = null;
		
		String nameStatusDemand = statusDemand.getStateDemand();
		if ((nameStatusDemand == null) || nameStatusDemand.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut de la demande doit être renseigné");
		}
		
		try
		{
			newStatusDemand = statusDemandServ.createStatusDemand(statusDemand);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newStatusDemand);
	}
	
	@PutMapping("/modification={idStatusDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putStatusDemand(@RequestBody StatusDemand statusDemand, @PathVariable Integer idStatusDemand)
	{
		StatusDemand modifyStatusDemand = null;
		getStatusDemandById(idStatusDemand);
		
		String nameStatusDemand = statusDemand.getStateDemand();
		if ((nameStatusDemand == null) || (nameStatusDemand.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut de la demande doit être renseigné");
		}
		
		try
		{
			modifyStatusDemand = statusDemandServ.updateStatusDemand(statusDemand, idStatusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyStatusDemand);
	}
	
	@DeleteMapping("/suppression={idStatusDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStatusDemand(@PathVariable Integer idStatusDemand)
	{
		try
		{
			statusDemandServ.deleteStatusDemand(idStatusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}
	
}
