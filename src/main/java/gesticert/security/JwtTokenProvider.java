package gesticert.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import gesticert.exception.InvalidJWTException;
import gesticert.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe utilitaire qui est chargée de fournir le jeton JWT, de le vérifier,
 * etc
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	/**
	 * Durée de validité du jeton avant de devoir se reconnecter : 1h
	 */
	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000;

	@Autowired
	private UserDetailsService userDetailsServ;

	/**
	 * Codage de la clé secrète au même format (Base64) que l'en-tête et la charge
	 * utile afin de permettre la création de la signature HMAC
	 * (header+payload+secretKey)
	 */
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	/**
	 * Création du jeton JWT avec l'idRH de l'utilisateur (sub), les rôles de
	 * l'utilisateur (auth), la date actuelle (iat) et la date actuelle + la durée
	 * de validité (exp)
	 * 
	 * @param idRHUser
	 * @param roles
	 * @return une chaîne de caractère (jeton JWT créé)
	 */
	public String createToken(String idRHUser, List<Role> roles) {

		Claims claims = Jwts.claims().setSubject(idRHUser);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	/**
	 * Renvoie de l'authentification utilisateur basée sur un jeton JWT
	 * 
	 * @param token
	 * @return l'objet d'authentification si l'utilisateur est trouvé
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsServ.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/**
	 * Récupération de l'utilisateur du jeton JWT
	 * 
	 * @param token
	 * @return l'idRH de l'utilisateur sous forme d'une châine de caractère
	 */
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Résolution du jeton JWT à partir d'une requête HTTP L'en-tête doit contenir
	 * un champ d'autorisation où le jeton JWT doit être ajouté après "Bearer "
	 * 
	 * @param req
	 * @return le jeton JWT à partir de l'en-tête HTTP
	 */
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	/**
	 * Vérification de la validité du jeton JWT La signature doit être correcte et
	 * exp doit être supérieur à iat (cf. méthode createToken)
	 * 
	 * @param token
	 * @return vrai si le jeton JWT est valide, lance l'exception si le jeton JWT
	 *         est non valide
	 * @throws InvalidJWTException
	 */
	public boolean validateToken(String token) throws InvalidJWTException {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidJWTException();
		}
	}

}
