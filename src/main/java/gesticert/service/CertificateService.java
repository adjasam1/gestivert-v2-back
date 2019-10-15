package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Certificate;

/**
 * Service pour l'entité Certificat
 * 
 * @see Certificate
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface CertificateService {

	/**
	 * Recherche de tous les certificats
	 * 
	 * @return liste de tous les certificats
	 */
	public List<Certificate> getAllCertificates();

	/**
	 * Recherche d'un certificat par son id
	 * 
	 * @param idCertificate
	 * @return un certificat
	 */
	public Optional<Certificate> getCertificateById(Integer idCertificate);

	/**
	 * Recherche de tous les certificats d'un utilisateur
	 * 
	 * @param idRHUser
	 * @return liste de tous les certificats d'un utilisateur
	 */
	public List<Certificate> getCertificateByUser(String idRHUser);

	/**
	 * Création d'un certificat
	 * 
	 * @param certificate
	 * @return ajout d'un certificat
	 */
	public Certificate createCertificate(Certificate certificate);

	/**
	 * Modification d'un certificat
	 * 
	 * @param certificate
	 * @param idCertificate
	 * @return modification d'un certificat
	 */
	public Certificate updateCertificate(Certificate certificate, Integer idCertificate);

	/**
	 * Suppression d'un certificat
	 * 
	 * @param idCertificate
	 */
	public void deleteCertificate(Integer idCertificate);

}
