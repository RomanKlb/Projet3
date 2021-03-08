package fr.isika.cdi07.projet3demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DonMonetaireRepository;
import fr.isika.cdi07.projet3demo.dao.FactureRepository;
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
	
	@Autowired
	private FactureRepository factureRepo;
	
	//service ou repo?
	@Autowired
	private ProjetRepository projetRepo;
	
	//NB : surement mettre en type optional pour avoir possibilite de vue dans le controller
	public Optional<Projet> getProjet(long id) {
		return projetRepo.findById(id);
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
	public StatutDon enregistrerDansLaBase(DonMonetaire don, ParticipationProjet participationProjet) {
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.MONETAIRE);//normalement dans le POST du controller
		checkAndSaveIfSeuilReached(don, participationProjet);
		return participationProjet.getStatutDon();
	}

	private void checkAndSaveIfSeuilReached(DonMonetaire don, ParticipationProjet participationProjet) {
		if(don.getMontant() > 2000) {
			participationProjet.withStatutDon(StatutDon.EN_ATTENTE);
			participationProjetRepo.save(participationProjet);
			
			saveDonInDB(don, participationProjet);
		}else {
			participationProjet.withStatutDon(StatutDon.APPROUVE);							
			participationProjetRepo.save(participationProjet);
			
			//TODO : facture a generer
			
			Optional<Projet> projetToDonate = projetRepo.findById(participationProjet.getProjet().getIdProjet());
			double newMontantCollecte = projetToDonate.get().getMontantCollecte() + don.getMontant();
			projetToDonate.get().setMontantCollecte(newMontantCollecte);
			projetRepo.save(projetToDonate.get());
			
			saveDonInDB(don, participationProjet);
		}
	}

	private void saveDonInDB(DonMonetaire don, ParticipationProjet participationProjet) {
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
