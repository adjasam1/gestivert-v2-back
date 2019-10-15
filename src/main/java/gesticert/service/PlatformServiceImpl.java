package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Platform;
import gesticert.repository.PlatformRepository;

/**
 * Implémentation de PlateformeService contenant les méthodes CRUD pour l'entité
 * Plateforme
 * 
 * @see PlatformService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class PlatformServiceImpl implements PlatformService {

	/**
	 * Injection des dépendances
	 * 
	 * @see PlatformRepository
	 */
	private PlatformRepository platformRepo;

	/**
	 * Constructeur
	 * 
	 * @param platformRepo
	 */
	public PlatformServiceImpl(PlatformRepository platformRepo) {
		this.platformRepo = platformRepo;
	}
	
	/**
	 * Recherche de toutes les plateformes
	 */
	@Override
	public List<Platform> getAllPlatforms() {
		return platformRepo.findAll();
	}
	
	/**
	 * Recherche d'une plateforme par son id
	 */
	@Override
	public Optional<Platform> getPlatformById(Integer idPlatform) {
		return platformRepo.findById(idPlatform);
	}

	/**
	 * Création d'une plateforme
	 */
	@Override
	public Platform createPlatform(Platform platform) {
		return platformRepo.saveAndFlush(platform);
	}
	
	/**
	 * Modification d'une adresse alternative
	 */
	@Override
	public Platform updatePlatform(Platform platform, Integer idPlatform) {
		return platformRepo.saveAndFlush(platform);
	}

	/**
	 * Suppression d'une plateforme
	 */
	@Override
	public void deletePlatform(Integer idPlatform) {
		platformRepo.deleteById(idPlatform);
	}

}
