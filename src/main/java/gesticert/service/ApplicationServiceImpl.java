package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gesticert.model.Application;
import gesticert.repository.ApplicationRepository;

/**
 * Implémentation de ApplicationService contenant les méthodes CRUD pour
 * l'entité Application
 * 
 * @see ApplicationService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	/**
	 * Injection des dépendances
	 * 
	 * @see ApplicationRepository
	 */
	private ApplicationRepository applicationRepo;

	/**
	 * Constructeur
	 * 
	 * @param applicationRepo
	 */
	public ApplicationServiceImpl(ApplicationRepository applicationRepo) {
		this.applicationRepo = applicationRepo;
	}

	/**
	 * Recherche de toutes les applications
	 */
	@Override
	public List<Application> getAllApplications() {
		return applicationRepo.findAll(new Sort(Sort.Direction.ASC, "codeCCX"));
	}

	/**
	 * Recherche d'une application par son id
	 */
	@Override
	public Optional<Application> getApplicationById(Integer idApplication) {
		return applicationRepo.findById(idApplication);
	}

	/**
	 * Création d'une application
	 */
	@Override
	public Application createApplication(Application application) {
		return applicationRepo.saveAndFlush(application);
	}

	/**
	 * Modification d'une application
	 */
	@Override
	public Application updateApplication(Application application, Integer idApplication) {
		return applicationRepo.saveAndFlush(application);
	}

	/**
	 * Suppression d'une application
	 */
	@Override
	public void deleteApplication(Integer idApplication) {
		applicationRepo.deleteById(idApplication);
	}

}
