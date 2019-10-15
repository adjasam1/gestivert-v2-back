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

import gesticert.model.Server;
import gesticert.service.ServerService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Serveur
 * 
 * @see ServerService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/serveur")
public class ServerController {
	
	/**
	 * injection des dependances
	 */
	private ServerService serverServ;

	/**
	 * Constructeur
	 * 
	 * @param serverServ
	 */
	public ServerController(ServerService serverServ) {
		this.serverServ = serverServ;
	}
	
	/**
	 * Mapping qui gèrent les requêtes HTTP entrantes (GET, POST, PUT, DELETE)
	 * 
	 * 
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllServers()
	{
		List<Server> listServers = null;
		
		try
		{
			listServers = serverServ.getAllServers();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listServers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listServers);
	}
	
	@GetMapping("/id={idServer}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getServerById(@PathVariable Integer idServer)
	{
		Optional<Server> listServers = null;
		
		try
		{
			listServers = serverServ.getServerById(idServer);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listServers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listServers);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postServer(@RequestBody Server server)
	{
		Server newServer = null;
		
		String nameServer = server.getNameServer();
		if ((nameServer == null) || nameServer.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du serveur doit être renseigné");
		}
		
		try
		{
			newServer = serverServ.createServer(server);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newServer);
	}
	
	@PutMapping("/modification={idServer}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putServer(@RequestBody Server server, @PathVariable Integer idServer)
	{
		Server modifyServer = null;
		getServerById(idServer);
		
		String nameServer = server.getNameServer();
		if ((nameServer == null) || (nameServer.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du serveur doit être renseigné");
		}
		
		try
		{
			modifyServer = serverServ.updateServer(server, idServer);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyServer);
	}
	
	@DeleteMapping("/suppression={idServer}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteServer(@PathVariable Integer idServer)
	{
		try
		{
			serverServ.deleteServer(idServer);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
