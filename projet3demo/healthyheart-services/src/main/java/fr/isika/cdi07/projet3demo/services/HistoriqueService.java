package fr.isika.cdi07.projet3demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.HistoriqueRepository;
import fr.isika.cdi07.projet3demo.model.Historique;


@Service
public class HistoriqueService {
	
	@Autowired
	private HistoriqueRepository historiqueRepo;
	
	public List<Historique> afficherAllHistorique() {
		return historiqueRepo.findAll();
	}
	
	public Historique ajout(Historique historique) {
		return historiqueRepo.save(historique);
	}

	public Historique getHistoById(Long histoId) {
		return historiqueRepo.getOne(histoId);
	}


}
