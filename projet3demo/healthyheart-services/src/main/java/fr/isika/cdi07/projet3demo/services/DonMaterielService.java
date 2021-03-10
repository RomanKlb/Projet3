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
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class DonMaterielService implements  IDonService<DonMateriel>{

	@Autowired
	private DonMaterielRepository donMaterielRepo;
	
	@Autowired
	private ParticipationProjetRepository participationProjetRepo;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public List<DonMateriel> afficherDons() {
		return donMaterielRepo.findAll();
	}

	@Override
	public Long compterDons() {
		return donMaterielRepo.count();
	}

	@Override
	public StatutDon enregistrerDansLaBase(DonMateriel don, ParticipationProjet participationProjet, Utilisateur user) {
		participationProjet.withDate(Date.valueOf(LocalDate.now()))
							.withTypeParticipation(TypeParticipation.MATERIEL);
		checkAndSaveIfSeuilReached(don, participationProjet, user);
		return participationProjet.getStatutDon();
	}
	
	private void checkAndSaveIfSeuilReached(DonMateriel don, ParticipationProjet participationProjet, Utilisateur user) {
		double totalMateriel = don.getMontant() * don.getQuantite();
		if(totalMateriel > 10000) {
			participationProjet.withStatutDon(StatutDon.EN_ATTENTE);
			participationProjetRepo.save(participationProjet);
			
			saveDonInDB(don, participationProjet);
		}else {
			participationProjet.withStatutDon(StatutDon.APPROUVE)
								.withRole(addRoleDonateurToUser(user));
			participationProjetRepo.save(participationProjet);			
			saveDonInDB(don, participationProjet);
		}
	}

	private void saveDonInDB(DonMateriel don, ParticipationProjet participationProjet) {
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		donMaterielRepo.save(don);
	}
	
	private Role addRoleDonateurToUser(Utilisateur user) {
		return roleService.hasRole(user, TypeRole.DONATEUR);
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

	@Override
	public void modifierStatutDon(long idParticipation, StatutDon statutDon) {
		Optional<ParticipationProjet> optional = participationProjetRepo.findById(idParticipation);
		if(optional.isPresent()) {
			optional.get().setStatutDon(statutDon);	
			participationProjetRepo.save(optional.get());
		}
		
	}

}
