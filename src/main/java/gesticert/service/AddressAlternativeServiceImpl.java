package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.AddressAlternative;
import gesticert.model.Certificate;
import gesticert.repository.AddressAlternativeRepository;

/**
 * Implémentation de AdresseAlternativeService contenant les méthodes CRUD pour
 * l'entité AdresseAlternative
 * 
 * @see AddressAlternativeService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class AddressAlternativeServiceImpl implements AddressAlternativeService {

	/**
	 * Injection des dépendances
	 * 
	 * @see AddressAlternativeRepository
	 */
	private AddressAlternativeRepository addressAlternativeRepo;

	/**
	 * Constructeur
	 * 
	 * @param addressAlternativeRepo
	 */
	public AddressAlternativeServiceImpl(AddressAlternativeRepository addressAlternativeRepo) {
		this.addressAlternativeRepo = addressAlternativeRepo;
	}

	/**
	 * Recherche de toutes les adresses alternatives
	 */
	@Override
	public List<AddressAlternative> getAllAddressAlternatives() {
		return addressAlternativeRepo.findAll();
	}

	/**
	 * Recherche d'une adresse alternative par son id
	 */
	@Override
	public Optional<AddressAlternative> getAddressAlternativeById(Integer idAddressAlternative) {
		return addressAlternativeRepo.findById(idAddressAlternative);
	}

	/**
	 * Recherche des adresses alternatives d'un certificat
	 */
	@Override
	public Iterable<AddressAlternative> getAddressAlternativeByCertificate(Certificate certificate) {
		return addressAlternativeRepo.findByCertificate(certificate);
	}

	/**
	 * Création d'une adresse alternative
	 */
	@Override
	public AddressAlternative createAddressAlternative(AddressAlternative addressAlternative) {
		return addressAlternativeRepo.saveAndFlush(addressAlternative);
	}

	/**
	 * Modification d'une adresse alternative
	 */
	@Override
	public AddressAlternative updateAddressAlternative(AddressAlternative addressAlternative) {
		return addressAlternativeRepo.saveAndFlush(addressAlternative);
	}

	/**
	 * Suppression d'une adresse alternative
	 */
	@Override
	public void deleteAddressAlternative(Integer idAddressAlternative) {
		addressAlternativeRepo.deleteById(idAddressAlternative);
	}

}
