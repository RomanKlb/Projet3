package fr.isika.cdi07.projet3demo.repository;

import javax.persistence.EntityTransaction;

// I : type de l'id
// T : objet lui même
//public abstract class AbstractDal<I, T> {
//
//	public abstract I persist(T objet);
//	
//	public void save(T objet) {
//		EntityTransaction transaction = this.entityManager.getTransaction();
//		transaction.begin();
//		
//		persist(objet);
//		
//		this.entityManager.flush();
//		transaction.commit();
//	}
//	
//}


//class TestDal extends AbstractDal<Long, Projet> {
//
//	@Override
//	public Long persist(Projet objet) {
//		EntityTransaction transaction = this.entityManager.getTransaction();
//		transaction.begin();
//		this.entityManager.persist(object);
//
//		//projet.getModules().stream().forEach(module -> entityManager.persist(module));
//
//		this.entityManager.flush();
//		transaction.commit();
//	}
//	
//}