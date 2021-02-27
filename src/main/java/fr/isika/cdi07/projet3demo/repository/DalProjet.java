package fr.isika.cdi07.projet3demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.isika.cdi07.projet3demo.model.Appreciation;
import fr.isika.cdi07.projet3demo.model.Historique;
import fr.isika.cdi07.projet3demo.model.Notification;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.utils.HibernateUtil;

public class DalProjet {

	private EntityManager entityManager;

	public DalProjet() {
		this.entityManager = HibernateUtil.createEntityManager();
	}

	
//	private <T> void persist(T object) {
//		EntityTransaction transaction = this.entityManager.getTransaction();
//		transaction.begin();
//		this.entityManager.persist(object);
//
//		//projet.getModules().stream().forEach(module -> entityManager.persist(module));
//
//		this.entityManager.flush();
//		transaction.commit();
//	}
	
	
	/*
	 * Create
	 */
	

	public void persister(Projet projet) {
		
//		persist(projet);
		
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(projet);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	}
	
	public void persisterHisto(Historique historique) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(historique);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	
	}
	
	public void persisterAppreciation(Appreciation appreciation) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(appreciation);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	
	}
	
	public void persisterRole(Role role) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(role);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	
	}
	public void persisterNotif(Notification notif) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(notif);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	}
	
	public void persisterUtilisateur(Utilisateur utilisateur) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(utilisateur);

		//projet.getModules().stream().forEach(module -> entityManager.persist(module));

		this.entityManager.flush();
		transaction.commit();
	}
	
	
	/*
	 * Find
	 */
	
//	public Optional<Projet> rechercheProjetParId(Long idProjet) {
//		return Optional.ofNullable(entityManager.find(Projet.class, idProjet));
//	}
	
	public Projet findByIdProjet (Long idProjet) {
		return this.entityManager
				.createNamedQuery("Projet.findById", Projet.class)
				.setParameter("idProjetParam", idProjet)
				.getSingleResult();
	}
	
	
	public List<Projet> findAllProject() {
		
		return this.entityManager
				.createNamedQuery("Projet.findAll", Projet.class)
				.getResultList();
	}
	
	
	/*
	 * Update
	 */
	
		public Projet modifierProjet(Projet projet) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			Projet mergedProjet = entityManager.merge(projet);
			transaction.commit();
			return mergedProjet;
		}
		
	
}