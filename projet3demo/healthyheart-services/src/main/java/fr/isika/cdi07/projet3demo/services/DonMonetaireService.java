package fr.isika.cdi07.projet3demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DonMonetaireRepository;
import fr.isika.cdi07.projet3demo.dao.ParticipationProjetRepository;
import fr.isika.cdi07.projet3demo.dao.ProjetRepository;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;

@Service
public class DonMonetaireService implements IDonService<DonMonetaire>{
	
	@Autowired
	private DonMonetaireRepository donMonetaireRepo;
	
	@Autowired
	private ParticipationProjetRepository participationProjetRepo;
	
	//a enlever
	@Autowired
	private ProjetRepository projetRepo;
	
	public Projet getProjet(long id) {
		Optional<Projet> optional = projetRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("Projet not found");
	}

	@Override
	public List<DonMonetaire> afficherDons() {
		return donMonetaireRepo.findAll();
	}

	@Override
	public Long compterDons() {
		return donMonetaireRepo.count();
	}
	
	@Override
	public void enregistrerDansLaBase(DonMonetaire don, ParticipationProjet participationProjet) {
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.MONETAIRE)
							.withIsAnonyme(false)
							.withStatutDon(StatutDon.APPROUVE);
		participationProjetRepo.save(participationProjet);
		
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		donMonetaireRepo.save(don);
	}

	@Override
	public DonMonetaire obtenirDonById(long id) {
		Optional<DonMonetaire> optional = donMonetaireRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("Don not found");
	}

	@Override
	public void supprimerDonById(long id) {
		donMonetaireRepo.deleteById(id);
	}

	

	



}
