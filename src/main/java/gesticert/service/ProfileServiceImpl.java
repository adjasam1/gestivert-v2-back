package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gesticert.model.Profile;
import gesticert.repository.ProfileRepository;

/**
 * Implémentation de ProfilService contenant les méthodes CRUD pour l'entité
 * Profil
 * 
 * @see ProfileService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	/**
	 * Injection des dépendances
	 * 
	 * @see ProfileRepository
	 */
	private ProfileRepository profileRepo;

	/**
	 * Constructeur
	 * 
	 * @param profileRepo
	 */
	public ProfileServiceImpl(ProfileRepository profileRepo) {
		this.profileRepo = profileRepo;
	}

	/**
	 * Recherche de tous les profils
	 */
	@Override
	public List<Profile> getAllProfiles() {
		return profileRepo.findAll();
	}

	/**
	 * Recherche d'un profil par son id
	 */
	@Override
	public Optional<Profile> getProfileById(Integer idProfile) {
		return profileRepo.findById(idProfile);
	}

	/**
	 * Création d'un profil
	 */
	@Override
	public Profile createProfile(Profile profile) {
		return profileRepo.saveAndFlush(profile);
	}

	/**
	 * Modification d'un profil
	 */
	@Override
	public Profile updateProfile(Profile profile, Integer idProfile) {
		return profileRepo.saveAndFlush(profile);
	}

	/**
	 * Suppression d'un profil
	 */
	@Override
	public void deleteProfile(Integer idProfile) {
		profileRepo.deleteById(idProfile);
	}

}
