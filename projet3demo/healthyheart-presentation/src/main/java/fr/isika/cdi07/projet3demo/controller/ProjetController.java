package fr.isika.cdi07.projet3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.services.ProjetService;

@Controller
public class ProjetController {

	@Autowired
	private ProjetService projetService;
	
	@GetMapping("/ShowAllProjetList")
	public String showAllProjetList(Model model) {
		model.addAttribute("listProjet", projetService.afficherAllProjet());
		return "listProjet";
	}
	
	@GetMapping("/ShowProjetListPublie")
	public String showProjetListPublie(Model model) {
		model.addAttribute("listProjetPublie", projetService.afficherProjetPublie());
		return "catalogue";
	}
	
	@GetMapping("/ShowNewProjetForm")
	public String showNewProjetForm(Model model) {
				
		//create model attribute to bind form data
		Projet projet = new Projet();
		model.addAttribute("projet", projet);
		return "newProjet";
	}
	
	@PostMapping("/saveProjet")
	public String saveProjet(@ModelAttribute("projet") Projet projet) {
		//save projet to database
		projetService.ajoutProjet(projet);
		return "listProjet";
	}
}
