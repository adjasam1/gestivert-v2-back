package gesticert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gesticert.repository.UserRepository;

/**
 * Service UtilisateurDétail pour l'authentification
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	/**
	 * Injection des dépendances
	 */
	@Autowired
	private UserRepository userRepo;

	/**
	 * Recherche d'un utilisateur par son idRH
	 */
	@Override
	public UserDetails loadUserByUsername(String idRHUser) throws UsernameNotFoundException {
		final Optional<gesticert.model.User> user = userRepo.findByIdRHUser(idRHUser);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User '" + idRHUser + "' not found");
		}

		return User.withUsername(idRHUser).password(user.get().getPasswordUser()).authorities(user.get().getRoleList())
				.accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
	}

}
