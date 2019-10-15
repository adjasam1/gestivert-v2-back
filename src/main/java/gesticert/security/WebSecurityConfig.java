package gesticert.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuration globale de la sécurité pour l'API REST basée sur HTTP
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// Utilisé dans la méthode signin (UserServiceImpl)
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Configuration sécurité HTTP
	 * 
	 * @param http (objet HttpSecurity à configurer)
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors();

		// Blocage attaque CSRF (Cross Site Request Forgery) qui pourrait prendre des
		// infos sensibles
		// présent dans le coockie comme le jeton est stocké dans le stockage de sesion
		// (session storage)
		http.csrf().disable();

		// Aucune session n'est créée ou utilisée par Spring Security
		// Stateless permet de ne pas garder la session en memoire comme le jeton est
		// renvoyé à chaque connexion et requêtes
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Points d'entrée
		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/api/utilisateur/sign-in").permitAll()
				.antMatchers("/api/utilisateur/sign-up").permitAll().anyRequest().authenticated();

		// Application de JWT
		http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * Configuration de la sécurité web Utile à des fins de développement pour, par
	 * exemple, autoriser l'accès à la console h2
	 * 
	 * @param web (objet WebSecurity à configurer)
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		// .anyRequest();
	}

	/**
	 * Configuration générique pour le CORS (Cross-Origin Resource Sharing) Utile
	 * pour le développement du Front-end avec Angular
	 * 
	 * @return l'objet CorsConfigurationSource
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));

		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));

		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

}
