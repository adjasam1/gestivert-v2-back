package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Platform;

/**
 * Service pour l'entité Plateforme
 * 
 * @see Platform
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface PlatformService {

	/**
	 * Recherche de toutes les plateformes
	 * 
	 * @return liste de toutes les plateformes
	 */
	public List<Platform> getAllPlatforms();

	/**
	 * Recherche d'une plateforme par son id
	 * 
	 * @param idPlatform
	 * @return une plateforme
	 */
	public Optional<Platform> getPlatformById(Integer idPlatform);

	/**
	 * Création d'une plateforme
	 * 
	 * @param platform
	 * @return ajout d'une plateforme
	 */
	public Platform createPlatform(Platform platform);

	/**
	 * Modification d'une adresse alternative
	 * 
	 * @param platform
	 * @param idPlatform
	 * @return modification d'une plateforme
	 */
	public Platform updatePlatform(Platform platform, Integer idPlatform);

	/**
	 * Suppression d'une plateforme
	 * 
	 * @param idPlatform
	 */
	public void deletePlatform(Integer idPlatform);

}
