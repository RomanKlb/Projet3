package fr.isika.cdi07.projet3demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.projet3demo.model.ParticipationProjet;

public interface ParticipationProjetRepository extends JpaRepository<ParticipationProjet, Long>{
	
	
}
