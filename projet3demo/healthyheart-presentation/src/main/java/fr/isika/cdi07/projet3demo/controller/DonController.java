package fr.isika.cdi07.projet3demo.controller;

import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.isika.cdi07.projet3demo.dao.ProjetRepository;
import fr.isika.cdi07.projet3demo.model.DonMateriel;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutDon;
import fr.isika.cdi07.projet3demo.modelform.DonForm;
import fr.isika.cdi07.projet3demo.services.DonMaterielService;
import fr.isika.cdi07.projet3demo.services.DonMonetaireService;
import fr.isika.cdi07.projet3demo.services.DonTempsService;
import fr.isika.cdi07.projet3demo.services.ProjetService;

@Controller
@RequestMapping("/don")
@Validated
public class DonController {
	
	private static final String DEFAULT_REDIRECTION = "redirect:../afficherListeDesDon";
	private static final Logger logger = Logger.getLogger(DonController.class.getSimpleName());
	
	@Autowired 
	private DonMonetaireService donMonetaireService;
	
	@Autowired 
	private DonMaterielService donMaterielService;
	
	@Autowired 
	private DonTempsService donTempsService;
	
	@Autowired
	private ProjetService projetService;
	
	@GetMapping("/faireUnDon/projet")
	public String faireUnDon(@RequestParam long id, Model model) {		
		DonMonetaire donMonetaire = new DonMonetaire();
		DonMateriel donMateriel = new DonMateriel();
		DonTemps donTemps = new DonTemps();
		ParticipationProjet participationProjet = new ParticipationProjet();
		
		DonForm donForm = new DonForm();
		//a changer avvec le service:
		Optional<Projet> projetFound = donMonetaireService.getProjet(id);
		
		if(!projetFound.isPresent())
			return "projet_not_found";
		participationProjet.setProjet(projetFound.get());
		//TODO check boolean don materiel et temps
		
		model.addAttribute("donMonetaire", donMonetaire);
		model.addAttribute("donMateriel", donMateriel);
		model.addAttribute("donTemps", donTemps);
		
		model.addAttribute("partProjet", participationProjet);
		model.addAttribute("donForm", donForm); 
		
		return "faire_un_don";
	}
	
	@PostMapping("/sauvegarderDonMonetaire")
	public String sauvegarderDonMonetaire(
			@ModelAttribute("donMonetaire") DonMonetaire don, 
			@ModelAttribute("partProjet") ParticipationProjet pp,
			@ModelAttribute("donForm") DonForm donForm) {
		System.out.println("participation projet anonyme = " + donForm.isAnonyme());
		pp.setAnonyme(donForm.isAnonyme());
		StatutDon statutDon = donMonetaireService.enregistrerDansLaBase(don, pp);
		return statutDon.equals(StatutDon.APPROUVE)
				? DEFAULT_REDIRECTION
				: "don_en_attente";
		
	}
	
	@PostMapping("/sauvegarderDonMateriel")
	public String sauvegarderDonMateriel(
			@ModelAttribute("donMateriel") DonMateriel don,
			@ModelAttribute("partProjet") ParticipationProjet pp) {
		donMaterielService.enregistrerDansLaBase(don, pp);
		return DEFAULT_REDIRECTION;
	}
	
	@PostMapping("/sauvegarderDonTemps")
	public String sauvegarderDonTemps(
			@ModelAttribute("donTemps") DonTemps don,
			@ModelAttribute("partProjet") ParticipationProjet pp) {
		donTempsService.enregistrerDansLaBase(don, pp);
		return DEFAULT_REDIRECTION;
	}
}
