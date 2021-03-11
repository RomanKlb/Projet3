package fr.isika.cdi07.projet3demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.ParticipationProjetRepository;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class ParticipationProjetService implements IParticipationProjetService {

	@Autowired
	private ParticipationProjetRepository participationRepo;
	
	@Override
	public ParticipationProjet addParticipation(ParticipationProjet participationProjet) {
		return participationRepo.save(participationProjet);
	}
	
	@Override
	public Optional<ParticipationProjet> getParticipationById(long id) {
		return participationRepo.findById(id);
	}
	
	@Override
	public Optional<ParticipationProjet> getParticipationByProjetAndUser(Projet projet, Utilisateur user) {
		return getAllParticipationsByProjet(projet, StatutDon.APPROUVE)
				.stream()
				.filter(pp -> pp.getRole().getTypeRole().equals(TypeRole.DONATEUR) 
						&& pp.getRole().getUtilisateur().equals(user))
				.findFirst();
	}
	
	@Override
	public int countParticipationsByProjet(Projet projet) {
		return getAllParticipationsByProjet(projet, StatutDon.APPROUVE).size();
	}

	@Override
	public List<ParticipationProjet> getAllParticipationsByProjet(Projet projet, StatutDon statutDon) {
		return participationRepo.findAllByProjetAndStatutDon(projet, statutDon);
	}

	@Override
	public List<ParticipationProjet> getAllApprovedParticipationFromUser(Utilisateur user) {
		return participationRepo.findAll().stream()
				.filter(pp -> pp.getRole().getUtilisateur().equals(user))
				.filter(pp -> pp.getStatutDon().equals(StatutDon.APPROUVE))
				.collect(Collectors.toList());
	}

	@Override
	public void updateStatutDon(ParticipationProjet participationProjet, StatutDon statutDon) {
		participationProjet.setStatutDon(statutDon);
		participationRepo.save(participationProjet);		
	}

	@Override
	public boolean checkIfAnonyme(Projet projet, Utilisateur user) {
		Optional<ParticipationProjet> optParticipation = getAllApprovedParticipationFromUser(user)
				.stream()
				.filter(pp -> pp.isAnonyme() && pp.getProjet().equals(projet))
				.findFirst();
		return optParticipation.isPresent();
	}

	

	

	
	

}
