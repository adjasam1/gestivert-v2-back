package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Server;

/**
 * Service pour l'entité Serveur
 * 
 * @see Server
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface ServerService {

	/**
	 * Recherche de tous les serveurs
	 * 
	 * @return liste de tous les serveurs
	 */
	public List<Server> getAllServers();

	/**
	 * Recherche d'un serveur par son id
	 * 
	 * @param idServer
	 * @return un serveur
	 */
	public Optional<Server> getServerById(Integer idServer);

	/**
	 * Création d'un serveur
	 * 
	 * @param server
	 * @return ajout d'un serveur
	 */
	public Server createServer(Server server);

	/**
	 * Modification d'un serveur
	 * 
	 * @param server
	 * @param idServer
	 * @return modification d'un serveur
	 */
	public Server updateServer(Server server, Integer idServer);

	/**
	 * Suppression d'un serveur
	 * 
	 * @param idServer
	 */
	public void deleteServer(Integer idServer);

}
