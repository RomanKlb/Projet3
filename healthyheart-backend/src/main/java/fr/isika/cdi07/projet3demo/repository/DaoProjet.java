package fr.isika.cdi07.projet3demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

<<<<<<< HEAD
import org.hibernate.resource.transaction.backend.jta.internal.StatusTranslator;
import org.hibernate.sql.Select;

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutProjet;
=======
import org.hibernate.sql.Select;

import fr.isika.cdi07.projet3demo.model.Projet;
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce
import fr.isika.cdi07.projet3demo.utils.HibernateUtil;

public class DaoProjet {


	private EntityManager entityManager;

	public DaoProjet() {
		this.entityManager = HibernateUtil.createEntityManager();
	}


<<<<<<< HEAD
	public void persisterProjet(Projet projet) {
=======
	public void persister(Projet projet) {
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce

		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(projet);

		this.entityManager.flush();
		transaction.commit();
	}


	public List<Projet> rechercheProjetParTitre(String titre) {

		List<Projet> projets = new ArrayList<Projet>();

		Query query = entityManager.createQuery("SELECT p FROM Projet p WHERE p.titre like :paramTitre",Projet.class);
		query.setParameter("paramTitre", "%"+titre+"%");

		projets = query.getResultList();

		return projets;
	}

<<<<<<< HEAD
	public List<Projet> obtenirTousLesProjets(){
		return this.entityManager
				.createNamedQuery("Projet.findAll", Projet.class)
				.getResultList();
	}

	public List<Projet> obtenirTousLesProjetsParStatutPublie() {
		List<Projet> projets = new ArrayList<Projet>();
		List<Projet> projetRecup = new ArrayList<Projet>();
		projets = obtenirTousLesProjets();

		for(Projet proj : projets) {
			if(proj.getStatutDuProjet().equals(StatutProjet.PUBLIE)) {
				
				projetRecup.add(proj);
			}
		}

		return projetRecup;
	}


=======
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce
	public Projet rechercheProjetParId(Long id) {
		return entityManager.find(Projet.class, id);

	}


	public void modifierProjet(Projet projet) {
		if(projet != null) {
			rechercheProjetParId(projet.getIdProjet());
			EntityTransaction transaction = this.entityManager.getTransaction();
			transaction.begin();
			this.entityManager.merge(projet);
			transaction.commit();
		}		

	}


}
