package fr.isika.cdi07.projet3demo.main;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fr.isika.cdi07.projet3demo.model.Appreciation;
import fr.isika.cdi07.projet3demo.model.Historique;
import fr.isika.cdi07.projet3demo.model.Notification;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.StatutProjet;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.repository.DalProjet;
import fr.isika.cdi07.projet3demo.utils.HibernateUtil;

public class MainProjet3Demo {

	
	
	public static void main(String[] args) {
		
		DalProjet dalProjet = new DalProjet();
		
		Projet projet = new Projet();
		projet.setTitre("Test");
		projet.setDescriptionCourte("testtest");
		projet.setDescriptionLongue("Testtestestest");
		projet.setDateFin(Date.valueOf(LocalDate.now()));
		projet.setDateMaj(Date.from(Instant.now()));
		projet.setDonMateriel(false);
		projet.setDonTemps(false);
		projet.setMontantAttendu(2000.0);
		projet.setMontantCollecte(0.0);
		projet.setStatutDuProjet(StatutProjet.CREE);
		
		dalProjet.persister(projet);
		
		Notification notification = new Notification();
		notification.setLibelle("test");
		notification.setDate(LocalDateTime.of(2021, 02, 02, 13, 00));
		
		dalProjet.persisterNotif(notification);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("gné");
		utilisateur.setPrenom("gno");
		utilisateur.setDateMaj(LocalDateTime.now());
		utilisateur.setEmail("gno@gni.com");
		utilisateur.setMdp("gnoki");
		
		dalProjet.persisterUtilisateur(utilisateur);
		
		Historique historique = new Historique();
		historique.setActeur(utilisateur);
		historique.setDateHeure(LocalDateTime.now());
		historique.setEtatProjet(StatutProjet.CREE);
		historique.setEvenement("Creation");
		historique.setLibelle("Historique");
		historique.setNotification(notification);
		historique.setProjet(projet);
		
		dalProjet.persisterHisto(historique);
		Role role = new Role(TypeRole.ADMINISTRATEUR, utilisateur);
		dalProjet.persisterRole(role);
		
		Appreciation appreciation = new Appreciation();
		appreciation.setRole(role);
		appreciation.setDate(Date.valueOf(LocalDate.now()));
		appreciation.setNote(5);
		appreciation.setProjet(projet);
		
		dalProjet.persisterAppreciation(appreciation);		
		
		List<Projet> listeprojet = dalProjet.findAllProject();
		for(Projet projetTotaux : listeprojet) {
			System.out.println(projetTotaux);
		}
		
		
		projet.setDescriptionCourte("Cmodifier");
		dalProjet.modifierProjet(projet);
		

		for(Projet projetTotaux : listeprojet) {
			System.out.println(projetTotaux);
		}
		
		
		// Relations !!
		
		
		// Persistance des données
		
		
		

		
		// Fermer les ressources
		HibernateUtil.closeAll();
	}
}
