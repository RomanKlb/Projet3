package fr.isika.cdi07.projet3demo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.PorteurProjetRepository;
import fr.isika.cdi07.projet3demo.model.PorteurProjet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.TypePorteur;

@Service
public class PorteurProjetService {
	private static final Logger LOGGER = Logger.getLogger(PorteurProjetService.class.getSimpleName());

	@Autowired
	PorteurProjetRepository porteurProjetRepo;
	
	public PorteurProjet isPresent(Role role) {
		
		List<PorteurProjet> porteurProjets = porteurProjetRepo.findAll();
		Optional<PorteurProjet> optPorteurProjet = porteurProjets.stream().filter(r -> r.getRole().equals(role)).findFirst();
		if(optPorteurProjet.isPresent()) {
			LOGGER.info("*****DANS PORTEUR PROJET SERVICE MEHTODE ISPRESENT : "+optPorteurProjet);
			return optPorteurProjet.get();
		
		}
			
		PorteurProjet newPorteurProjet = new PorteurProjet();
		newPorteurProjet.setRole(role);
		newPorteurProjet.setTypePorteur(TypePorteur.PRIVE);
		return porteurProjetRepo.save(newPorteurProjet);
		
	}
	
	public PorteurProjet ajoutPorteur(PorteurProjet porteurProjet) {
		return porteurProjetRepo.save(porteurProjet);
	}

}
