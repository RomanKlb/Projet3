package fr.isika.cdi07.projet3demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DonMonetaireRepository;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;

@Service
public class DonService {
	
	@Autowired
	private DonMonetaireRepository donMonetaireRepo;
	
	public List<DonMonetaire> afficherDonsMonetaires() {
		return donMonetaireRepo.findAll();
	}
	
	public Long compterDonsMonetaire() {
		return donMonetaireRepo.count();
	}
	
	public DonMonetaire ajout(DonMonetaire don) {
		return donMonetaireRepo.save(don);
	}

}
