package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.ProjetRepository;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutProjet;

@Service
public class ProjetService {

	@Autowired
	private ProjetRepository projetRepo;
	
	public Projet ajoutProjet(Projet projet) {
		
		projet.setDateFin(Date.from(Instant.now()));
		projet.setDateMaj(Date.from(Instant.now()));
		projet.setMontantCollecte(Double.valueOf(0));
		projet.setStatutDuProjet(StatutProjet.CREE);
		
		return projetRepo.save(projet);
		
	}

	public List<Projet> afficherAllProjet() {
		
		return projetRepo.findAll();
	}
	
	
}
