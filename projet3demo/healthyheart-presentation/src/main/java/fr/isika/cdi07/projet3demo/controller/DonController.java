package fr.isika.cdi07.projet3demo.controller;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private static final String EN_ATTENTE_REDIRECTION = "don_en_attente";
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
		DonForm donForm = new DonForm();
		Optional<Projet> projetFound = projetService.getProjetById(id);

		if(!projetFound.isPresent())
			return "projet_not_found";
		
		donForm.getParticipationProjet().setProjet(projetFound.get());
		model.addAttribute("donForm", donForm); 
		return "faire_un_don";
	}

	@PostMapping("/sauvegarderDonMonetaire")
	public String sauvegarderDonMonetaire(@ModelAttribute("donForm") DonForm donForm) {
		donForm.getParticipationProjet().setAnonyme(donForm.isAnonyme());
		StatutDon statutDon = donMonetaireService
				.enregistrerDansLaBase(donForm.getDonMonetaire(), donForm.getParticipationProjet());
		return statutDon.equals(StatutDon.APPROUVE)
				? DEFAULT_REDIRECTION
						: EN_ATTENTE_REDIRECTION;
	}

	@PostMapping("/sauvegarderDonMateriel")
	public String sauvegarderDonMateriel(@ModelAttribute("donForm") DonForm donForm) {
		donForm.getParticipationProjet().setAnonyme(donForm.isAnonyme());
		StatutDon statutDon = donMaterielService
				.enregistrerDansLaBase(donForm.getDonMateriel(), donForm.getParticipationProjet());
		return statutDon.equals(StatutDon.APPROUVE)
				? DEFAULT_REDIRECTION
						: EN_ATTENTE_REDIRECTION;
	}

	@PostMapping("/sauvegarderDonTemps")
	public String sauvegarderDonTemps(@ModelAttribute("donForm") DonForm donForm) {
		donForm.getParticipationProjet().setAnonyme(donForm.isAnonyme());
		StatutDon statutDon = donTempsService
				.enregistrerDansLaBase(donForm.getDonTemps(), donForm.getParticipationProjet());
		return statutDon.equals(StatutDon.APPROUVE)
				? DEFAULT_REDIRECTION
						: EN_ATTENTE_REDIRECTION;
	}
}
