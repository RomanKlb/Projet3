package fr.isika.cdi07.projet3demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DonTempsRepository;
import fr.isika.cdi07.projet3demo.dao.ParticipationProjetRepository;
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;

@Service
public class DonTempsService implements IDonService<DonTemps>{

	@Autowired
	private DonTempsRepository donTempsRepo;
	
	@Autowired
	private ParticipationProjetRepository participationProjetRepo;
	
	@Override
	public List<DonTemps> afficherDons() {
		return donTempsRepo.findAll();
	}

	@Override
	public Long compterDons() {
		return donTempsRepo.count();
	}

	@Override
	public void enregistrerDansLaBase(DonTemps don) {
		ParticipationProjet participationProjet = new ParticipationProjet();
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.TEMPS)
							.withIsAnonyme(false)
							.withStatutDon(StatutDon.APPROUVE);
		participationProjetRepo.save(participationProjet);
		
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		donTempsRepo.save(don);		
	}

	@Override
	public DonTemps obtenirDonById(long id) {
		Optional<DonTemps> optional = donTempsRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("Don not found");
	}

	@Override
	public void supprimerDonById(long id) {
		donTempsRepo.deleteById(id);
		
	}

}