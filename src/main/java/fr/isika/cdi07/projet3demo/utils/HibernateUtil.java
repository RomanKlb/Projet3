package fr.isika.cdi07.projet3demo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("projet3demo");
	}
	
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public static void closeAll() {
		if(entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}
}
