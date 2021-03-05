package fr.isika.cdi07.projet3demo.services;

import java.util.List;

import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.model.Projet;

public interface ICommentaireService {
	
	Commentaire getCommentaireById(long id);
	void saveCommentaire(Commentaire commentaire);
	void deleteCommentaireById(long id);
	List<Commentaire> getCommentairesList(Projet projet);
}
