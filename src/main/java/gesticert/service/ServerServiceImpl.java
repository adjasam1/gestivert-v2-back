package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Server;
import gesticert.repository.ServerRepository;

/**
 * Implémentation de ServeurService contenant les méthodes CRUD pour l'entité
 * Server
 * 
 * @see ServerService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class ServerServiceImpl implements ServerService {

	/**
	 * Injection des dépendances
	 * 
	 * @see ServerRepository
	 */
	private ServerRepository serverRepo;

	/**
	 * Constructeur
	 * 
	 * @param serverRepo
	 */
	public ServerServiceImpl(ServerRepository serverRepo) {
		this.serverRepo = serverRepo;
	}
	
	/**
	 * Recherche de tous les serveurs
	 */
	@Override
	public List<Server> getAllServers() {
		return serverRepo.findAll();
	}
	
	/**
	 * Recherche d'un serveur par son id
	 */
	@Override
	public Optional<Server> getServerById(Integer idServer) {
		return serverRepo.findById(idServer);
	}

	/**
	 * Création d'un serveur
	 */
	@Override
	public Server createServer(Server server) {
		return serverRepo.saveAndFlush(server);
	}
	
	/**
	 * Modification d'un serveur
	 */
	@Override
	public Server updateServer(Server server, Integer idServer) {
		return serverRepo.saveAndFlush(server);
	}
	
	/**
	 * Suppression d'un serveur
	 */
	@Override
	public void deleteServer(Integer idServer) {
		serverRepo.deleteById(idServer);
	}

}
