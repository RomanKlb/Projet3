package fr.isika.cdi07.projet3demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.cdi07.projet3demo.model.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long>{

	List<Projet> findProjectsByTitre(String titre);


	
}
