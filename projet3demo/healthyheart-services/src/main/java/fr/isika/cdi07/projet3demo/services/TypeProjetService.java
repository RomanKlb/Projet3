package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.TypeProjetRepository;
import fr.isika.cdi07.projet3demo.model.TypeProjet;

@Service

public class TypeProjetService {

	@Autowired
	private TypeProjetRepository typeProjetRepo;
	
	
	public TypeProjet ajoutTypeProjet(TypeProjet typeProjet) {
		typeProjet.setDate(Date.from(Instant.now()));
		
		return typeProjetRepo.save(typeProjet);
	}
	
	
	public List<TypeProjet> afficherAllTypeProjet(){
		return typeProjetRepo.findAll();
	}


	public Optional<TypeProjet> getTypeProjetById(Long id) {
		return typeProjetRepo.findById(id);
	}


	public TypeProjet getTypeProjetByIdNoOptional(Long id) {
		return typeProjetRepo.findByIdTypeProjet(id);
	}
}
