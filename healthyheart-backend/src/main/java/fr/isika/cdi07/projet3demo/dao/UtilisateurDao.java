package fr.isika.cdi07.projet3demo.dao;

import java.util.Optional;

import javax.persistence.EntityManager;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.utils.HibernateUtil;

public class UtilisateurDao {

	private EntityManager entityManager;
	
	public UtilisateurDao() {
		this.entityManager = HibernateUtil.createEntityManager();
	}

	public Optional<Utilisateur> findUserByEmail(String email) {

		return Optional.of(this.entityManager.find(Utilisateur.class, email));
	}
	
	
}
