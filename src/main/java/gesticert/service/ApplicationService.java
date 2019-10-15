package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Application;

/**
 * Service pour l'entité Application
 * 
 * @see Application
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface ApplicationService {

	/**
	 * Recherche de toutes les applications
	 * 
	 * @return liste de toutes les applications
	 */
	public List<Application> getAllApplications();

	/**
	 * Recherche d'une application par son id
	 * 
	 * @param idApplication
	 * @return une application
	 */
	public Optional<Application> getApplicationById(Integer idApplication);

	/**
	 * Ajout d'une application
	 * 
	 * @param application
	 * @return ajout d'une application
	 */
	public Application createApplication(Application application);

	/**
	 * Modification d'une application
	 * 
	 * @param application
	 * @param idApplication
	 * @return modification d'une application
	 */
	public Application updateApplication(Application application, Integer idApplication);

	/**
	 * Suppression d'une application
	 * 
	 * @param idApplication
	 */
	public void deleteApplication(Integer idApplication);

}
