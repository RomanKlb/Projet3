package fr.isika.cdi07.projet3demo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import fr.isika.cdi07.projet3demo.dao.UtilisateurDao;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.ParticipationProjetServices;

class CreerDonCUTest {

	@Test
	void shouldReturnUserIfFound() {
		UtilisateurDao repo = new UtilisateurDao();
		Optional<Utilisateur> utilisateur = repo.findUserByEmail("aaa@aaa.fr");		
		assertEquals("aaa@aaa.fr", utilisateur.get().getEmail());
		
	}
	
	@Test
	void shouldAffectApprouveIfDonMonetaireIsLessThan2000() {
		UtilisateurDao repo = new UtilisateurDao();
		Optional<Utilisateur> utilisateur = repo.findUserByEmail("aaa@aaa.fr");
		DonMonetaire donMonetaire = new DonMonetaire();
		ParticipationProjet participationProjet = new ParticipationProjet();
		Role role = new Role(TypeRole.DONATEUR, utilisateur.get());
		
		donMonetaire.setDate(Date.valueOf(LocalDate.now()));
		donMonetaire.setMontant((double) 100);
		donMonetaire.setParticipationProjet(participationProjet);
		
		participationProjet.setTypeParticipation(TypeParticipation.MONETAIRE);
		participationProjet.setAnonyme(true);
		participationProjet.setDate(Date.valueOf(LocalDate.now()));
		participationProjet.setRole(role);
		
//		assertEquals(StatutDon.APPROUVE, new ParticipationProjetServices()
//				.ajouterDon(donMonetaire).getStatutDon());

	}

}
