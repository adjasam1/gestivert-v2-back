package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.StatusDemand;
import gesticert.repository.StatusDemandRepository;

/**
 * Implémentation de StatutDemandeService contenant les méthodes CRUD pour
 * l'entité StatutDemande
 * 
 * @see StatusDemandService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class StatusDemandServiceImpl implements StatusDemandService {

	/**
	 * Injection des dépendances
	 * 
	 * @see StatusDemandRepository
	 */
	private StatusDemandRepository statusDemandRepo;

	/**
	 * Constructeur
	 * 
	 * @param statusDemandRepo
	 */
	public StatusDemandServiceImpl(StatusDemandRepository statusDemandRepo) {
		this.statusDemandRepo = statusDemandRepo;
	}
	
	/**
	 * Recherche de tous les statuts des demandes
	 */
	@Override
	public List<StatusDemand> getAllStatusDemand() {
		return statusDemandRepo.findAll();
	}
	
	/**
	 * Recherche d'un statut de demande par son id
	 */
	@Override
	public Optional<StatusDemand> getStatusDemandById(Integer idStatusDemand) {
		return statusDemandRepo.findById(idStatusDemand);
	}

	/**
	 * Création d'un statut de demande
	 */
	@Override
	public StatusDemand createStatusDemand(StatusDemand statusDemand) {
		return statusDemandRepo.saveAndFlush(statusDemand);
	}
	
	/**
	 * Modification d'un statut de demande
	 */
	@Override
	public StatusDemand updateStatusDemand(StatusDemand statusDemand, Integer idStatusDemand) {
		return statusDemandRepo.saveAndFlush(statusDemand);
	}
	
	/**
	 * Suppression d'un statut de demande
	 */
	@Override
	public void deleteStatusDemand(Integer idStatusDemand) {
		statusDemandRepo.deleteById(idStatusDemand);
	}

}
