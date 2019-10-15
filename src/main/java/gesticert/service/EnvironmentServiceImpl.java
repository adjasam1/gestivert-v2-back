package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Environment;
import gesticert.repository.EnvironmentRepository;

/**
 * Implémentation de EnvironnementService contenant les méthodes CRUD pour
 * l'entité Environnement
 * 
 * @see EnvironmentService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	/**
	 * Injection des dépendances
	 * 
	 * @see EnvironmentRepository
	 */
	private EnvironmentRepository environmentRepo;

	/**
	 * Constructeur
	 * 
	 * @param environmentRepo
	 */
	public EnvironmentServiceImpl(EnvironmentRepository environmentRepo) {
		this.environmentRepo = environmentRepo;
	}
	
	/**
	 * Recherche de tous les environnements
	 */
	@Override
	public List<Environment> getAllEnvironments() {
		return environmentRepo.findAll();
	}
	
	/**
	 * Recherche d'un environnement par son id
	 */
	@Override
	public Optional<Environment> getEnvironmentById(Integer idEnvironment) {
		return environmentRepo.findById(idEnvironment);
	}

	/**
	 * Création d'un environnement
	 */
	@Override
	public Environment createEnvironment(Environment environment) {
		return environmentRepo.saveAndFlush(environment);
	}
	
	/**
	 * Modification d'un environnement
	 */
	@Override
	public Environment updateEnvironment(Environment environment, Integer idEnvironment) {
		return environmentRepo.saveAndFlush(environment);
	}
	
	/**
	 * Suppression d'un environnement
	 */
	@Override
	public void deleteEnvironment(Integer idEnvironment) {
		environmentRepo.deleteById(idEnvironment);
	}

}
