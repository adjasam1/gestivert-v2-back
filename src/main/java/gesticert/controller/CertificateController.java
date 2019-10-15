package gesticert.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gesticert.model.Certificate;
import gesticert.service.CertificateService;
import gesticert.service.MailService;

/**
 * Controlleur en charge de gérer les requêtes HTTP sur l'entité Certificat
 * 
 * @see CertificateService
 * @see MailService
 * 
 * @author Samuel Sabot
 * @version 2.0
 */
@RestController
@RequestMapping("/api/certificat")
public class CertificateController {

	/**
	 * Injection des dependances
	 */

	private CertificateService certificateServ;

	private MailService mailServ;

	/**
	 * Constructeur
	 * 
	 * @param certificateServ
	 * @param mailServ
	 */
	public CertificateController(CertificateService certificateServ, MailService mailServ) {
		this.certificateServ = certificateServ;
		this.mailServ = mailServ;
	}

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT,
	 * DELETE) avec url
	 * 
	 * @param idCertificate
	 * @param word
	 * @param certificate
	 * @return résultat de la requête (200) ou Requête erronée (400) ou Ressource
	 *         non trouvé (404) ou Erreur interne du serveur (500)
	 */

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllCertificates() {
		List<Certificate> listCertificates = null;

		try {
			listCertificates = certificateServ.getAllCertificates();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listCertificates == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}

	@GetMapping("/id={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateById(@PathVariable Integer idCertificate) {
		Optional<Certificate> listCertificates = null;

		try {
			listCertificates = certificateServ.getCertificateById(idCertificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listCertificates == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}

	@GetMapping("/utilisateur={idRHUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByUser(@PathVariable String idRHUser) {
		Iterable<Certificate> certificate = null;
		try {
			certificate = certificateServ.getCertificateByUser(idRHUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(certificate);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postCertificate(@RequestBody Certificate certificate) {
		Certificate newCertificate = null;

		String nameCertificate = certificate.getNameCertificate();
		if ((nameCertificate == null) || (nameCertificate.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le nom du certificat, même provisoire, doit être renseigné");
		}

		try {
			newCertificate = certificateServ.createCertificate(certificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
	}

	@PutMapping("/modification={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putCertificate(@RequestBody Certificate certificate, @PathVariable Integer idCertificate) {
		Certificate modifyCertificate = null;
		getCertificateById(idCertificate);

		String nameCertificate = certificate.getNameCertificate();
		if ((nameCertificate == null) || (nameCertificate.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le nom du certificat, même provisoire, doit être renseigné");
		}

		try {
			modifyCertificate = certificateServ.updateCertificate(certificate, idCertificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyCertificate);
	}

	@DeleteMapping("/suppression={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCertificate(@PathVariable Integer idCertificate) {
		try {
			certificateServ.deleteCertificate(idCertificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

	@ResponseBody
	@RequestMapping("/mail")
	//@PostMapping("/mail")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public Object sendHtmlEmail(@RequestBody Certificate certificate) throws MessagingException {
		return mailServ.sendHtmlEmail(certificate);
	}

}
