package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gesticert.exception.ExistingIdRHUserException;
import gesticert.exception.InvalidCredentialsException;
import gesticert.model.Department;
import gesticert.model.Profile;
import gesticert.model.User;
import gesticert.repository.UserRepository;
import gesticert.security.JwtTokenProvider;

/**
 * Implémentation de UtilisateurService contenant les méthodes CRUD pour
 * l'entité Utilisateur
 * 
 * @see UserService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * Injections des dépendances
	 */

	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Constructeur
	 * 
	 * @param userRepo
	 * @param passwordEncoder
	 * @param jwtTokenProvider
	 * @param authenticationManager
	 */
	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Recherche d'un utilisateur (authentification)
	 */
	@Override
	public String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idRHUser, passwordUser));
			return jwtTokenProvider.createToken(idRHUser, userRepo.findByIdRHUser(idRHUser).get().getRoleList());
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException();
		}
	}

	/**
	 * Création d'un utilisateur (authentification)
	 */
	@Override
	public String signup(User user) throws ExistingIdRHUserException {
		if (!userRepo.existsByIdRHUser(user.getIdRHUser())) {
			User userToSave = new User(user.getIdRHUser(), passwordEncoder.encode(user.getPasswordUser()),
					user.getNameUser(), user.getFirstNameUser(), user.geteMailUser(), user.getPhoneUser(),
					user.getDepartment(), user.getProfile(), user.getRoleList());
			userRepo.saveAndFlush(userToSave);
			return jwtTokenProvider.createToken(user.getIdRHUser(), user.getRoleList());
		} else {
			throw new ExistingIdRHUserException();
		}
	}

	/**
	 * Recherche de tous les utilisateurs
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll(new Sort(Sort.Direction.ASC, "idRHUser"));
	}

	/**
	 * Recherche d'un utilisateur par son idRH
	 */
	@Override
	public Optional<User> getUserByIdRH(String idRHUser) {
		return userRepo.findByIdRHUser(idRHUser);
	}

	/**
	 * Recherche de tous les utilisateurs ayant un profil
	 */
	@Override
	public Iterable<User> getUserByProfile(Profile profile) {
		return userRepo.findByProfile(profile, new Sort(Sort.Direction.ASC, "idRHUser"));
	}

	/**
	 * Modification d'un profil des utilisateurs
	 */
	@Override
	public User removeProfile(Profile profile) {
		return userRepo.removeProfileUser(profile);
	}

	/**
	 * Modification d'un téléphone des utilisateurs
	 */
	@Override
	public User updatePhoneUser(String nameUser) {
		return userRepo.updatePhoneUser(nameUser);
	}

	/**
	 * Recherche de tous les utilisateurs d'un service
	 */
	@Override
	public Iterable<User> getUserByDepartment(Department department) {
		return userRepo.findByDepartment(department, new Sort(Sort.Direction.ASC, "idRHUser"));
	}

	/**
	 * Création d'un utilisateur
	 */
	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	/**
	 * Modification d'un utilisateur
	 */
	@Override
	public User updateUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	/**
	 * Suppression d'un utilisateur
	 */
	@Override
	public void deleteUser(Integer idUser) {
		userRepo.deleteById(idUser);
	}

}
