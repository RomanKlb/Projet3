package fr.isika.cdi07.projet3demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.model.Projet;

@Service
public interface ICommentaireService {
	
	Optional<Commentaire> getCommentaireById(long id);
	Commentaire saveCommentaire(Commentaire commentaire);
	void deleteCommentaireById(long id);
	List<Commentaire> getCommentairesList(Projet projet);
}
