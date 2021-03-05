package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.UtilisateurRepository;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepo;

	public Utilisateur ajoutUtilisateur(Utilisateur utilisateur) {
		utilisateur.setDateMaj(Date.from(Instant.now()));

		return utilisateurRepo.save(utilisateur);
	}

	public List<Utilisateur> afficherAllUtilisateur() {
		return utilisateurRepo.findAll();
	}

}
