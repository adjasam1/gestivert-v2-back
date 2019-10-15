package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.TypeDemand;
import gesticert.repository.TypeDemandRepository;

/**
 * Implémentation de TypeDemandeService contenant les méthodes CRUD pour
 * l'entité TypeDemande
 * 
 * @see TypeDemandService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class TypeDemandServiceImpl implements TypeDemandService {

	/**
	 * Injection des dépendances
	 * 
	 * @see TypeDemandRepository
	 */
	private TypeDemandRepository typeDemandRepo;

	/**
	 * Constructeur
	 * 
	 * @param typeDemandRepo
	 */
	public TypeDemandServiceImpl(TypeDemandRepository typeDemandRepo) {
		this.typeDemandRepo = typeDemandRepo;
	}
	
	/**
	 * Recherche de tous les types de demande
	 */
	@Override
	public List<TypeDemand> getAllTypesDemand() {
		return typeDemandRepo.findAll();
	}
	
	/**
	 * Recherche d'un type de demande par son id
	 */
	@Override
	public Optional<TypeDemand> getTypeDemandById(Integer idTypeDemand) {
		return typeDemandRepo.findById(idTypeDemand);
	}

	/**
	 * Création d'un type de demande
	 */
	@Override
	public TypeDemand createTypeDemand(TypeDemand typeDemand) {
		return typeDemandRepo.saveAndFlush(typeDemand);
	}
	
	/**
	 * Modification d'un type de demande
	 */
	@Override
	public TypeDemand updateTypeDemand(TypeDemand typeDemand, Integer idTypeDemand) {
		return typeDemandRepo.saveAndFlush(typeDemand);
	}
	
	/**
	 * Suppression d'un type de demande
	 */
	@Override
	public void deleteTypeDemand(Integer idTypeDemand) {
		typeDemandRepo.deleteById(idTypeDemand);
	}

}
