package fr.isika.cdi07.projet3demo.dao;

import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.projet3demo.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, String>{

}
