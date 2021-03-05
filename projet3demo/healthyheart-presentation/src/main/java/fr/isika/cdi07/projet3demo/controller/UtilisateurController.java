package fr.isika.cdi07.projet3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/ShowAllUtilisateur")
	public String showAllUtilisateur(Model model) {
		model.addAttribute("listUtilisateur", utilisateurService.afficherAllUtilisateur());
		return "listUtilisateur";
	}
	
	@GetMapping("/ShowNewUtilisateur")
	public String showNewUtilisateur(Model model) {
				
		//create model attribute to bind form data
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "newUtilisateur";
	}
	
	@PostMapping("/saveUtilisateur")
	public String saveUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		//save projet to database
		utilisateurService.ajoutUtilisateur(utilisateur);
		return "listUtilisateur";
	}

}
