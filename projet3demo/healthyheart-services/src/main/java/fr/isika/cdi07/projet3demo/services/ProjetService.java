package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
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

		//portefeuilleprojet doit exister
		//categorieprojet doit exister


		//DateFin en dur warning ...
		projet.setDateFin(Date.from(Instant.now()));
		projet.setDateMaj(Date.from(Instant.now()));
		projet.setMontantCollecte(Double.valueOf(0));
		projet.setStatutDuProjet(StatutProjet.CREE);

		return projetRepo.save(projet);

	}

	public List<Projet> afficherAllProjet() {

		return projetRepo.findAll();
	}

	public List<Projet> afficherProjetPublie() {
		List<Projet> projets = projetRepo.findAll();
		List<Projet> projetsPublies = new ArrayList<Projet>();
		for(Projet projet : projets) {
			if(projet.getStatutDuProjet().equals(StatutProjet.PUBLIE))
				projetsPublies.add(projet);
		}
		return projetsPublies;
	}


}
