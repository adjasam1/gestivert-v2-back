package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.TypeDemand;

/**
 * Service pour l'entité Service
 * 
 * @see TypeDemand
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface TypeDemandService {

	/**
	 * Recherche de tous les types de demande
	 * 
	 * @return liste de tous les types de demande
	 */
	public List<TypeDemand> getAllTypesDemand();

	/**
	 * Recherche d'un type de demande par son id
	 * 
	 * @param idTypeDemand
	 * @return un type de demande
	 */
	public Optional<TypeDemand> getTypeDemandById(Integer idTypeDemand);

	/**
	 * Création d'un type de demande
	 * 
	 * @param typeDemand
	 * @return ajout d'un type de demande
	 */
	public TypeDemand createTypeDemand(TypeDemand typeDemand);

	/**
	 * Modification d'un type de demande
	 * 
	 * @param typeDemand
	 * @param idTypeDemand
	 * @return modification d'un type de demande
	 */
	public TypeDemand updateTypeDemand(TypeDemand typeDemand, Integer idTypeDemand);

	/**
	 * Suppression d'un type de demande
	 * 
	 * @param idTypeDemand
	 */
	public void deleteTypeDemand(Integer idTypeDemand);
	
	

}
