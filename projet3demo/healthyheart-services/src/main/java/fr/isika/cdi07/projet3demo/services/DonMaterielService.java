package fr.isika.cdi07.projet3demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DonMaterielRepository;
import fr.isika.cdi07.projet3demo.dao.ParticipationProjetRepository;
import fr.isika.cdi07.projet3demo.model.DonMateriel;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;

@Service
public class DonMaterielService implements  IDonService<DonMateriel>{

	@Autowired
	private DonMaterielRepository donMaterielRepo;
	
	@Autowired
	private ParticipationProjetRepository participationProjetRepo;
	@Override
	public List<DonMateriel> afficherDons() {
		return donMaterielRepo.findAll();
	}

	@Override
	public Long compterDons() {
		return donMaterielRepo.count();
	}

	@Override
	public void enregistrerDansLaBase(DonMateriel don, ParticipationProjet participationProjet) {
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.MATERIEL)
							.withIsAnonyme(false)
							.withStatutDon(StatutDon.APPROUVE);
		participationProjetRepo.save(participationProjet);
		
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		donMaterielRepo.save(don);
		
	}

	@Override
	public DonMateriel obtenirDonById(long id) {
		Optional<DonMateriel> optional = donMaterielRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("Don not found");
	}

	@Override
	public void supprimerDonById(long id) {
		donMaterielRepo.deleteById(id);
		
	}

}
