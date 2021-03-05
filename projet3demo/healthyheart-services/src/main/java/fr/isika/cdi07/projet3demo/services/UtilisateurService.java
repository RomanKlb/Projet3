package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.UtilisateurRepository;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepo;
	
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		utilisateur.setDateMaj(Date.from(Instant.now()));
		return utilisateurRepo.save(utilisateur);
	}

	public Utilisateur chercherUtilisateurParEmailEtMdp(String email, String mdp) {
		return utilisateurRepo.findUtilisateurByEmailAndMdp(email, mdp);
	}
	
	public List<Utilisateur> retournerTousLesUtilisateur(){
		return utilisateurRepo.findAll();
	}
	
	public Utilisateur chercherUtilisateurParEmail(String email) {
		return utilisateurRepo.findUtilisateurByEmail(email);
	}
	
	public Utilisateur chercherOptionalUtilisateurParEmail(String email) {
		Optional<Utilisateur> optionalUtilisateur = Optional.of(utilisateurRepo.findUtilisateurByEmail(email));
		Utilisateur utilisateur = null;
		if(optionalUtilisateur.isPresent()) {
			utilisateur = optionalUtilisateur.get();
		}else {
			throw new RuntimeException("L'utilisateur n'a pas été trouvé");
		}
		return utilisateur;
	}
	
	public Utilisateur ajoutUtilisateur(Utilisateur utilisateur) {
		utilisateur.setDateMaj(Date.from(Instant.now()));

		return utilisateurRepo.save(utilisateur);
	}

	public List<Utilisateur> afficherAllUtilisateur() {
		return utilisateurRepo.findAll();
	}

}
