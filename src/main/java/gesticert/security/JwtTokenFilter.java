package gesticert.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import gesticert.exception.InvalidJWTException;

/**
 * Filtre spécifique vérifiant que chaque requête HTTP arrivant sur le serveur
 * contient un jeton JWT valide
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		// Vérification de l'habilitation
		String token = jwtTokenProvider.resolveToken(httpServletRequest);
		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				// Obtention de l'authentification
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				// Injection dans le contexte de sécurité
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (InvalidJWTException ex) {
			// Très important : garantit que l'utilisateur n'est pas authentifié
			SecurityContextHolder.clearContext();
			httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT provided");
			return;
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
