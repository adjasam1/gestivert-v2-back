package gesticert.service;

import java.util.List;
import java.util.Optional;

import gesticert.model.Root;

/**
 * Service pour l'entité Racine
 * 
 * @see Root
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
public interface RootService {

	/**
	 * Recherche de toutes les racines
	 * 
	 * @return liste de toutes les racines
	 */
	public List<Root> getAllRoots();

	/**
	 * Recherche d'une racine par son id
	 * 
	 * @param idRoot
	 * @return une racine
	 */
	public Optional<Root> getRootById(Integer idRoot);

	/**
	 * Création d'une racine
	 * 
	 * @param root
	 * @return ajout d'une racine
	 */
	public Root createRoot(Root root);

	/**
	 * Modification d'une racine
	 * 
	 * @param root
	 * @param idRoot
	 * @return modification d'une racine
	 */
	public Root updateRoot(Root root, Integer idRoot);

	/**
	 * Suppression d'une racine
	 * 
	 * @param idRoot
	 */
	public void deleteRoot(Integer idRoot);

}
