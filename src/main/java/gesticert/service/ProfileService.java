package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Profile;

/**
 * Service pour l'entité Profil
 * 
 * @see Profile
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface ProfileService {

	/**
	 * Recherche de tous les profils
	 * 
	 * @return liste de tous les profils
	 */
	public List<Profile> getAllProfiles();

	/**
	 * Recherche d'un profil par son id
	 * 
	 * @param idProfile
	 * @return un profil
	 */
	public Optional<Profile> getProfileById(Integer idProfile);

	/**
	 * Création d'un profil
	 * 
	 * @param profile
	 * @return ajout du profil
	 */
	public Profile createProfile(Profile profile);

	/**
	 * Modification d'un profil
	 * 
	 * @param profile
	 * @param idProfile
	 * @return modification d'un profil
	 */
	public Profile updateProfile(Profile profile, Integer idProfile);

	/**
	 * Suppression d'un profil
	 * 
	 * @param idProfile
	 */
	void deleteProfile(Integer idProfile);

}
