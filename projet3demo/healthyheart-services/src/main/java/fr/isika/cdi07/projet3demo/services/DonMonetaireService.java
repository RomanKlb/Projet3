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
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class DonMonetaireService implements IDonService<DonMonetaire>{
	
	@Autowired
	private DonMonetaireRepository donMonetaireRepo;
	
	@Autowired
	private ParticipationProjetRepository participationProjetRepo;
	
	@Autowired
	private ProjetRepository projetRepo;
	
	@Autowired
	private RoleService roleService;
	
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
	public StatutDon enregistrerDansLaBase(DonMonetaire don, ParticipationProjet participationProjet, Utilisateur user) {
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.MONETAIRE);//normalement dans le POST du controller
		checkAndSaveIfSeuilReached(don, participationProjet, user);
		return participationProjet.getStatutDon();
	}

	private void checkAndSaveIfSeuilReached(DonMonetaire don, ParticipationProjet participationProjet, Utilisateur user) {
		if(don.getMontant() > 2000) {
			participationProjet.withStatutDon(StatutDon.EN_ATTENTE);
			participationProjetRepo.save(participationProjet);
			
			saveDonInDB(don, participationProjet);
		}else {
			participationProjet.withStatutDon(StatutDon.APPROUVE)
								.withRole(addRoleDonateurToUser(user));
			participationProjetRepo.save(participationProjet);
			
			addMontantDansCollecteProjet(don, participationProjet);
			
			saveDonInDB(don, participationProjet);
		}
	}

	private void addMontantDansCollecteProjet(DonMonetaire don, ParticipationProjet participationProjet) {
		Optional<Projet> projetToDonate = projetRepo.findById(participationProjet.getProjet().getIdProjet());
		double newMontantCollecte = projetToDonate.get().getMontantCollecte() + don.getMontant();
		projetToDonate.get().setMontantCollecte(newMontantCollecte);
		projetRepo.save(projetToDonate.get());
	}

	private void saveDonInDB(DonMonetaire don, ParticipationProjet participationProjet) {
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		donMonetaireRepo.save(don);
	}
	
	private Role addRoleDonateurToUser(Utilisateur user) {
		return roleService.hasRole(user, TypeRole.DONATEUR);
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

	@Override
	public void modifierStatutDon(long idParticipation, StatutDon statutDon) {
		Optional<ParticipationProjet> optional = participationProjetRepo.findById(idParticipation);
		if(optional.isPresent()) {
			optional.get().setStatutDon(statutDon);	
			participationProjetRepo.save(optional.get());
			//TODO switch statutdon
			//si APPROUVE >>> ajout du role DONATEUR + ajout du montant dans projet
			//si REJETE >>> ne rien faire
		}
			
	}

	

	



}
