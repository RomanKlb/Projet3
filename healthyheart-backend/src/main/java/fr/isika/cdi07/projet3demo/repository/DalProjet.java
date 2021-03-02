package fr.isika.cdi07.projet3demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.isika.cdi07.projet3demo.model.Appreciation;
import fr.isika.cdi07.projet3demo.model.Categorie;
import fr.isika.cdi07.projet3demo.model.Historique;
import fr.isika.cdi07.projet3demo.model.MessageInterne;
import fr.isika.cdi07.projet3demo.model.MessageRecu;
import fr.isika.cdi07.projet3demo.model.Notification;
import fr.isika.cdi07.projet3demo.model.PortefeuilleProjet;
import fr.isika.cdi07.projet3demo.model.PorteurProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.Territoire;
import fr.isika.cdi07.projet3demo.model.TypeProjet;
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


		public void persisterPorteurProjet(PorteurProjet porteurprojet) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(porteurprojet);

			this.entityManager.flush();
			transaction.commit();
		}


		public void persisterPortefeuilleProjet(PortefeuilleProjet portefeuilleprojet) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(portefeuilleprojet);

			this.entityManager.flush();
			transaction.commit();
			
		}


		public void persisterTypeProjet(TypeProjet typeprojet) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(typeprojet);

			this.entityManager.flush();
			transaction.commit();
			
		}


		public void persisterTerritoire(Territoire territoire) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(territoire);

			this.entityManager.flush();
			transaction.commit();
		}


		public void persisterCategorie(Categorie categorie) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(categorie);

			this.entityManager.flush();
			transaction.commit();
		}



		public void persisterMessageInterne(MessageInterne message) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(message);

			this.entityManager.flush();
			transaction.commit();
			
		}


		public void persisterMessageRecu(MessageRecu messageRecu) {
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.persist(messageRecu);

			this.entityManager.flush();
			transaction.commit();
		}
		
	
}