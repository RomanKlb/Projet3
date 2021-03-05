package fr.isika.cdi07.projet3demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.isika.cdi07.projet3demo.model.DonMateriel;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.services.DonMaterielService;
import fr.isika.cdi07.projet3demo.services.DonMonetaireService;
import fr.isika.cdi07.projet3demo.services.DonTempsService;

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
	
	@GetMapping("/faireUnDon")
	public String faireUnDon(Model model) {		
		DonMonetaire donMonetaire = new DonMonetaire();
		DonMateriel donMateriel = new DonMateriel();
		DonTemps donTemps = new DonTemps();
		
		model.addAttribute("donMonetaire", donMonetaire);
		model.addAttribute("donMateriel", donMateriel);
		model.addAttribute("donTemps", donTemps);
		
		return "faire_un_don";
	}
	
	@PostMapping("/sauvegarderDonMonetaire")
	public String sauvegarderDonMonetaire(@Valid DonMonetaire don, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
			return "faire_un_don";
		donMonetaireService.enregistrerDansLaBase(don);
		return DEFAULT_REDIRECTION;
	}
	
	@PostMapping("/sauvegarderDonMateriel")
	public String sauvegarderDonMateriel(@ModelAttribute("donMateriel") DonMateriel don) {
		donMaterielService.enregistrerDansLaBase(don);
		return DEFAULT_REDIRECTION;
	}
	
	@PostMapping("/sauvegarderDonTemps")
	public String sauvegarderDonTemps(@ModelAttribute("donTemos") DonTemps don) {
		donTempsService.enregistrerDansLaBase(don);
		return DEFAULT_REDIRECTION;
	}
}
