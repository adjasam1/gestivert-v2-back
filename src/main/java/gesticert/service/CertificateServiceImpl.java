package gesticert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gesticert.model.Certificate;
import gesticert.repository.CertificateRepository;

/**
 * Implémentation de CertificatService contenant les méthodes CRUD pour l'entité
 * Certificat
 * 
 * @see CertificateService
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Service
public class CertificateServiceImpl implements CertificateService {

	/**
	 * Injection des dépendances
	 * 
	 * @see CertificateRepository
	 */
	private CertificateRepository certificateRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Constructeur
	 * 
	 * @param certificateRepo
	 */
	public CertificateServiceImpl(CertificateRepository certificateRepo, BCryptPasswordEncoder passwordEncoder) {
		this.certificateRepo = certificateRepo;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Recherche de tous les certificats
	 */
	@Override
	public List<Certificate> getAllCertificates() {
		return certificateRepo.findAll(new Sort(Sort.Direction.ASC, "nameCertificate"));
	}

	/**
	 * Recherche d'un certificat par son id
	 */
	@Override
	public Optional<Certificate> getCertificateById(Integer idCertificate) {
		return certificateRepo.findById(idCertificate);
	}

	/**
	 * Recherche de tous les certificats d'un utilisateur
	 */
	@Override
	public List<Certificate> getCertificateByUser(String idRHUser) {
		return certificateRepo.findByIdRHUser(idRHUser);
	}

	/**
	 * Création d'un certificat
	 */
	@Override
	public Certificate createCertificate(Certificate certificate) {
		certificate = new Certificate(
				certificate.getNameCertificate(), 
				certificate.getLinkAddressPrincipal(),
				certificate.getLinkInstallation(), 
				passwordEncoder.encode(certificate.getPasswordCertificate()),
				certificate.getDateIssue(),
				certificate.getApplication(), 
				certificate.getEnvironment(), 
				certificate.getPlatform(),
				certificate.getRoot(),
				certificate.getServers());
		return certificateRepo.saveAndFlush(certificate);
	}

	/**
	 * Modification d'un certificat
	 */
	@Override
	public Certificate updateCertificate(Certificate certificate, Integer idCertificate) {
		return certificateRepo.saveAndFlush(certificate);
	}

	/**
	 * Suppression d'un certificat
	 */
	@Override
	public void deleteCertificate(Integer idCertificate) {
		certificateRepo.deleteById(idCertificate);
	}

}
