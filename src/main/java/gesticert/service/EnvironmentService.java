package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Environment;

/**
 * Service pour l'entité Environnement
 * 
 * @see Environment
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface EnvironmentService {

	/**
	 * Recherche de tous les environnements
	 * 
	 * @return liste de tous les environnements
	 */
	public List<Environment> getAllEnvironments();

	/**
	 * Recherche d'un environnement par son id
	 * 
	 * @param idEnvironment
	 * @return un environnement
	 */
	public Optional<Environment> getEnvironmentById(Integer idEnvironment);

	/**
	 * Création d'un environnement
	 * 
	 * @param environment
	 * @return ajout d'un environnement
	 */
	public Environment createEnvironment(Environment environment);

	/**
	 * Modification d'un environnement
	 * 
	 * @param environment
	 * @param idEnvironment
	 * @return modification d'un environnement
	 */
	public Environment updateEnvironment(Environment environment, Integer idEnvironment);

	/**
	 * Suppression d'un environnement
	 * 
	 * @param idEnvironment
	 */
	public void deleteEnvironment(Integer idEnvironment);

}
