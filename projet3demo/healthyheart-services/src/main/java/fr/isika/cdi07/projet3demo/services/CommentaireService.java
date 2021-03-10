package fr.isika.cdi07.projet3demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.isika.cdi07.projet3demo.dao.CommentaireRepository;
import fr.isika.cdi07.projet3demo.dao.ParticipationProjetRepository;
import fr.isika.cdi07.projet3demo.dao.RoleRepository;

import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.TypeRole;

@Service
public class CommentaireService implements ICommentaireService{

	@Autowired
	private CommentaireRepository commentaireRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private ParticipationProjetRepository participationRepo;
	
	@Override
	public Optional<Commentaire> getCommentaireById(long id) {
		return commentaireRepo.findById(id);	
	}

	@Override
	public Commentaire saveCommentaire(Commentaire commentaire) {
		commentaire.setDate(Date.valueOf(LocalDate.now()));
		//TODO setter avec le role
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

	@Override
	public boolean hasRoleToComment(Projet projet, String userEmail) {
		Optional<Role> roleFound = getRoleNonLambda(userEmail);
		if(!roleFound.isPresent())
			return false;

		Optional<ParticipationProjet> ppFound = participationRepo.findAll()
					.stream()
					.filter(pp -> pp.getProjet().equals(projet)
								&& pp.getRole().equals(roleFound.get()))
					.findFirst();
		if(!ppFound.isPresent())
			return false;
		return true;
	}

	@Override
	public Optional<Role> getRoleNonLambda(String userEmail) {
		return roleRepo.findAll()
					.stream()
					.filter(r -> r.getUtilisateur().getEmail().equalsIgnoreCase(userEmail)
								&& !r.getTypeRole().equals(TypeRole.LAMBDA))
					.findFirst();
	}

}
