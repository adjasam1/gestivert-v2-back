package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.StatusDemand;

/**
 * Service pour l'entité StatutDemande
 * 
 * @see StatusDemand
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface StatusDemandService {

	/**
	 * Recherche de tous les statuts des demandes
	 * 
	 * @return liste de tous les statuts des demandes
	 */
	public List<StatusDemand> getAllStatusDemand();

	/**
	 * Recherche d'un statut de demande par son id
	 * 
	 * @param idStatusDemand
	 * @return un statut de demande
	 */
	public Optional<StatusDemand> getStatusDemandById(Integer idStatusDemand);

	/**
	 * Création d'un statut de demande
	 * 
	 * @param statusDemand
	 * @return ajout d'un statut de demande
	 */
	public StatusDemand createStatusDemand(StatusDemand statusDemand);

	/**
	 * Modification d'un statut de demande
	 * 
	 * @param statusDemand
	 * @param idStatusDemand
	 * @return modification d'un statut de demande
	 */
	public StatusDemand updateStatusDemand(StatusDemand statusDemand, Integer idStatusDemand);

	/**
	 * Suppression d'un statut de demande
	 * 
	 * @param idStatusDemand
	 */
	public void deleteStatusDemand(Integer idStatusDemand);

}
