package fr.isika.cdi07.projet3demo.dao;

import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.projet3demo.model.Commentaire;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{

}
