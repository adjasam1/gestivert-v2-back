package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.exception.ExistingIdRHUserException;
import gesticert.exception.InvalidCredentialsException;
import gesticert.model.Department;
import gesticert.model.Profile;
import gesticert.model.User;

/**
 * Service pour l'entité Utilisateur
 * 
 * @see User
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface UserService {

	/**
	 * Recherche d'un utilisateur (authentification)
	 * 
	 * @param idRHUser
	 * @param passwordUser
	 * @return création d'un jeton JWT
	 * @throws InvalidCredentialsException
	 */
	public String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException;

	/**
	 * Création d'un utilisateur (authentification)
	 * 
	 * @param user
	 * @return ajout d'un utilisateur et création d'un jeton JWT
	 * @throws ExistingIdRHUserException
	 */
	public String signup(User user) throws ExistingIdRHUserException;

	/**
	 * Recherche de tous les utilisateurs
	 * 
	 * @return liste de tous les utilisateurs
	 */
	public List<User> getAllUsers();

	/**
	 * Recherche d'un utilisateur par son idRH
	 * 
	 * @param idRHUser
	 * @return un utilisateur
	 */
	public Optional<User> getUserByIdRH(String idRHUser);

	/**
	 * Recherche de tous les utilisateurs ayant le même profil
	 * 
	 * @param profile
	 * @return liste des utilisateurs ayant le profil
	 */
	public Iterable<User> getUserByProfile(Profile profile);

	/**
	 * Modification d'un profil des utilisateurs
	 * 
	 * @param profile
	 * @return modification d'un profil des utilisateurs
	 */
	public User removeProfile(Profile profile);

	/**
	 * Recherche de tous les utilisateurs d'un service
	 * 
	 * @param department
	 * @return liste des utilisateurs d'un service
	 */
	public Iterable<User> getUserByDepartment(Department department);

	/**
	 * Création d'un utilisateur
	 * 
	 * @param user
	 * @return Ajout d'un utilisateur
	 */
	public User createUser(User user);

	/**
	 * Modification d'un utilisateur
	 * 
	 * @param user
	 * @return Modification d'un utilisateur
	 */
	public User updateUser(User user);

	/**
	 * Suppression d'un utilisateur
	 * 
	 * @param idUser
	 */
	public void deleteUser(Integer idUser);

	public User updatePhoneUser(String nameUser);

}
