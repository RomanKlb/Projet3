package fr.isika.cdi07.projet3demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.utils.HibernateUtil;

public class DaoUtilisateur {

	private EntityManager entityManager;

	public DaoUtilisateur() {
	
		this.entityManager = HibernateUtil.createEntityManager();
	}


	public void persisterUtilisateur(Utilisateur utilisateur) {

		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(utilisateur);

		this.entityManager.flush();
		transaction.commit();
	}


	public Utilisateur rechercherUtilisateurParLoginMdp(String email, String mdp) {

		Query query = entityManager.createQuery("SELECT u FROM Utilisateur u"
				+ " WHERE u.email = :paramEmail "
				+ "AND u.mdp = :paramMdp");
		query.setParameter("paramEmail", email);
		query.setParameter("paramMdp", mdp);
		
		List<Utilisateur> utilisateurs = query.getResultList();
		
		Utilisateur utilisateur = null;
		
		if(utilisateurs.size() == 1)
		{
			utilisateur = utilisateurs.get(0);
		}
		
		return utilisateur;
	}

}
