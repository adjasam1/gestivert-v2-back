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

import gesticert.model.TypeDemand;
import gesticert.service.TypeDemandService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité TypeDemande
 * 
 * @see TypeDemandService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/typedemande")
public class TypeDemandController {
	
	/**
	 * injection des dependances de TypeDemandService
	 */
	private TypeDemandService typeDemandServ;

	/**
	 * Constructeur
	 * 
	 * @param typeDemandServ
	 */
	public TypeDemandController(TypeDemandService typeDemandServ) {
		this.typeDemandServ = typeDemandServ;
	}
	
	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * @param idTypeDemand
	 * @param typeDemand
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllTypeDemands()
	{
		List<TypeDemand> listTypeDemands = null;
		
		try
		{
			listTypeDemands = typeDemandServ.getAllTypesDemand();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listTypeDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listTypeDemands);
	}
	
	@GetMapping("/id={idTypeDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getTypeDemandById(@PathVariable Integer idTypeDemand)
	{
		Optional<TypeDemand> listTypeDemands = null;
		
		try
		{
			listTypeDemands = typeDemandServ.getTypeDemandById(idTypeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listTypeDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listTypeDemands);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postTypeDemand(@RequestBody TypeDemand typeDemand)
	{
		TypeDemand newTypeDemand = null;
		
		String typeTypeDemand = typeDemand.getNameTypeDemand();
		if ((typeTypeDemand == null) || typeTypeDemand.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type de la demande doit être renseigné");
		}
		
		try
		{
			newTypeDemand = typeDemandServ.createTypeDemand(typeDemand);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newTypeDemand);
	}
	
	@PutMapping("/modification={idTypeDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putTypeDemand(@RequestBody TypeDemand typeDemand, @PathVariable Integer idTypeDemand)
	{
		TypeDemand modifyTypeDemand = null;
		
		String typeTypeDemand = typeDemand.getNameTypeDemand();
		if ((typeTypeDemand == null) || (typeTypeDemand.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type de la demande doit être renseigné");
		}
		
		try
		{
			modifyTypeDemand = typeDemandServ.updateTypeDemand(typeDemand, idTypeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyTypeDemand);
	}
	
	@DeleteMapping("/suppression={idTypeDemand}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteTypeDemand(@PathVariable Integer idTypeDemand)
	{
		try
		{
			typeDemandServ.deleteTypeDemand(idTypeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
