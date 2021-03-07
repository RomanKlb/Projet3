package fr.isika.cdi07.projet3demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.isika.cdi07.projet3demo.dao.CommentaireRepository;

import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.model.Projet;

@Service
public class CommentaireService implements ICommentaireService{

	@Autowired
	private CommentaireRepository commentaireRepo;
	
	@Override
	public Optional<Commentaire> getCommentaireById(long id) {
		return commentaireRepo.findById(id);	
	}

	@Override
	public Commentaire saveCommentaire(Commentaire commentaire) {
		return commentaireRepo.save(commentaire);
	}

	@Override
	public void deleteCommentaireById(long id) {
		commentaireRepo.deleteById(id);
		
	}

	@Override
	public List<Commentaire> getCommentairesList(Projet projet) {
		return commentaireRepo.findAll().stream()
										.filter(c -> c.getProjet().equals(projet))
										.collect(Collectors.toList());
	}

}
