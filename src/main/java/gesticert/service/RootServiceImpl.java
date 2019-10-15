package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Root;
import gesticert.repository.RootRepository;

/**
 * Implémentation de RacineService contenant les méthodes CRUD pour l'entité
 * Racine
 * 
 * @see RootService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class RootServiceImpl implements RootService {

	/**
	 * Injection des dépendances
	 * 
	 * @see RootRepository
	 */
	private RootRepository rootRepo;

	/**
	 * Constructeur
	 * 
	 * @param rootRepo
	 */
	public RootServiceImpl(RootRepository rootRepo) {
		this.rootRepo = rootRepo;
	}
	
	/**
	 * Recherche de toutes les racines
	 */
	@Override
	public List<Root> getAllRoots() {
		return rootRepo.findAll();
	}
	
	/**
	 * Recherche d'une racine par son id
	 */
	@Override
	public Optional<Root> getRootById(Integer idRoot) {
		return rootRepo.findById(idRoot);
	}

	/**
	 * Création d'une racine
	 */
	@Override
	public Root createRoot(Root root) {
		return rootRepo.saveAndFlush(root);
	}
	
	/**
	 * Modification d'une racine
	 */
	@Override
	public Root updateRoot(Root root, Integer idRoot) {
		return rootRepo.saveAndFlush(root);
	}
	
	/**
	 * Suppression d'une racine
	 */
	@Override
	public void deleteRoot(Integer idRoot) {
		rootRepo.deleteById(idRoot);
	}

}
