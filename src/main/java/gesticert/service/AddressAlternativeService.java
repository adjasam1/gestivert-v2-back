package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.AddressAlternative;
import gesticert.model.Certificate;

/**
 * Service pour l'entité AdresseAlternative
 * 
 * @see AddressAlternative
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface AddressAlternativeService {

	/**
	 * Recherche de toutes les adresses alternatives
	 * 
	 * @return liste de toutes les adresses alternatives
	 */
	public List<AddressAlternative> getAllAddressAlternatives();

	/**
	 * Recherche d'une adresse alternative par son id
	 * 
	 * @param idAddressAlternative
	 * @return une adresse alternative
	 */
	public Optional<AddressAlternative> getAddressAlternativeById(Integer idAddressAlternative);

	/**
	 * Recherche des adresses alternatives d'un certificat
	 * 
	 * @param certificate
	 * @return liste des adresses alternatives d'un certificat
	 */
	public Iterable<AddressAlternative> getAddressAlternativeByCertificate(Certificate certificate);

	/**
	 * Création d'une adresse alternative
	 * 
	 * @param addressAlternative
	 * @return ajout d'une adresse alternative
	 */
	public AddressAlternative createAddressAlternative(AddressAlternative addressAlternative);

	/**
	 * Modification d'une adresse alternative
	 * 
	 * @param addressAlternative
	 * @return modification d'une adresse alternative
	 */
	public AddressAlternative updateAddressAlternative(AddressAlternative addressAlternative);

	/**
	 * Suppression d'une adresse alternative
	 * 
	 * @param idAddressAlternative
	 */
	public void deleteAddressAlternative(Integer idAddressAlternative);

}
